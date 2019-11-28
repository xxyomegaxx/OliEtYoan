package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class PasteCommand extends EditDocumentCommand {

	public PasteCommand(EditableDocument doc, EditorTextArea txt, CommandLog com) {
		super(doc, txt, com);
	}

	@Override
	public void implExecute() {
		editDoc.paste(editTextArea.getSelectionStart());

	}

	@Override
	protected void saveState() {
		// TODO Auto-generated method stub

	}

}