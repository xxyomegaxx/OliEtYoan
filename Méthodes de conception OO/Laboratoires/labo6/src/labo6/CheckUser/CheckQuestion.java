package labo6.CheckUser;

import labo6.User;

public class CheckQuestion extends CheckUserBehavior{

	public CheckQuestion(User u) {
		super(u);
	}

	@Override
	public Boolean checkForWakeUp(String message) {
		if (message.indexOf("?") >= 1) {
			return true;
		} else {
			return false;
		}
		
	}

}
