package Profilers;

import labo6.CheckUser.CheckQuestion;
import labo6.CheckUser.CheckUserBehavior;
import labo6.Ressources.Gender;
import labo6.Labo6Main;
import labo6.Ressources;
import labo6.User;
import labo6.WaitBehavior.WaitBehavior;
import labo6.WaitBehavior.WaitBehaviorAsk;
import labo6.bots.ChatBot;
import labo6.database.Picture;
import labo6.database.Picture.PictureKey;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

public class NormalProfile extends Profiler {

	public NormalProfile(User u, Labo6Main l) {
		super(u,l);
	}

	public ChatBot createChatBot() {
		CheckUserBehavior checking = createCheckBehavior();
		WaitBehavior waiting = createWaitBehavior();
		Gender genre;
		
		System.out.println();
		
		Picture Pic = PictureDatabase.getAllPictures().random();

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
		return new CheckQuestion(peer);
	}

	public WaitBehavior createWaitBehavior() {
		return new WaitBehaviorAsk(peer);
	}

	public String generateAnswer(TextList li) {

		return li.random().getMessage();
	}

	public String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting, true);

		return li.random().getMessage();
	}

	public TextList getSuitableMessages() {
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
