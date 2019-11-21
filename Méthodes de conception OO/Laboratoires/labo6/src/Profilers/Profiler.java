package Profilers;

import labo6.CheckUser.CheckUserBehavior;
import labo6.WaitBehavior.WaitBehavior;
import labo6.bots.ChatBot;
import labo6.database.PictureList;
import labo6.database.TextList;

public abstract class Profiler {
	

	public abstract ChatBot createChatBot();
	
	public abstract CheckUserBehavior createCheckBehavior();
	
	public abstract WaitBehavior createWaitBehavior();

	public abstract String generateAnswer(TextList li);

	protected abstract TextList getSuitableMessages();

	protected abstract PictureList getSuitablePictures();

	protected abstract String generateGreeting(TextList li);

}
