package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class MinCommand extends EditDocumentCommand{

	public MinCommand(EditableDocument doc, EditorTextArea txt) {
		super(doc, txt);
	}

	@Override
	public void execute() {
		editDoc.minimize(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

}