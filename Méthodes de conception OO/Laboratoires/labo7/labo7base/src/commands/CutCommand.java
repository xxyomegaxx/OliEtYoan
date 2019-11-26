package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class CutCommand extends EditDocumentCommand{

	public CutCommand(EditableDocument doc, EditorTextArea txt,CommandLog com) {
		super(doc, txt,com);
	}

	@Override
	public void implExecute() {
		editDoc.cut(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

}