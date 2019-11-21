package labo6.WaitBehavior;

import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public class WaitBehavior1000 extends WaitBehavior {
	
	public int compteur;

	public WaitBehavior1000(User p) {
		super(p);
	}
	
	@Override
	public void sleep() {
		try {

			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}

	}

	@Override
	public void waitForUser(ChatBot bot) {
		
		String text = peer.getUI().getText();
		
		String normal = TextDatabase.getAllMessages().random().getMessage();
		
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		list.keep(TextKey.isOffensive, true);
	
		sleep();
		
		if(peer.getUI().getText().equals(text)) {
			compteur++;
			if(compteur >= 4) {
				bot.appendMessage(list.random().getMessage());
			}else {
				bot.appendMessage(normal);
			}
		}
		
	}

}
