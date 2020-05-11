package me.ayush_03.chattranslationplus.api;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.rmtheis.yandtran.language.Language;

public class SignTranslateEvent extends Event {
	
	private Player player;
	private Sign sign;
	private Language target_language;
	String[] original_lines;
	String[] translated_lines;
	
	private static final HandlerList handlers = new HandlerList();
	
	public SignTranslateEvent(Player player, Sign sign, Language target_language, String[] original, String[] translated) {
		this.player = player;
		this.sign = sign;
		this.target_language = target_language;
		this.original_lines = original;
		this.translated_lines = translated;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Sign getSign() {
		return sign;
	}
	
	public Language getTargetLanguage() {
		return target_language;
	}
	
	public String[] getOriginalLines() {
		return original_lines;
	}
	
	public String[] getTranslatedLines() {
		return translated_lines;
	}

	
	public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
