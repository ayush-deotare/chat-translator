package me.ayush_03.chattranslationplus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.rmtheis.yandtran.detect.Detect;
import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

import me.ayush_03.chattranslationplus.api.ChatTranslateEvent;

public class TalkEvent implements Listener {
	public List<String> lines = new ArrayList<String>();

	Main pl;
	public TalkEvent(Main pl) {
		this.pl = pl;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTalk(final AsyncPlayerChatEvent e) {
		final Player p = e.getPlayer();	
		
		
		if (Main.language.containsKey(p.getName())) {
			
			try {
		        Translate.setKey(pl.getConfig().getString("translator-key"));
		        
		        Language lang_from = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_from"));
		        Language lang_to = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_to"));
		       
		        String translated = Translate.execute(ChatColor.stripColor(e.getMessage()), lang_from, lang_to);

		        ChatTranslateEvent event = new ChatTranslateEvent(e.getMessage(), translated, p, lang_from, lang_to);
		        Bukkit.getServer().getPluginManager().callEvent(event);
		        if (!event.isCancelled()) {
		        	e.setMessage(translated);
		        	
//		        	for (String str : Main.auto.keySet()) {
//						Player pl = Bukkit.getPlayer(str);
//						Language to = Main.auto.get(str);
//						
//						String translate = Translate.execute(e.getMessage(), lang_to, to);
//						pl.sendMessage("§a" + p.getName() + " §8: §6" + translate);
		        	
		        	if  (!Main.auto.isEmpty()) {
//		        		for (String str : Main.auto.keySet()) {
//							Player pl = Bukkit.getPlayer(str);
//							e.getRecipients().remove(pl);
//		        		}
		        	
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new BukkitRunnable() {
							
							@Override
							public void run() {

									
									try {
										Translate.setKey(pl.getConfig().getString("translator-key"));
										String detect = Detect.execute(e.getMessage());
										for (String str : Main.auto.keySet()) {
											Player pl = Bukkit.getPlayer(str);
											Language to = Main.auto.get(str);
											
											String translate = Translate.translate(e.getMessage(), detect, to);
											String format = Main.getInstance().getConfig().getString("format");
											format = ChatColor.translateAlternateColorCodes('&', format);
											format = format.replace("{DISPLAY_NAME}", p.getDisplayName()).replace("{MESSAGE}", translate);
											pl.sendMessage(format);
										}
									} catch (Exception ex){
										
									
									}
									} 
								
							}, 5L);
						}
//					}
		        	
		        }
		      } catch (Exception ex) {
		      }
		} 
			
			if (pl.enabledDefaultLanguage() && !Main.language.containsKey(p.getName())) {
				try {
					Translate.setKey(pl.getConfig().getString("translator-key"));
					String detect = Detect.execute(e.getMessage());
					String to = Main.getInstance().getConfig().getString("default-language");
					
					if (to.equalsIgnoreCase("greek")) {
						to = to.replace("greek", "el");
					}
					
					if (to.equalsIgnoreCase("portuguese")) {
						to = to.replace("portuguese", "pt");
					}
					
					Language lang = Language.fromString(to);
					
					String translate = Translate.translate(e.getMessage(), detect, lang);
					e.setMessage(translate);
					
					if  (!Main.auto.isEmpty()) {
//						for (String str : Main.auto.keySet()) {
//							Player pl = Bukkit.getPlayer(str);
//							e.getRecipients().remove(pl);
//		        		}
					Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new BukkitRunnable() {
						
						@Override
						public void run() {

								
								try {
									Translate.setKey(pl.getConfig().getString("translator-key"));
									String detect = Detect.execute(e.getMessage());
									for (String str : Main.auto.keySet()) {
										Player pl = Bukkit.getPlayer(str);
										Language to = Main.auto.get(str);
										
										String translate = Translate.translate(e.getMessage(), detect, to);
										String format = Main.getInstance().getConfig().getString("format");
										format = ChatColor.translateAlternateColorCodes('&', format);
										format = format.replace("{DISPLAY_NAME}", p.getDisplayName()).replace("{MESSAGE}", translate);
										pl.sendMessage(format);
									}
								} catch (Exception ex){
								
								}
								} 
							
						}, 5L);
					}
					
//					for (String players : Main.auto.keySet()) {
//						Player pl = Bukkit.getPlayer(players);
//					//	e.getRecipients().remove(pl);
//						
//						
//						Language p_to = Main.auto.get(players);
//						
//						String translated = Translate.translate(e.getMessage(), detect, p_to);
//						pl.sendMessage("§a" + p.getName() + " §8: §6" + translated);
//					}
				} catch (Exception ex) {
				}
			} 
			
