package me.ayush_03.chattranslationplus.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rmtheis.yandtran.language.Language;

import me.ayush_03.chattranslationplus.FileManager;
import me.ayush_03.chattranslationplus.Main;

public class Cmd implements CommandExecutor {
	
	Main pl;
	public Cmd(Main pl) {
		this.pl = pl;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("translator")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Main.prefix + ChatColor.RED + " Only players can translate their chat!");
				return true;
			}		
			
			Player p = (Player) sender;
			if (!(p.hasPermission("translator.use"))) {
				p.sendMessage(Main.prefix + ChatColor.RED +" You do not have permission to execute this command!");
				return true;
			} 
			
			if (args.length == 0) {
				p.sendMessage("§e/translator [translate_from] [translate_to] §6: A command to set translator mode.");
				p.sendMessage("§e/translate [translate_from] [translate_to] {String to translate} §6: A command to translate single string.");
				p.sendMessage("§e/translator languages §6: Displays all languages followed by their ID.");
				p.sendMessage("§e/translator disable §6: Disables the translator mode.");
				p.sendMessage("§e/translator check §6: Checks if you are in translator mode.");
				p.sendMessage("§e/language <language> §6: Enables Auto Self Translator.");
				p.sendMessage("§e/language disable §6: Disables Auto Self Translator.");
				p.sendMessage("§e/translator info §6: Displays the information of the plugin.");
				p.sendMessage("§e/translator reload §6: Reloads the configuration of the plugin (Only for Admins).");
				return true;
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("languages")) {
					p.sendMessage("§6Language §8- §eLanguage ID");
					p.sendMessage("");
					for (String str : Main.langs) {
						p.sendMessage("§6" + str.toUpperCase() + " §8- §e" + str.toLowerCase());
					}
				}
				
				if (args[0].equalsIgnoreCase("disable")) {
					if (!Main.language.containsKey(p.getName())) {
						String already = FileManager.getManager().getLang().getString("translator-already-disabled");
						already = ChatColor.translateAlternateColorCodes('&', already);
						p.sendMessage(already);
						return true;
					} else {
						Main.language.remove(p.getName());
						FileManager.getManager().set(p.getName() + ".translate_from", null);
						FileManager.getManager().set(p.getName() + ".translate_to", null);
						
						String disabled = FileManager.getManager().getLang().getString("translator-disabled");
						disabled = ChatColor.translateAlternateColorCodes('&', disabled);
						p.sendMessage(disabled);
					}
				}
				
				if (args[0].equalsIgnoreCase("check")) {
					if (!Main.language.containsKey(p.getName())) {
						p.sendMessage(Main.prefix + ChatColor.GOLD + " Translation: " + ChatColor.RED + "OFF");
					} else {
						p.sendMessage(Main.prefix + ChatColor.GOLD + " Translation: " + ChatColor.GREEN + "ON");
						p.sendMessage(ChatColor.YELLOW + FileManager.getManager().getDatabase().getString(p.getName() + ".translate_from").toUpperCase() + " to " + FileManager.getManager().getDatabase()
						.getString(p.getName() + ".translate_to").toUpperCase());
					}
				}
				
				if (args[0].equalsIgnoreCase("reload")) {
					if (!p.hasPermission("translator.reload")) {
						p.sendMessage("§cYou do not have permission to execute this command.");
						return true;
					}
					
					Main.getInstance().reloadConfig();
					
					String reload = FileManager.getManager().getLang().getString("config-reload");
					reload = ChatColor.translateAlternateColorCodes('&', reload);
					p.sendMessage(reload);
				}
				
			}
			
			if (args[0].equalsIgnoreCase("info")) {
				p.sendMessage("§6Plugin:- §eChatTranslatorPlus");
				p.sendMessage("§6Author:- §eAyush_03");
				p.sendMessage("§6Version:- §e" + Main.getInstance().getDescription().getVersion());
				p.sendMessage("§6Link:- §e" + "https://www.spigotmc.org/resources/chattranslatorplus.12795/");
			}
			
			
			if (args.length == 2) {
				if (!Main.langs.contains(args[0]) || !Main.langs.contains(args[1])) {
					p.sendMessage(Main.prefix + ChatColor.RED +  " Invalid Language ID!");
					p.sendMessage(ChatColor.AQUA + " For all languages' id use:- /translator languages");
					return true;
				}
				FileManager.getManager().set(p.getName() + ".translate_from", args[0].replace("greek", "el").replace("portuguese", "pt"));
				FileManager.getManager().set(p.getName() + ".translate_to", args[1].replace("greek", "el").replace("portuguese", "pt"));
				
				Main.language.put(p.getName(), Language.ENGLISH);
				
				String enabled = FileManager.getManager().getLang().getString("translator-enabled");
				enabled = ChatColor.translateAlternateColorCodes('&', enabled);
				enabled = enabled.replace("%lang_from%", args[0].toUpperCase()).replace("%lang_to%", args[1].toUpperCase());
				p.sendMessage(enabled);
			}
			}
		
		return true;
	}
}
		
		
	