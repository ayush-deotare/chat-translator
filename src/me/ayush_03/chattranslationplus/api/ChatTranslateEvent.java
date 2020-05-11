package me.ayush_03.chattranslationplus.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.rmtheis.yandtran.language.Language;

public class ChatTranslateEvent extends Event implements Cancellable {
	
	private String text_before;
	private String translated_text;
	private Player player;
	private Language default_language;
	private Language translate_language;
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	
	public ChatTranslateEvent(String original_text, String translated_text, Player player, Language default_lang, Language translated) {
		this.text_before = original_text;
		this.translated_text = translated_text;
		this.player = player;
		this.default_language = default_lang;
		this.translate_language = translated;
	}
	
	public Language getDefaultLanguage() {
		return default_language;
	}
	
	public Language getTargetLanguage() {
		return translate_language;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getTranslatedText() {
		return translated_text;
	}
	
	public String getOriginalText() {
		return text_before;
	}
	

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean flag) {
		cancelled = flag;
	}

	public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