			if (!Main.language.containsKey(p.getName()) && !pl.enabledDefaultLanguage()) {
				if  (!Main.auto.isEmpty()) {
//					for (String str : Main.auto.keySet()) {
//						Player pl = Bukkit.getPlayer(str);
//						e.getRecipients().remove(pl);
//	        		}
					Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new BukkitRunnable() {
						
						@Override
						public void run() {
							try {
								Translate.setKey(pl.getConfig().getString("translator-key"));
								String detect = Detect.execute(e.getMessage());
								for (String str : Main.auto.keySet()) {
									Player pl = Bukkit.getPlayer(str);
									e.getRecipients().remove(pl);
									Language to = Main.auto.get(str);
									
									String translate = Translate.translate(e.getMessage(), detect, to);
									// Bukkit.getServer().getConsoleSender().sendMessage(String.format(format.get(0), new Object[] {args[0], message.toString()}));
									// pl.sendMessage("§a" + p.getName() + " §8: §6" + translate);
									String format = Main.getInstance().getConfig().getString("format");
									format = ChatColor.translateAlternateColorCodes('&', format);
									format = format.replace("{DISPLAY_NAME}", p.getDisplayName()).replace("{MESSAGE}", translate);
									pl.sendMessage(format);
								}
							} catch (Exception ex){
							
							}
							
						}
					}, 5L);
					
	
				
				}
			}
		
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) { 
		Player p = e.getPlayer();
		if (Main.language.containsKey(p.getName())) {
			if (!pl.keepTranslator()) {
			Main.language.remove(p.getName());
			FileManager.getManager().getDatabase().set(p.getName() + ".translate_from", null);
			FileManager.getManager().getDatabase().set(p.getName() + ".translate_to", null);
			pl.saveConfig();
			}
		}
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Main.language.containsKey(p.getName())) {
			p.sendMessage("§6You have translator mode: §aON");
			p.sendMessage("§7If you wish to disable translator mode, do: §b/translator disable");
		}
	}
	
//	@EventHandler
//	public void onExecute(PlayerCommandPreprocessEvent e) {
//		Player p = e.getPlayer();
//		
//		
//		// w,m,t,pm,emsg,epm,tell,etell,whisper,ewhisper
//		String[] commands = {"w","m","t","pm","emsg","epm","tell","etell","whisper","ewhisper"};
//		for (String a : commands) {
//		if (e.getMessage().startsWith("/" + a)) {
//			// /msg Ayush_03 Testing plugin
//						
//		String[] args = e.getMessage().split(" ");
//		if (args.length == 0 || args.length == 1  || args.length == 2) return;
//		
//		StringBuilder message = new StringBuilder();
//		for (int i = 2; i < args.length; i++) {
//			message = message.append(args[i] + " ");
//		}
//		
//		if (!pl.allowEssentials()) return;
//		
//		if (Main.language.containsKey(p.getName())) {
//			 Translate.setKey(FileManager.getManager().getDatabase().getString("translator-key"));
//			 
//			Language from = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_from"));
//			Language to = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_to"));
//			
//			try {
//				String translated = Translate.execute(message.toString(), from, to);
//				System.out.println(args[0] + " " + args[1]  + " " + translated);
//				e.setMessage(args[0] + " " + args[1]  + " " + translated);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		}
//		}
//		
//		if (e.getMessage().startsWith("/r") || e.getMessage().startsWith("/er") || e.getMessage().startsWith("/reply") || e.getMessage().startsWith("/er")) {
//			String[] args = e.getMessage().split(" ");
//			// /r <message>
//			
//			if (args.length == 0 || args.length == 1) return;
//			
//			StringBuilder message = new StringBuilder();
//			for (int i = 1; i < args.length; i++) {
//				message = message.append(args[i] + " ");
//			}
//			
//			if (!pl.allowEssentials()) return;
//			
//			if (Main.language.containsKey(p.getName())) {
//				 Translate.setKey(FileManager.getManager().getDatabase().getString("translator-key"));
//				 
//				Language from = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_from"));
//				Language to = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_to"));
//				
//				try {
//					String translated = Translate.execute(message.toString(), from, to);
//					System.out.println(args[0] + " " + args[1]  + " " + translated);
//					e.setMessage(args[0] + " " + translated);
//				} catch (Exception e1) {
//				     TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//			
//		}
//	}
	
	@EventHandler
	public void onExecute(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (!Main.language.containsKey(p.getName())) return;
		
		for (String command : Main.getInstance().getConfig().getStringList("commands")) {
			String[] split = command.split(" ; ");
			String[] args = e.getMessage().split(" ");
			
			int start = Integer.parseInt(split[1]);
			
			if (args[0].startsWith(split[0])) {
				
				if (args.length < start) return;
				
				StringBuilder message = new StringBuilder();
				for (int i = start; i < args.length; i ++) {
					message = message.append(args[i] + " ");
				}
				
				 Translate.setKey(pl.getConfig().getString("translator-key"));
				 
				Language from = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_from"));
				Language to = Language.fromString(FileManager.getManager().getDatabase().getString(p.getName() + ".translate_to"));
				
				try {
					String translated = Translate.execute(message.toString(), from, to);
					
					StringBuilder cmd = new StringBuilder();
					for (int i = 0; i < start; i ++) {
						cmd = cmd.append(args[i] + " ");
					}
					e.setMessage(cmd.toString() + "" + translated);
				} catch (Exception ex) {}
				
			}
			
			
		}
	}

}
