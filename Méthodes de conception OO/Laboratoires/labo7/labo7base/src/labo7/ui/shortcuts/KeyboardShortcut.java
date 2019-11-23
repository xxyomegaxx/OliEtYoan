package labo7.ui.shortcuts;

import java.awt.event.KeyEvent;

/*
 * Classe d�finissant un raccourci clavier.
 * Pr�voit une m�thode � �tre appel�e lorsque le raccourci est d�clench�.
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

