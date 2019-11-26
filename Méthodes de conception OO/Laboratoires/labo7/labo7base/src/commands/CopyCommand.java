package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class CopyCommand extends EditDocumentCommand{

	public CopyCommand(EditableDocument doc, EditorTextArea txt) {
		super(doc, txt);
	}

	@Override
	public void execute() {
		editDoc.copy(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

}