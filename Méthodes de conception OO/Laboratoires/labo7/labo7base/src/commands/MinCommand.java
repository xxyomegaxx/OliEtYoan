package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class MinCommand extends EditDocumentCommand{

	public MinCommand(EditableDocument doc, EditorTextArea txt,CommandLog com) {
		super(doc, txt,com);
	}

	@Override
	public void implExecute() {
		editDoc.minimize(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

}