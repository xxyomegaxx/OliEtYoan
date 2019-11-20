package labo6.session;

import labo6.database.Picture.PictureKey;
import labo6.Labo6Main;
import labo6.User;
import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.bots.ImpatientChatBot;
import labo6.bots.SlowmoChatBot;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public class CasualSession extends Session {

	public CasualSession(Labo6Main l, User u, ChatBot r) {
		super(l, u, r);
	}
	
	@Override
	public ChatBot createChatBot() {
		return new SlowmoChatBot(human, "other", PictureDatabase.getAllPictures().random(), Gender.random());
	}

	@Override
	protected TextList getSuitableMessages() {
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		list.keep(TextKey.isSeductive, false);
		return list;
	}

	@Override
	protected PictureList getSuitablePictures() {
		PictureList picList = new PictureList();
		picList = PictureDatabase.getAllPictures();
		picList.keep(PictureKey.isSeductive, false);
		return picList;
	}

	@Override
	protected String generateAnswer(TextList li) {
		return li.random().getMessage();
	}

	@Override
	protected String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting, true);

		return li.random().getMessage();
	}

}
