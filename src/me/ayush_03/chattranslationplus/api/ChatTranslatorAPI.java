package me.ayush_03.chattranslationplus.api;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

import me.ayush_03.chattranslationplus.Main;

public class ChatTranslatorAPI  {
	
	Plugin plugin = Main.getInstance(); // Instance of Main class.
	
	public ChatTranslatorAPI() { } // Constructor without any parameters.
	
	/**
	 * Returns the key of the Yandex API.
	 */
	
	public String getKey() {
		
		Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("ChatTranslationPlus");
		return pl.getConfig().getString("translator-key");
	}
	  
	  /**
	   * Translate a string.
	   */
	
		  public String translate(Language from, Language to, String text) {
			
			  try {
				  Translate.setKey(getKey());
				 String t = Translate.execute(text, from, to);
				 return t;
			  } catch (Exception e) {
				  System.out.println("Invalid translation key in plugin's config!");
			  }
			  
			  return null;
		  }
		  
		  /**
		   * Returns all players who are in translator mode.
		   */
		  
		  public Set<String> getTranslatorUsers() {
			 
			  return Main.language.keySet();
		  }
		  
		  /**
		   * Checks if player is in translator mode.
		   */
		  
		  public boolean isInTranslator(Player p) {
			
			  return Main.language.containsKey(p.getName());
		  }
		  
		  /**
		   * Returns the language which players want to translate its language.
		   */
		  
		  public Language getPlayerTargetLanguage(Player p) throws NullPointerException {
			  
			  Language lang = Language.fromString(plugin.getConfig().getString(p.getName() + ".translate_to"));
			  return lang;
		  }
		  
		  /**
		   * Returns the language from which player wants to translate.
		   */
		  
		  public Language getPlayerLanguage(Player p) throws NullPointerException {
			 
			  Language lang = Language.fromString(plugin.getConfig().getString(p.getName() + ".translate_from"));
			  return lang;
		  }
		  
		  /** 
		   * Checks if player has auto self translator enabled.
		   */
		  
		  public boolean hasSelfTranslator(Player p) {
			  return Main.auto.containsKey(p.getName());
		  }
		  
		  /**
		   * Gets the selected language of a player in auto self translator, if enabled.
		   */
		  
		  public Language getSelfTrasnlatorLanguage(Player p) throws NullPointerException {
			  return Main.auto.get(p.getName());
		  }
		  
		  /**
		   * Checks if server has default language enabled.
		   */
		  
		  public boolean enabledDefaultLanguage() {
			  return Main.getInstance().getConfig().getBoolean("enable-default-language");
		  }
		  
		  /**
		   * Returns the default language of the server, if enabled.
		   */
		  
		  public Language getServerDefaultLanguage() {
			  Language lang = Language.fromString(Main.getInstance().getConfig().getString("default-language"));
			  return lang;
		  }
}
