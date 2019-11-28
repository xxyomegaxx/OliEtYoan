package labo7.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JPopupMenu;

import commands.CommandFactory;
import commands.CommandLog;
import commands.CopyCommand;
import commands.MajCommand;
import commands.MinCommand;
import commands.PasteCommand;
import commands.UndoCommand;
import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class EditorMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	private EditableDocument editableDoc;
	private EditorTextArea textArea;

	public EditorMenu(EditableDocument e, EditorTextArea t) {
		editableDoc = e;
		textArea = t;
	}

	@SuppressWarnings("serial")
	public EditorMenuItem createEditorItem() {

		CopyCommand copy = CommandFactory.getInstance().createCopyCommand();
		PasteCommand paste = CommandFactory.getInstance().createPasteCommand();
		MajCommand maj = CommandFactory.getInstance().createMajCommand();
		MinCommand min = CommandFactory.getInstance().createMinCommand();
		
		CommandLog command = CommandFactory.getInstance().getCommandLog();
		UndoCommand undo = CommandFactory.getInstance().createUndoCommand();
		

		EditorMenuItem menuItem = null;

		System.out.println(command.getUndolist());
		
		if (textArea.getSelectionStart() != textArea.getSelectionEnd()) {

			this.add(menuItem = new EditorMenuItem("Copier", editableDoc, textArea) {

			});
			menuItem.storeCommand(copy);

			this.add(menuItem = new EditorMenuItem("Majuscule", editableDoc, textArea) {

			});
			menuItem.storeCommand(maj);
			this.add(menuItem = new EditorMenuItem("Minuscule", editableDoc, textArea) {

			});
			menuItem.storeCommand(min);

			return menuItem;

		}
		
		if (textArea.getSelectionStart() == textArea.getSelectionEnd()) {
			this.add(menuItem = new EditorMenuItem("Coller", editableDoc, textArea) {

			});
			menuItem.storeCommand(paste);
		}
		
		
		if(command.getUndolist().size() != 0) {
			this.add(menuItem = new EditorMenuItem("Undo", editableDoc, textArea) {
				
			});
			menuItem.storeCommand(undo);
		}
		return menuItem;
		
		

	}

}
