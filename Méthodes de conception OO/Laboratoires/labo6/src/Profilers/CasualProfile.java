package Profilers;

import labo6.CheckUser.CheckSame;
import labo6.CheckUser.CheckUserBehavior;
import labo6.Labo6Main;
import labo6.Ressources.Country;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.WaitBehavior.WaitBehavior;
import labo6.WaitBehavior.WaitBehaviorNothing;
import labo6.bots.ChatBot;
import labo6.database.Picture;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.Language;
import labo6.database.TextMessage.TextKey;

public class CasualProfile extends Profiler {

	public CasualProfile(User u, Labo6Main l) {
		super(u, l);
	}

	public ChatBot createChatBot() {
		CheckUserBehavior checking = createCheckBehavior();
		WaitBehavior waiting = createWaitBehavior();
		Gender genre;

		Picture Pic = getSuitablePictures().random();

		if (Pic.match(PictureKey.gender, Gender.male)) {
			genre = Gender.male;
			return new ChatBot(peer, "other", Pic, genre, waiting, checking);
		}
		if (Pic.match(PictureKey.gender, Gender.female)) {
			genre = Gender.female;
			return new ChatBot(peer, "other", Pic, genre, waiting, checking);
		} else {
			genre = Gender.unknown;
			return new ChatBot(peer, "other", Pic, genre, waiting, checking);
		}
	}

	public CheckUserBehavior createCheckBehavior() {
		return new CheckSame(peer);
	}

	public WaitBehavior createWaitBehavior() {
		return new WaitBehaviorNothing(peer);
	}

	public TextList getSuitableMessages() {

		Country pays = peer.getCountry();
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		list.keep(TextKey.isSeductive, false);
		if (pays.equals(Country.Quebec) || pays.equals(Country.France)) {
			list.keep(TextKey.language, Language.french);
			return list;
		} else {
			list.keep(TextKey.language, Language.english);
			return list;
		}
	}

	protected PictureList getSuitablePictures() {
		PictureList picList = new PictureList();
		picList = PictureDatabase.getAllPictures();
		picList.keep(PictureKey.isSeductive, false);

		Country pays = peer.getCountry();

		if (pays.equals(Country.Japan)) {
			picList.keep(PictureKey.isComic, true);
		}

		return picList;
	}

	public String generateAnswer(TextList li) {
		return li.random().getMessage();
	}

	public String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting, true);

		return li.random().getMessage();
	}

}
