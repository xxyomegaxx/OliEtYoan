package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;

public class PatientChatBot extends ChatBot {

	public PatientChatBot(User p, String n, Picture pic, Gender g) {
		super(p, n, pic, g);
	}
	
	@Override
	public Boolean checkForWakeUp(String message) {
		
		if(message.indexOf("?") >= 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void sleep() {
		try {

			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
	}
	
	

}
