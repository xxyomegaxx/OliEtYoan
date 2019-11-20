package labo6.session;

import javafx.collections.ListChangeListener;
import labo6.Labo6Main;
import labo6.User;
import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.bots.ImpatientChatBot;
import labo6.bots.PatientChatBot;
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
		return new ImpatientChatBot(human, "other", PictureDatabase.getAllPictures().random(),Gender.random());
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
