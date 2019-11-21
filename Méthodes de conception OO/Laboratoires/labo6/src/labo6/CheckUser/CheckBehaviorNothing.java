package labo6.CheckUser;

import labo6.User;

public class CheckBehaviorNothing extends CheckUserBehavior {

	public CheckBehaviorNothing(User u) {
		super(u);
	}

	@Override
	public Boolean checkForWakeUp(String message) {
		
		
		return true;

	}

}
