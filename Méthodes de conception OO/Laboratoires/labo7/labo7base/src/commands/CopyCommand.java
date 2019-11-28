package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class CopyCommand extends EditDocumentCommand{

	public CopyCommand(EditableDocument doc, EditorTextArea txt,CommandLog com) {
		super(doc, txt,com);
	}

	@Override
	public void implExecute() {
		editDoc.copy(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

	@Override
	protected void saveState() {
		editDoc.copy(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

}