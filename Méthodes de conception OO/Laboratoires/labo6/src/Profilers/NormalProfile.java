package Profilers;

import labo6.CheckUser.CheckQuestion;
import labo6.CheckUser.CheckUserBehavior;
import labo6.Ressources.Gender;
import labo6.WaitBehavior.WaitBehavior;
import labo6.WaitBehavior.WaitBehaviorAsk;
import labo6.bots.ChatBot;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public class NormalProfile extends Profiler {
	
	public ChatBot createChatBot() {
		CheckUserBehavior checking = createCheckBehavior();
		WaitBehavior waiting = createWaitBehavior();
		return new ChatBot(human, "other", PictureDatabase.getAllPictures().random(), Gender.random(), waiting,
				checking);
	}
	
	public CheckUserBehavior createCheckBehavior() {
		return new CheckQuestion(human);
	}
	
	public WaitBehavior createWaitBehavior() {
		return new WaitBehaviorAsk(human);
	}

	protected String generateAnswer(TextList li) {

		return li.random().getMessage();
	}

	protected String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting, true);

		return li.random().getMessage();
	}

	protected TextList getSuitableMessages() {
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		return list;
	}

	protected PictureList getSuitablePictures() {
		PictureList picList = new PictureList();
		picList = PictureDatabase.getAllPictures();
		return picList;
	}

}
