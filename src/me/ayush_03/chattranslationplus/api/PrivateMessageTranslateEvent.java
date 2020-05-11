package me.ayush_03.chattranslationplus.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.rmtheis.yandtran.language.Language;

public class PrivateMessageTranslateEvent extends Event implements Cancellable {

    private CommandSender sender;
    private Player target;
    private String translated_message;
    private String original_message;
    private Language original_language;
    private Language translated_language;
    
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	
	 public PrivateMessageTranslateEvent(CommandSender message_sender, Player target_player, String original_message, String translated_message,
		Language original_language, Language translated_language) {
		 
		this.sender = message_sender;
		this.target = target_player;
		this.original_message = original_message;
		this.translated_message = translated_message;
		this.original_language = original_language;
		this.translated_language = translated_language;
		
	}
	 
	 public CommandSender getMessageSender() {
		 return sender;
	 }
	 
	 public Player getTargetPlayer() {
		 return target;
	 }
	 
	 public String getTranslatedMessage() {
		 return translated_message;
	 }
	 
	 public String getOriginalMessage() {
		 return original_message;
	 }
	 
	 public Language getOriginalLanguage() {
		 return original_language;
	 }
	 
	 public Language getTranslatedLanguage() {
		 return translated_language;
	 }
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean flag) {
		this.cancelled = true;
	}
	
	public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
