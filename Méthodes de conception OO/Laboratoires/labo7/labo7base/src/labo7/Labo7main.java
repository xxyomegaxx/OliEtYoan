package labo7;

import java.awt.EventQueue;

import labo7.model.EditableDocument;
import labo7.ui.Editor;

public class Labo7main {

	
		public static void main(String[] args) {
			
			final EditableDocument doc = new EditableDocument();
			
			/*
			 * Mise en file de l'exécution de l'interface graphique pour le
			 * EventDispatchThread (gestionnaire de fenêtres).
			*/
						
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					
					Editor twitedit = new Editor(doc);					
					doc.setText("In 1492, everyone knew the world was roughly spherical with a circumference of 24,000 miles. Columbus, ignoring nearly 2,000 years of evidence, decided the circumference was 8,000 miles. No one would finance the crazy dude except the benighted Ferdinand and Isabella. (In their defense, their nation had just ended a centuries-long war and hadn't had much time for academic study.) Columbus (to the day he died) insisted he'd traveled to India and denyied that he'd landed on a new continent.");
					twitedit.setVisible(true);
				}
			});
			
			
		}

	

}
