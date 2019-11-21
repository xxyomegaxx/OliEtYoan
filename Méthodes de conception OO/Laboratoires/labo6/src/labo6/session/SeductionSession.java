package labo6.session;

import javafx.collections.ListChangeListener;
import labo6.Labo6Main;
import labo6.User;
import labo6.CheckUser.CheckBehaviorAsk;
import labo6.CheckUser.CheckBehaviorNothing;
import labo6.CheckUser.CheckUserBehavior;
import labo6.Ressources.Gender;
import labo6.WaitBehavior.WaitBehavior;
import labo6.WaitBehavior.WaitBehaviorAsk;
import labo6.WaitBehavior.WaitBehaviorNothing;
import labo6.bots.ChatBot;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.TextKey;

public class SeductionSession extends Session {

	public SeductionSession(Labo6Main l, User u, ChatBot r) {
		super(l, u, r);
	}

	@Override
	public ChatBot createChatBot() {
		CheckUserBehavior checking = createCheckBehavior();
		WaitBehavior waiting = createWaitBehavior();
		return new ChatBot(human, "other", PictureDatabase.getAllPictures().random(),Gender.random(), waiting,checking);
	}
	
	public CheckUserBehavior createCheckBehavior() {
		return new CheckBehaviorNothing(human);
	}
	
	public WaitBehavior createWaitBehavior() {
		return new WaitBehaviorNothing(human);
	}

	@Override
	public String generateAnswer(TextList li) {
		return li.random().getMessage();
	}

	@Override
	protected TextList getSuitableMessages() {
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		list.keep(TextKey.isSeductive, true);
		return list;
	}

	@Override
	protected PictureList getSuitablePictures() {
		PictureList picList = new PictureList();
		picList = PictureDatabase.getAllPictures();
		picList.keep(PictureKey.isSeductive, true);
		return picList;
	}

	@Override
	protected String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting, true);

		return li.random().getMessage();
	}
}
