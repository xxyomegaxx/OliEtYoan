package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class PasteCommand extends EditDocumentCommand{

	public PasteCommand(EditableDocument doc, EditorTextArea txt) {
		super(doc, txt);
	}

	@Override
	public void execute() {
		editDoc.paste(editTextArea.getSelectionStart());
		
	}

}