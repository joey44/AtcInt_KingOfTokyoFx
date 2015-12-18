package ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch;

import java.io.Serializable;


/**
 * @author joel
 *
 */

public class Chat implements Serializable{
	
	//Absender einer Nachricht
	private String absender;
	
	//Inhalt der Chat Nachricht
	private String chatNachricht;
	
	
	
	public Chat(String chatNachricht, String absender) {
		
		this.chatNachricht = chatNachricht;
		this.absender = absender;
		
		
	}
	
	
	


	public String getChatNachricht() {
		return chatNachricht;
	}


	public void setChatNachricht(String chatNachricht) {
		this.chatNachricht = chatNachricht;
	}


	public String getAbsender() {
		return absender;
	}


	public void setAbsender(String absender) {
		this.absender = absender;
	}





	@Override
	public String toString() {
		return "Chat [absender=" + absender + ", chatNachricht=" + chatNachricht + "]";
	}





	

}
