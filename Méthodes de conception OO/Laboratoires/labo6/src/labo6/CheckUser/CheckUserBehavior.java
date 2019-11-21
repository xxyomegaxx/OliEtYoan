package labo6.CheckUser;

import labo6.User;

public abstract class CheckUserBehavior {
	
	protected User peer;
	
	public CheckUserBehavior(User u) {
		peer = u;
	}
	
	public abstract Boolean checkForWakeUp(String message);
}
