package labo6.session;

import labo6.Labo6Main;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.PictureDatabase;


/*
 * Cette classe représente une session d'un utilisateur humain
 * avec un ou plusieurs robots.
 * La session se termine lorsqu'on détecte que l'utilisateur humain
 * s'est déconnecté (change de pays ou de genre, via les boutons "PAYS" et "GENRE").
 */

public class Session {

	private User human;
	private ChatBot robot;
	private Labo6Main ui;
	private boolean ended;
	private Thread sleeper;

	public Session(Labo6Main l, User u) {
		ui = l;
		human = u;
		ended = false;
		sleeper = Thread.currentThread();
	}

	public void start() {

		robot = new ChatBot(human, "Other", PictureDatabase.getAllPictures().random(), Gender.random());
		ui.initBackGround(robot);
		
		robot.appendMessage("Hello there!");
		String oldText = human.getUI().getText();
		while (!hasEnded()) {

			robot.sleep(2000);

			if (!human.getUI().getText().equals(oldText)) {

				robot.appendMessage("lol");
				oldText = human.getUI().getText();
			}

		}

	}
	
	/*
	 * Appelé par le bouton SUIVANT
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
