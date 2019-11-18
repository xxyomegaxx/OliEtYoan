package labo6.session;

import java.util.ArrayList;

import labo6.CasualSession;
import labo6.Labo6Main;
import labo6.SeductionSession;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage.TextKey;

/*
 * Cette classe repr�sente une session d'un utilisateur humain
 * avec un ou plusieurs robots.
 * La session se termine lorsqu'on d�tecte que l'utilisateur humain
 * s'est d�connect� (change de pays ou de genre, via les boutons "PAYS" et "GENRE").
 */

public class Session {

	protected User human;
	private ChatBot robot;
	private Labo6Main ui;
	private boolean ended;
	private Thread sleeper;

	public final static String NORMAL_SESSION = "normal";
	public final static String SEDUCTION_SESSION = "seduction";
	public final static String CASUAL_SESSION = "CasualSession";

	public Session(Labo6Main l, User u) {
		ui = l;
		human = u;
		ended = false;
		sleeper = Thread.currentThread();
//		initStudentList();
	}

//	protected void setTextList(TextList li){
//		list=li;
//	}

//	protected void initStudentList() {
//		setTextList(TextDatabase.getAllMessages());
//	}

	protected String generateAnswer(TextList li) {

		return li.random().getMessage();
	}

	protected String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting, true);

		return li.random().getMessage();
	}

	protected TextList getSuitableMessages() {
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		return list;
	}

	protected PictureList getSuitablePictures() {
		PictureList picList = new PictureList();
		picList = PictureDatabase.getAllPictures();
		return picList;
	}

	public final void start() {

		robot = new ChatBot(human, "Other", PictureDatabase.getAllPictures().random(), Gender.random());
		ui.initBackGround(robot);

		TextList suitableMsg = getSuitableMessages();
		String helloMsg = generateGreeting(suitableMsg.clone());
		robot.appendMessage(helloMsg);
		String oldText = human.getUI().getText();
		while (!hasEnded()) {

			robot.sleep(2000);

			if (!human.getUI().getText().equals(oldText)) {

				String message = generateAnswer(suitableMsg.clone());
				robot.appendMessage(message);

				oldText = human.getUI().getText();
			}

		}

	}
	
	/*
	 * Fonction qui permet au creator Labo6Main de creer une sessions sans savoir son type.
	 */
	public static Session createSession(String type, Labo6Main ui, User humanUser) {
		while (true) {

			switch (type) {
			case NORMAL_SESSION:
				return new Session(ui, humanUser);
			case SEDUCTION_SESSION:
				return new SeductionSession(ui, humanUser);
			case CASUAL_SESSION:
				return new CasualSession(ui, humanUser);
			default:
				throw new IllegalArgumentException("Wrong session type: " + type);
			}
		}

	}

	/*
	 * Appel� par le bouton SUIVANT
	 */
	public void changeChatBot() {
		robot = new ChatBot(human, "Other", PictureDatabase.getAllPictures().random(), Gender.random());
		ui.initBackGround(robot);
	}

	public synchronized void end() {
		ended = true;
		sleeper.interrupt();
	}

	private synchronized boolean hasEnded() {
		return ended;
	}

}
