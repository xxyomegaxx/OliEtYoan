package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class EditTextCommand extends EditDocumentCommand{

	public EditTextCommand(EditableDocument doc, EditorTextArea txt, CommandLog com) {
		super(doc, txt, com);
	}

	@Override
	protected void implExecute() {
		editDoc.setText(editTextArea.getText());
		
	}

	@Override
	protected void saveState() {
		// TODO Auto-generated method stub
		
	}

}
