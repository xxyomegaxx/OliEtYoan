package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;

public class ChatBot extends User {

	//L'utilisateur avec lequel le robot est en communication.
	private User peer;

	public ChatBot(User p, String n, Picture pic, Gender g) {
		super(n, pic, g);
		peer = p;
	}

	public void sleep(int time) {
		try {

			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}
	
	public void appendMessage(String msg){
		getUI().appendMessage(msg);
	}
	
	public User getPeer(){
		return peer;
	}

	
}
