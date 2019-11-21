package labo6.session;

import javafx.collections.ListChangeListener;
import labo6.Labo6Main;
import labo6.User;
import labo6.CheckUser.CheckQuestion;
import labo6.CheckUser.CheckAnything;
import labo6.CheckUser.CheckUserBehavior;
import labo6.Ressources.Gender;
import labo6.WaitBehavior.WaitBehavior;
import labo6.WaitBehavior.WaitBehaviorAsk;
import labo6.WaitBehavior.WaitBehaviorNothing;
import labo6.WaitBehavior.WaitBehaviorSaySomething;
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

}
