package me.ayush_03.chattranslationplus;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.rmtheis.yandtran.language.Language;

import me.ayush_03.chattranslationplus.cmds.Cmd;
import me.ayush_03.chattranslationplus.cmds.LanguageCMD;
import me.ayush_03.chattranslationplus.cmds.PMCmd;
import me.ayush_03.chattranslationplus.cmds.Translate_Cmd;


public class Main extends JavaPlugin {
	
	public static HashMap<String, Language> language = new HashMap<String, Language>();
	public static ArrayList<String> langs = new ArrayList<String>();
	public static ArrayList<String> guide = new ArrayList<String>();
	public static final String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "ChatTranslatorPlus" + ChatColor.GRAY + "]";
	public static HashMap<String, Language> auto = new HashMap<String, Language>();
	public static ArrayList<Player> remove = new ArrayList<Player>();
	
	 String ip = getConfig().getString("host");
	 String port = getConfig().getString("port");
	 String db = getConfig().getString("database-name");
	
	final String DB_NAME = "jdbc:mysql://" + ip + ":" + port + "/" + db;
	final String USER = getConfig().getString("username");
	final String PASS = getConfig().getString("password");

	public static Connection connection;
	
	public void onEnable() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_NAME,USER,PASS);
            getServer().getConsoleSender().sendMessage("§aSuccessfully connected to sql server!");
        } catch (Exception e) {
        	getServer().getConsoleSender().sendMessage("§cCould connect to sql server!");
        }
		addLangs();
		getCommand("translator").setExecutor(new Cmd(this));
		getCommand("translate").setExecutor(new Translate_Cmd(this));
		getCommand("language").setExecutor(new LanguageCMD());
		getCommand("chat").setExecutor(new PMCmd());
		getServer().getPluginManager().registerEvents(new TalkEvent(this), this);
		getServer().getPluginManager().registerEvents(new SignTranslator(), this);
		saveDefaultConfig();
		saveDefaultLang();
		FileManager.getManager().setup(this);
		
		
		enabled();
	}
	
	public void onDisable() {
		
		disabled();
	}
	
	public void addLangs() {
		
		langs.add("afrikaans");
		langs.add("albanian");
		langs.add("arabic"); 
		langs.add("armenian");
		langs.add("azerbaijani");
		langs.add("belarustan");
		langs.add("bulgarian");
		langs.add("catalan");
		langs.add("chinese");
		langs.add("croatian");
		langs.add("czech");
		langs.add("danish");
		langs.add("dutch");
		langs.add("english");
		langs.add("estonian");
		langs.add("finnish");
		langs.add("french");
		langs.add("georgian");
		langs.add("german");
		langs.add("greek");
		langs.add("hebrew");
		langs.add("icelandic");
		langs.add("hindi");
		langs.add("hungarian");
		langs.add("indonesian"); 
		langs.add("italian");
		langs.add("japanese");
		langs.add("kazakh"); 
		langs.add("korean");
		langs.add("latin");
		langs.add("latvian");
		langs.add("lithuanian");
		langs.add("macedonian");
		langs.add("malay");
		langs.add("maltese"); // NEW
		langs.add("norwegian");
		langs.add("polish");
		langs.add("portuguese");
		langs.add("romanian");
		langs.add("russian");
		langs.add("serbian");
		langs.add("slovak");
		langs.add("slovenian");
		langs.add("spanish");
		langs.add("swedish");
		langs.add("tagalog");
		langs.add("thai");
		langs.add("turkish");
		langs.add("ukrainian");
		langs.add("urdu"); 
		langs.add("uzbek"); 
		langs.add("vietnamese");
		langs.add("welsh");

	}
	
	public static Plugin getInstance() {
		Plugin p = Bukkit.getServer().getPluginManager().getPlugin("ChatTranslatorPlus");
		return p;
	}
	
	public boolean allowEssentials() {
		return getConfig().getBoolean("translate-essentials-pm");
	}
	
	public boolean keepTranslator() {
		return getConfig().getBoolean("keep-translator");
	}
	
	public boolean enabledDefaultLanguage() {
		return getConfig().getBoolean("enable-default-language");
	}
	
	public void enabled() {
		getServer().getConsoleSender().sendMessage(prefix + " §aChatTranslatorPlus v" + getDescription().getVersion() + " has been enabled.");
	}
	
	public void disabled() {
		
		getServer().getConsoleSender().sendMessage(prefix + " §cChatTranslatorPlus v" + getDescription().getVersion() + " has been disabled.");

		
	}
	
	public void saveDefaultLang() {
	    if (FileManager.getManager().lang == null) {
	    	FileManager.getManager().lang = new File(getDataFolder(), "lang.yml");
	    }
	    if (!FileManager.getManager().lang.exists()) {            
	         saveResource("lang.yml", false);
	     }
	}
	
	public void update(Player p, Language lang) {
		try {
			PreparedStatement statement = Main.connection.prepareStatement("insert into chat_backup (player, language)\nvalues ('" + p.getName() + "', '" + lang.toString() + "');");
			 statement.executeUpdate();
             statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
