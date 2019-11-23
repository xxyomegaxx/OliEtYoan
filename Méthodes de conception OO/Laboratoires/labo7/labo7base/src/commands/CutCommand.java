package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class CutCommand extends Command{

	public CutCommand(EditableDocument doc, EditorTextArea txt) {
		super(doc, txt);
	}

	@Override
	public void execute() {
		editDoc.cut(editTextArea.getSelectionStart(),editTextArea.getSelectionEnd());
		
	}

}