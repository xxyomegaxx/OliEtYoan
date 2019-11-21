package labo6.CheckUser;

import labo6.User;

public class CheckBehaviorSaySomething extends CheckUserBehavior {

	public CheckBehaviorSaySomething(User u) {
		super(u);
	}

	@Override
	public Boolean checkForWakeUp(String message) {
		String last = peer.getLastLine();

		if (message.equals(last)) {
//			System.out.println(message + "   message");
//			System.out.println(last + "   last");
			return true;
		} else {
//			System.out.println(message + "   mes");
//			System.out.println(last + "   la");
			return false;
		}

	}

}
