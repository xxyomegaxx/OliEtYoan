package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class MajCommand extends EditDocumentCommand{

	public MajCommand(EditableDocument doc, EditorTextArea txt) {
		super(doc, txt);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		editDoc.capitalize(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

}
