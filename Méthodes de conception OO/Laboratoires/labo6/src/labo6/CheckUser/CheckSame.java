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
		
		
		oldText = peer.getUI().getText();
		oldLine = peer.getLastLine();

		if (message.equals(oldText)) {
//			System.out.println(message + "   message");
//			System.out.println(last + "   last");
			oldText = message;
			return true;
		} else {
//			System.out.println(message + "   mes");
//			System.out.println(last + "   la");
			oldText = message;
			return false;
		}

	}

}
