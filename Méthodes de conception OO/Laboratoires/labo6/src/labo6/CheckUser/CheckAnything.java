package labo6.CheckUser;

import labo6.User;

public class CheckAnything extends CheckUserBehavior {

	public CheckAnything(User u) {
		super(u);
	}

	@Override
	public Boolean checkForWakeUp(String message) {
		return true;

	}

}
