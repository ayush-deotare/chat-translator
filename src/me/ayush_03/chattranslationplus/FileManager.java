package me.ayush_03.chattranslationplus;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class FileManager {
	
	File database;
	FileConfiguration config;
	
	File lang;
	FileConfiguration langc;
	
	Plugin p;
	
	private FileManager() { }
	
	private static FileManager instance = new FileManager();
	
	public static FileManager getManager() {
		return instance;
	}
	
	public void setup(Plugin p) {
		this.p = p;
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		this.database = new File(p.getDataFolder() + File.separator + "database.yml");
		this.lang = new File(p.getDataFolder() + File.separator + "lang.yml");


	    if (!database.exists()) {
			try { database.createNewFile(); 
			}
			catch (Exception e) { e.printStackTrace(); }
		
		}
	    
	    if (!lang.exists()) {
			try { lang.createNewFile(); 
			}
			catch (Exception e) { e.printStackTrace(); }
		
		}
	    
	    langc = YamlConfiguration.loadConfiguration(lang);
	    config = YamlConfiguration.loadConfiguration(database);
	}
	

	public void set(String path, Object value) {
		config.set(path, value);
		save();
	}
	
	public void save() {
		try {
			config.save(database);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public FileConfiguration getDatabase() {
		return config;
	}
	
	public FileConfiguration getLang() {
		return langc;
	}
	

}
