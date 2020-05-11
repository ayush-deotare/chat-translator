package me.ayush_03.chattranslationplus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.rmtheis.yandtran.detect.Detect;
import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

import me.ayush_03.chattranslationplus.api.SignTranslateEvent;

public class SignTranslator implements Listener {
	
	int a = 0;
	private ArrayList<String> translateds = new ArrayList<String>();
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && !Main.auto.containsKey(p.getName())) return;
		if (!(e.getClickedBlock().getState() instanceof Sign)) return;
		if (!(p.isSneaking())) return;
		
		Sign sign = (Sign) e.getClickedBlock().getState();
		
		p.sendMessage("§6======== §eSign Translator §6========");
		for (String str : sign.getLines()) {
			try {
				Translate.setKey(Main.getInstance().getConfig().getString("translator-key"));
				String detect = Detect.execute(str);
				Language to = Main.auto.get(p.getName());
				a = (a + 1);
				String translated = Translate.translate(str, detect, to);
				p.sendMessage("§6Line " + a + " : §e" + translated);
				translateds.add(translated);
				
			} catch (Exception ex) {
				
			}
		}
		p.sendMessage("§6======== §eSign Translator §6========");
		a = 0;
		
		SignTranslateEvent event = new SignTranslateEvent(p, sign, Main.auto.get(p.getName()), sign.getLines(), translateds.toArray(new String[translateds.size()]));
		Bukkit.getPluginManager().callEvent(event);
		
	}

}
