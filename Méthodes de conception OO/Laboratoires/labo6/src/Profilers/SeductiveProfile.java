package Profilers;

import labo6.CheckUser.CheckAnything;
import labo6.CheckUser.CheckUserBehavior;
import labo6.Labo6Main;
import labo6.Ressources.Country;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.WaitBehavior.WaitBehavior;
import labo6.WaitBehavior.WaitBehaviorSaySomething;
import labo6.bots.ChatBot;
import labo6.database.Picture;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.Language;
import labo6.database.TextMessage.TextKey;

public class SeductiveProfile extends Profiler {

	public SeductiveProfile(User u, Labo6Main l) {
		super(u, l);
	}

	public ChatBot createChatBot() {
		CheckUserBehavior checking = createCheckBehavior();
		WaitBehavior waiting = createWaitBehavior();
		Gender genre;
		
		PictureList Pic = getSuitablePictures();
		
		if(ui.getUserGender().equals(Gender.male) ) {
			Pic.keep(PictureKey.gender, Gender.female);
			genre = Gender.female;
			return new ChatBot(peer, "other", Pic.random(), genre, waiting, checking);
		}
		if(ui.getUserGender().equals(Gender.female)) {
			Pic.keep(PictureKey.gender, Gender.male);
			genre = Gender.male;
			return new ChatBot(peer, "other", Pic.random(), genre, waiting, checking);
		}
		else {
			Pic.keep(PictureKey.gender, Gender.unknown);
			genre = Gender.unknown;
			return new ChatBot(peer, "other", Pic.random(), genre, waiting, checking);
		}
	}

	public CheckUserBehavior createCheckBehavior() {
		return new CheckAnything(peer);
	}

	public WaitBehavior createWaitBehavior() {
		return new WaitBehaviorSaySomething(peer);
	}

	public String generateAnswer(TextList li) {
		return li.random().getMessage();
	}

	public TextList getSuitableMessages() {
		
		Country pays = peer.getCountry();
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		list.keep(TextKey.isSeductive, true);
		
		if(pays.equals(Country.Quebec)|| pays.equals(Country.France)) {
			list.keep(TextKey.language, Language.french);
			return list;
		}
		else {
			list.keep(TextKey.language, Language.english);
			return list;
		}
	}

	protected PictureList getSuitablePictures() {
		PictureList picList = new PictureList();
		picList = PictureDatabase.getAllPictures();
		picList.keep(PictureKey.isSeductive, true);
		
		Country pays = peer.getCountry();
		
		if(pays.equals(Country.Japan)) {
			picList.keep(PictureKey.isComic, true);
		}
		
		return picList;
	}

	public String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting, true);

		return li.random().getMessage();
	}

}
