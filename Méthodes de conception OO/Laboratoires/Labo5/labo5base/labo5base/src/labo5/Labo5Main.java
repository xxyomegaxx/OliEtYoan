package labo5;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import labo5.celebrities.BroScienceCelebrity;
import labo5.celebrities.DramaticCelebrity;
import labo5.celebrities.PositiveCelebrity;
import labo5.celebrities.SocialMediaCelebrity;
import labo5.followers.Fanboy;
import labo5.followers.Hater;
import labo5.followers.MessageLog;
import labo5.followers.PressAgent;
import labo5.followers.Reposter;
import labo5.followers.SerialReposter;
import labo5.followers.Troll;
import labo5.ui.MessageView;

public class Labo5Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private ArrayList<MessageView> views = new ArrayList<MessageView>();

	private ArrayList<SocialMediaCelebrity> celebs = new ArrayList<SocialMediaCelebrity>();
	private ArrayList<Reposter> fans = new ArrayList<Reposter>();
	private MessageLog log = new MessageLog();

	/*
	 * Initialisation des Celebrities et des Followers. �tablissement des liens
	 * entre eux.
	 */

	public Labo5Main(String[] args) {

		DramaticCelebrity dramaQueen = new DramaticCelebrity("BritneySpears", 2);
		PositiveCelebrity spiritualKing = new PositiveCelebrity("LookWithin", 3);
		BroScienceCelebrity superBro = new BroScienceCelebrity("JohnCena", 4);
		celebs.add(dramaQueen);
		celebs.add(spiritualKing);
		celebs.add(superBro);

		fans.add(new Reposter(this, "BritneyFan"));
		fans.add(new Reposter(this, "SpiritualFan"));
		fans.add(new Reposter(this, "JohnCenaFan"));
		Troll troll1 = new Troll (this,"The troll1",1);
		Troll troll2 = new Troll (this,"The troll2",2);
		SerialReposter nolife = new SerialReposter(this, "NoLife");
		Fanboy fanboy = new Fanboy(this,"The Fanboy");
		Hater hater = new Hater(this,"The Hater");
		PressAgent agent1 = new PressAgent("agent1",1);
		PressAgent agent2 = new PressAgent("agent2",2);
		

		for (int i = 0; i < celebs.size(); i++) {
			celebs.get(i).attach(fans.get(i));
			celebs.get(i).attach(log);
			celebs.get(i).attach(nolife);
			celebs.get(i).attach(troll1);
			celebs.get(i).attach(troll2);
			celebs.get(i).attachVeto(agent1);
			celebs.get(i).attachVeto(agent2);
}
		celebs.get(2).attach(fanboy);
		Reposter fanboyFan = new Reposter(this, "Fanboy Fan");
		fanboy.attach(fanboyFan);
		fanboy.attach(troll1);
		fanboy.attach(troll2);

		
		celebs.get(0).attach(hater);
		Reposter haterFan = new Reposter(this, "Hater Fan");
		hater.attach(haterFan);
		hater.attach(troll1);
		hater.attach(troll2);

	}

	/*
	 * Boucle qui change les statuts des vedettes � tour de r�le. Drama knows no
	 * bounds! Ne terminera jamais (fermez la fen�tre pour arr�ter le
	 * programme).
	 */

	public void startTheDrama() {

		while (true) {

			/*
			 * for (int i = 0; i < celebs.size(); i++) { SocialMediaCelebrity celeb =
			 * celebs.get(i); celeb.changeStatus(); fans.get(i).update(celeb.getStatus());
			 * log.update(celeb.getStatus());
			 */

			for (SocialMediaCelebrity celeb : celebs) {
				celeb.changeStatus();
			}

		}

	}


	/*
	 * Initialisation de la fen�tre.
	 */
	private void initUI() {

		setTitle("FaceTwit");
		setSize(1600, 1000);
		background = new JPanel(new GridLayout(4, 5));
		for (MessageView view : views) {
			background.add(view);
		}

		add(background);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addToMainWindow(MessageView view) {
		views.add(view);
	}

	public static void main(String[] args) {

		/*
		 * Mise en file de l'ex�cution de l'interface graphique pour le
		 * EventDispatchThread (gestionnaire de fen�tres).
		 */
		Labo5Main ex = new Labo5Main(args);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				ex.initUI();
				ex.setVisible(true);
			}
		});

		ex.startTheDrama();
	}
}
