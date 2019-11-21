package Profilers;

import labo6.Labo6Main;
import labo6.User;
import labo6.CheckUser.CheckUserBehavior;
import labo6.WaitBehavior.WaitBehavior;
import labo6.bots.ChatBot;
import labo6.database.PictureList;
import labo6.database.TextList;

public abstract class Profiler {

	protected User peer;
	protected Labo6Main ui;

	public Profiler(User u, Labo6Main l) {
		peer = u;
		ui = l;
	}

	public abstract ChatBot createChatBot();

	protected abstract CheckUserBehavior createCheckBehavior();

	protected abstract WaitBehavior createWaitBehavior();

	public abstract String generateAnswer(TextList li);

	public abstract TextList getSuitableMessages();

	protected abstract PictureList getSuitablePictures();

	public abstract String generateGreeting(TextList li);
	
}
