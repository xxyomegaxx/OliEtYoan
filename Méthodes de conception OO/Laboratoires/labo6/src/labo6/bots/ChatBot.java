package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.CheckUser.CheckUserBehavior;
import labo6.WaitBehavior.WaitBehavior;
import labo6.database.Picture;

public class ChatBot extends User {
	
	private WaitBehavior waiting;
	private CheckUserBehavior checking;

	// L'utilisateur avec lequel le robot est en communication.
	protected User peer;

	public ChatBot(User p, String n, Picture pic, Gender g, WaitBehavior w,CheckUserBehavior c) {
		super(n, pic, g);
		peer = p;
		waiting = w;
		checking = c;
	}

	public void appendMessage(String msg) {
		getUI().appendMessage(msg);
	}

	public User getPeer() {
		return peer;
	}
	
	public WaitBehavior getWaitBehavior() {
		return waiting;
	}
	public CheckUserBehavior getCheckUserBehavior() {
		return checking;
	}
	
	public boolean checkForWakeUp(String oldText) {
	
		return checking.checkForWakeUp(oldText);
	}

	public void waitForUser(ChatBot robot) {
		waiting.waitForUser(robot);
	}

}
