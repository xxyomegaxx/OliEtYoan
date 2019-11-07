package labo5.celebrities;

public abstract class SocialMediaCelebrity {

	private int waitTime;
	protected String name;
	protected String status = "I'm new to Facetwit, lol.";

	public SocialMediaCelebrity(String n, int wait) {
		name = n;
		waitTime = wait * 1000;

	}

	public abstract void changeStatus();

	protected void setStatus(String ns) {
		waitBeforePost();
		status = ns;

	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	private void waitBeforePost() {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
		}
	}

}
