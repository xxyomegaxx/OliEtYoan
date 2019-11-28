package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class MajCommand extends EditDocumentCommand{

	public MajCommand(EditableDocument doc, EditorTextArea txt,CommandLog com) {
		super(doc, txt,com);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void implExecute() {
		editDoc.capitalize(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

	@Override
	protected void saveState() {
		// TODO Auto-generated method stub
		
	}

}
