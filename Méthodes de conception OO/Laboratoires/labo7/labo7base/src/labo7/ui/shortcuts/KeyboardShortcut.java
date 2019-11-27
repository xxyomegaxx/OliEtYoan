package labo7.ui.shortcuts;

import java.awt.event.KeyEvent;

import commands.Command;
import commands.EditDocumentCommand;
import commands.Invoker;

/*
 * Classe d�finissant un raccourci clavier.
 * Pr�voit une m�thode � �tre appel�e lorsque le raccourci est d�clench�.
 */

public class KeyboardShortcut implements Invoker {

	private boolean control;
	private int keyCode;
	private Command command;

	public KeyboardShortcut(int code, boolean ctrl) {
		control = ctrl;
		keyCode = code;
	}

	public boolean match(KeyEvent evt) {
		if (evt.getID() == KeyEvent.KEY_PRESSED) {
			if (evt.getKeyCode() == keyCode && evt.isControlDown() == control) {
				return true;
			}
		}
		return false;
	}

	public void activateShortcut() {
		if (command != null) {
			command.execute();
		}

		else
			System.err.println("Erreur: Fonctionnalit� non implant�e");
	}

	@Override
	public void storeCommand(Command c) {
		command = c;

	}

}
