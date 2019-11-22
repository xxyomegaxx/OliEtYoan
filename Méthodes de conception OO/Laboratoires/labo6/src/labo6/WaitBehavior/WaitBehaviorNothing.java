package labo6.WaitBehavior;

import labo6.User;
import labo6.bots.ChatBot;

public class WaitBehaviorNothing extends WaitBehavior {
	public WaitBehaviorNothing(User p) {
		super(p);
	}

	@Override
	public void sleep() {
		try {

			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}

	}

	@Override
	public void waitForUser(ChatBot bot) {
		this.sleep();
	}
}
