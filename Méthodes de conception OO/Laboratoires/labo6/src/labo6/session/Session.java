package labo6.session;

import java.util.ArrayList;

import Profilers.NormalProfile;
import Profilers.Profiler;
import labo6.Labo6Main;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.CheckUser.CheckQuestion;
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
	protected ChatBot chatbot;
	protected Profiler profile;
	protected Labo6Main ui;
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
		
	}

	public final void start() {

		profile = createProfiler();
		
		chatbot = profile.createChatBot();

		ui.initBackGround(chatbot);

		// Creer une liste approprie pour la session instanciee
		TextList suitableMsg = profile.getSuitableMessages();

		// Genere un message de bienvenue
		String helloMsg = profile.generateGreeting(suitableMsg.clone());

		chatbot.appendMessage(helloMsg);

		String oldText = human.getUI().getText();

		while (!hasEnded()) {

			chatbot.waitForUser(chatbot);

			if (!human.getUI().getText().equals(oldText)) {

				if (chatbot.checkForWakeUp(oldText)) {

					String message = profile.generateAnswer(suitableMsg.clone());
					chatbot.appendMessage(message);

				}

				oldText = human.getUI().getText();// Prend le vieux texte
			}

		}

	}

	/*
	 * Fonction qui permet au creator Labo6Main de creer une sessions sans savoir
	 * son type.
	 */
	public static Session createSession(String type, Labo6Main ui, User humanUser) {

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

	public Profiler createProfiler() {
		return new NormalProfile(human,ui);
	}

	/*
	 * Appel� par le bouton SUIVANT
	 */
	public void changeChatBot() {
		// CA MARCHERA PAS
		chatbot = profile.createChatBot();
		ui.initBackGround(chatbot);
	}

	public synchronized void end() {
		ended = true;
		sleeper.interrupt();
	}

	private synchronized boolean hasEnded() {
		return ended;
	}

}
