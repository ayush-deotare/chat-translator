package me.ayush_03.chattranslationplus.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

import me.ayush_03.chattranslationplus.FileManager;
import me.ayush_03.chattranslationplus.Main;
import me.ayush_03.chattranslationplus.api.PrivateMessageTranslateEvent;

public class PMCmd implements CommandExecutor {
	
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		
		// /chat <player> <language_from> <language_to> [Message]
		
		if (cmd.getName().equalsIgnoreCase("chat")) {
			
			if (!(p.hasPermission("translator.use"))) {
				p.sendMessage("§cYou do not have permission to execute this command.");
				return true;
		      }
			
			if (args.length < 4) {
				p.sendMessage("§6Usage: §e/chat <player> (language_from) (language_to) [message]");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			if (!Main.langs.contains(args[1]) || !Main.langs.contains(args[2])) {
				p.sendMessage(Main.prefix + ChatColor.RED + " Invalid language ID!");
				p.sendMessage(ChatColor.AQUA + "For all languages' id use:- /translator languages");
				return true;
			}
			
			if (target == null) {
				String offline = FileManager.getManager().getLang().getString("player-not-online");
				offline = ChatColor.translateAlternateColorCodes('&', offline);
				offline = offline.replace("%player%", args[0]);
				p.sendMessage(offline);
			}
			
			String msg = "";
			for (int i = 3; i < args.length; i++) {
				msg =  msg + args[i] + " ";
			}
			
			try {
				  Translate.setKey(Main.getInstance().getConfig().getString("translator-key"));
				  
				  Language from = Language.fromString(args[1].replace("greek", "el").replace("portuguese", "pt"));
				  Language to = Language.fromString(args[2].replace("greek", "el").replace("portuguese", "pt"));
				 
				 String translated = Translate.execute(msg, from, to);
				 
				 PrivateMessageTranslateEvent event = new PrivateMessageTranslateEvent(p, target, msg, translated, from, to);
				 Bukkit.getServer().getPluginManager().callEvent(event);
				 
				 if (!event.isCancelled()) {
				 target.sendMessage("§6" + p.getName() + " -> me §8: §a" + translated);
				 p.sendMessage("§6me -> " + target.getName() + " §8: §a" + translated);
				 }
			
			} catch (Exception ex) {
				System.out.println("[ChatTranslatorPlus] There was error while translating a message. \n Cause: Invalid translator key.");
			}
		}
		return true;
	}

}
