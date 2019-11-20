package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.database.Picture;
import labo6.database.PictureDatabase;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public class ImpatientChatBot extends ChatBot {

	public ImpatientChatBot(User p, String n, Picture pic, Gender g) {
		super(p, n, pic, g);
	}

	@Override
	public Boolean checkForWakeUp(String message) {
		return true;
	}
	
	
	
	

	@Override
	public void sleep() {
		
	
		
		try {
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
	}

	@Override
	public void waitForUser() {
		String text = peer.getUI().getText();
		sleep();
		
		TextList questions = new TextList();
		questions = TextDatabase.getAllMessages();
		
		if(peer.getUI().getText().equals(text)) {
			String theQuestion = questions.random().getMessage();
			appendMessage(theQuestion);
		}
		
	}
	
	

}
