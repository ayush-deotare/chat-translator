package me.ayush_03.chattranslationplus.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rmtheis.yandtran.language.Language;

import me.ayush_03.chattranslationplus.FileManager;
import me.ayush_03.chattranslationplus.Main;

public class LanguageCMD implements CommandExecutor {
	
	Main plugin;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("language")) {
			if (!(sender instanceof Player)) {
				String only_players = FileManager.getManager().getLang().getString("only-players");
				only_players = ChatColor.translateAlternateColorCodes('&', only_players);
				sender.sendMessage(only_players);
				return true;
			}
			
			Player p = (Player) sender;
			if (!p.hasPermission("translator.use")) {
				p.sendMessage("§cYou do not have permission to execute this command.");
				return true;
			}
			
			if (args.length != 1) {
				p.sendMessage("§cUsage: /language <language>");
				return true;
			}
			
			if (args[0].equalsIgnoreCase("disable")) {
				if (Main.auto.containsKey(p.getName())) {
					Main.auto.remove(p.getName());
					
					String disabled = FileManager.getManager().getLang().getString("auto_translation-disabled");
					disabled = ChatColor.translateAlternateColorCodes('&', disabled);
					p.sendMessage(disabled);
					return true;
				} else {
					String already = FileManager.getManager().getLang().getString("auto_translation-already-disabled");
					already = ChatColor.translateAlternateColorCodes('&', already);
						
					p.sendMessage(already);
					return true;
				}
					
			}
			
			if (!Main.langs.contains(args[0])) {
				p.sendMessage(Main.prefix + ChatColor.RED +  " Invalid Language ID!");
				p.sendMessage(ChatColor.AQUA + " For all languages' id use:- /translator languages");
				return true;
			}
			
//			if (Main.getInstance().getConfig().getBoolean("enable-default-language") == true) {
//				p.sendMessage("§cERROR: Default language is allowed on server, which means this command will be disabled. Contact server administrator!");
//				return true;
//			}
			
			Language lang = Language.fromString(args[0].replace("greek", "el").replace("portuguese", "pt"));
			Main.auto.put(p.getName(), lang);
			
			String enabled = FileManager.getManager().getLang().getString("auto_translation-enabled");
			enabled = ChatColor.translateAlternateColorCodes('&', enabled);
			enabled = enabled.replace("%lang%", args[0].toUpperCase());
			p.sendMessage(enabled);
		}
		return true;
	}

}
