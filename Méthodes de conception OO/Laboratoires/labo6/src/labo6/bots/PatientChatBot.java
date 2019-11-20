package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

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

			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		
	}

	@Override
	public void waitForUser() {
		
		String text = peer.getUI().getText();
		
		TextList questions = new TextList();
		questions = TextDatabase.getAllMessages();
		questions.keep(TextKey.isQuestion, true);
		
		sleep();
		if(peer.getUI().getText().equals(text)) {
			String theQuestion = questions.random().getMessage();
			appendMessage(theQuestion);
		}
		
	}
	
	
	
	

}
