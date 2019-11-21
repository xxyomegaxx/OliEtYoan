package labo6.session;

import java.util.ArrayList;

import labo6.Labo6Main;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.CheckUser.CheckBehaviorAsk;
import labo6.CheckUser.CheckUserBehavior;
import labo6.WaitBehavior.WaitBehavior;
import labo6.WaitBehavior.WaitBehaviorAsk;
import labo6.bots.ChatBot;
import labo6.database.Picture;
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
	protected ChatBot robot;
	private Labo6Main ui;
	private boolean ended;
	private Thread sleeper;

	public final static String NORMAL_SESSION = "normal";
	public final static String SEDUCTION_SESSION = "seduction";
	public final static String CASUAL_SESSION = "CasualSession";

	public Session(Labo6Main l, User u, ChatBot r) {
		ui = l;
		human = u;
		ended = false;
		sleeper = Thread.currentThread();
		robot = createChatBot();
	}

	public final void start() {
		
		ui.initBackGround(robot);

		// Creer une liste approprie pour la session instanciee
		TextList suitableMsg = getSuitableMessages();
		// Genere un message de bienvenue
		String helloMsg = generateGreeting(suitableMsg.clone());

		String oldText = human.getUI().getText();
		
		while (!hasEnded()) {
		
			robot.getWaitBehavior().waitForUser(robot);
			
			if (!human.getUI().getText().equals(oldText)) {

				if (robot.getCheckUserBehavior().checkForWakeUp(oldText)) {
					
//					if(robot.getLastLine() == null) {
						robot.appendMessage(helloMsg);
//					}

					String message = generateAnswer(suitableMsg.clone());
					robot.appendMessage(message);

				}
			
				oldText = human.getUI().getText();// Prend le vieux texte
			}

		}

	}

	/*
	 * Fonction qui permet au creator Labo6Main de creer une sessions sans savoir
	 * son type.
	 */
	public static Session createSession(String type, Labo6Main ui, User humanUser, ChatBot rob) {

			switch (type) {
			case NORMAL_SESSION:
				return new Session(ui, humanUser, rob);
			case SEDUCTION_SESSION:
				return new SeductionSession(ui, humanUser, rob);
			case CASUAL_SESSION:
				return new CasualSession(ui, humanUser, rob);
			default:
				throw new IllegalArgumentException("Wrong session type: " + type);
			}
	}

	public ChatBot createChatBot() {
		CheckUserBehavior checking = createCheckBehavior();
		WaitBehavior waiting = createWaitBehavior();
		return new ChatBot(human, "other", PictureDatabase.getAllPictures().random(), Gender.random(), waiting,
				checking);
	}

	public CheckUserBehavior createCheckBehavior() {
		return new CheckBehaviorAsk(human);
	}

	public WaitBehavior createWaitBehavior() {
		return new WaitBehaviorAsk(human);
	}

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

	/*
	 * Appel� par le bouton SUIVANT
	 */
	public void changeChatBot() {
		// CA MARCHERA PAS
		robot = createChatBot();
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
