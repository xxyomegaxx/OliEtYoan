package labo6.WaitBehavior;

import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public class WaitBehaviorSaySomething extends WaitBehavior {

	public WaitBehaviorSaySomething(User p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sleep() {
		try {

			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
	}

	@Override
	public void waitForUser(ChatBot bot) {
		String text = peer.getUI().getText();

		String question = TextDatabase.getAllMessages().random().getMessage();		

		sleep();
		if (peer.getUI().getText().equals(text)) {
			bot.appendMessage(question);;
		}

	}

}
