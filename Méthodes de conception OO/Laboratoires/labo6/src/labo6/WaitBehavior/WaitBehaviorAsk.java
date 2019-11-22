package labo6.WaitBehavior;

import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public class WaitBehaviorAsk extends WaitBehavior {

	public WaitBehaviorAsk(User p) {
		super(p);
	}

	@Override
	public void sleep() {
		try {

			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void waitForUser(ChatBot bot) {

		String text = peer.getUI().getText();

		TextList questions = new TextList();
		questions = TextDatabase.getAllMessages();
		questions.keep(TextKey.isQuestion, true);
		String theQuestion = questions.random().getMessage();

		sleep();
		if (peer.getUI().getText().equals(text)) {
			bot.appendMessage(theQuestion);
		}

	}

}
