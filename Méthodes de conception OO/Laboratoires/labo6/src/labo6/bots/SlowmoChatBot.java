package labo6.bots;

import labo6.User;
import labo6.Ressources.Gender;
import labo6.database.Picture;
import labo6.database.PictureDatabase;

public class SlowmoChatBot extends ChatBot {
	
	public SlowmoChatBot(User p, String n, Picture pic, Gender g) {
		super(p, n, pic, g);
	}

	@Override
	public Boolean checkForWakeUp(String message) {
		
		String last = peer.getLastLine();
		
		
		if(message.equals(last)) {
//			System.out.println(message + "   message");
//			System.out.println(last + "   last");
			return true;
		}
		else {
//			System.out.println(message + "   mes");
//			System.out.println(last + "   la");
			return false;
		}
	}
	
	@Override
	public void sleep() {
		try {

			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		
	}
}
