package labo7.ui.shortcuts;

import java.awt.event.KeyEvent;

/*
 * Classe définissant un raccourci clavier.
 * Prévoit une méthode à être appelée lorsque le raccourci est déclenché.
 */

public abstract class KeyboardShortcut {

	private boolean control;
	private int keyCode;

	public KeyboardShortcut(int code, boolean ctrl) {
		control = ctrl;
		keyCode = code;
	}

	public boolean match(KeyEvent evt) {
		if (evt.getID() == KeyEvent.KEY_PRESSED) {				
			if(evt.getKeyCode() == keyCode && evt.isControlDown() == control){
				return true;
			}
		}
		return false;
	}

	public abstract void activateShortcut();

}

