package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;
import labo6.database.PictureDatabase;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public abstract class ChatBot extends User {

	//L'utilisateur avec lequel le robot est en communication.
	protected User peer;

	public ChatBot(User p, String n, Picture pic, Gender g) {
		super(n, pic, g);
		peer = p;
	}

	public abstract void sleep();
	
	public void appendMessage(String msg){
		getUI().appendMessage(msg);
	}
	
	public User getPeer(){
		return peer;
	}
	
	public abstract Boolean checkForWakeUp(String message);
	
}
