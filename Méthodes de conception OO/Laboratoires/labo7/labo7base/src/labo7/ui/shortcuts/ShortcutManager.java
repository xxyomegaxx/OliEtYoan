package labo7.ui.shortcuts;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/*
 * Classe gérant les raccourcis clavier.
 */

public class ShortcutManager implements KeyEventDispatcher {


	private ArrayList<KeyboardShortcut> shortcuts;

	public ShortcutManager() {
		
		shortcuts = new ArrayList<KeyboardShortcut>();
		initManager();
	}

	/*
	 * Méthode invoquée lorsqu'une touche est enfoncée ou relâchée.
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent evt) {

		/*
		 * Vérifie si un des raccourcis clavier correspond
		 * à l'événement reçu.
		 */		
		for (KeyboardShortcut sc : shortcuts) {
			if (sc.match(evt)) {
				sc.activateShortcut();
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * Permet l'ajout d'un raccourci.
	 */
	public void addShortcut(KeyboardShortcut sc){
		shortcuts.add(sc);
	}
	
	/*
	 * Définit les raccourcis actuels. 
	 */
	public void initManager(){
		
	
	}
	
	
	
}
