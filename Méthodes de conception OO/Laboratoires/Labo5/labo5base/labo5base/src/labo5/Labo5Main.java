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
import labo5.followers.MessageLog;
import labo5.followers.Reposter;
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

		for (int i = 0; i < celebs.size(); i++) {
			celebs.get(i).attach(fans.get(i));
			celebs.get(i).attach(log);
		}

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
