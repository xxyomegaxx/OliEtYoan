package labo6.WaitBehavior;

import labo6.User;
import labo6.bots.ChatBot;

public abstract class WaitBehavior {
	
	protected User peer;
	
	public WaitBehavior(User p) {
		peer = p;
	}
	
	public abstract void sleep();
	
	public abstract void waitForUser(ChatBot bot);
}
