 package me.ayush_03.chattranslationplus.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

import me.ayush_03.chattranslationplus.Main;


public class Translate_Cmd implements CommandExecutor {
	
	Main pl;
	public Translate_Cmd(Main pl) {
		this.pl = pl;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("translate")) {

			if (args.length <= 2) {
				sender.sendMessage(Main.prefix + ChatColor.RED + " Usage: /translate [translate_from] [translate_to] [String to translate]");
				return true;
			}
			
			if (!Main.langs.contains(args[0]) || !Main.langs.contains(args[1])) {
				sender.sendMessage(Main.prefix + ChatColor.RED + " Invalid language ID!");
				sender.sendMessage( ChatColor.AQUA + " For all languages' id use:- /translator languages");
				return true;
			} else {
				
				if (args[0].equalsIgnoreCase("greek") || args[1].equalsIgnoreCase("greek")) {
					String msg = "";
					for (int i = 2; i < args.length; i++) {
						msg =  msg + args[i] + " ";
					}
					
					try {
						  Translate.setKey(pl.getConfig().getString("translator-key"));
						  
						  String translate_from = args[0].replace("greek", "el");
						  String translate_to = args[1].replace("greek", "el");

					Language from = Language.fromString(translate_from);
					Language to = Language.fromString(translate_to);
					
					sender.sendMessage(ChatColor.GREEN + "Translating.....");
					
						String translated = Translate.execute(msg, from, to);
						sender.sendMessage("§6" + translated);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					return true;
				} 
				
				if (args[0].equalsIgnoreCase("portuguese") || args[1].equalsIgnoreCase("portuguese")) {
					String msg = "";
					for (int i = 2; i < args.length; i++) {
						msg =  msg + args[i] + " ";
					}
					
					try {
						  Translate.setKey(pl.getConfig().getString("translator-key"));
						  
						  String translate_from = args[0];
						  String translate_to = args[1];

					Language from = Language.fromString(translate_from.replace("portuguese", "pt"));
					Language to = Language.fromString(translate_to.replace("portuguese", "pt"));
					
					sender.sendMessage(ChatColor.GREEN + "Translating.....");
						String translated = Translate.execute(msg, from, to);
						sender.sendMessage("§6" + translated);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					return true;
				}
				
				 String msg = "";
				for (int i = 2; i < args.length; i++) {
					msg =  msg + args[i] + " ";
				}
				
				try {
					  Translate.setKey(pl.getConfig().getString("translator-key"));
					  
					  String translate_from = args[0];
					  String translate_to = args[1];

				Language from = Language.fromString(translate_from);
				Language to = Language.fromString(translate_to);
				
				sender.sendMessage(ChatColor.GREEN + "Translating.....");
				
					String translated = Translate.execute(msg, from, to);
					sender.sendMessage("§6" + translated);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
			
		return true;
		}

}
