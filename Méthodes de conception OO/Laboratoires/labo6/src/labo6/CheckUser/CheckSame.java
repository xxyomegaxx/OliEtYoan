package labo6.CheckUser;

import labo6.User;

public class CheckSame extends CheckUserBehavior {
	
	private String oldText;
	private String oldLine;
	
	public CheckSame(User u) {
		super(u);
	}

	@Override
	public Boolean checkForWakeUp(String message) {
		return peer.CheckTwoLastLines();

	}

}
