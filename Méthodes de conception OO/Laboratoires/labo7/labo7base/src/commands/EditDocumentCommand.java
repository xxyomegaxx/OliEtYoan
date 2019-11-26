package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public abstract class EditDocumentCommand extends Command{
	
	EditableDocument editDoc;
	EditorTextArea editTextArea;
	public EditDocumentCommand(EditableDocument doc,EditorTextArea txt)
	{
		editDoc = doc;
		editTextArea = txt;
	}

}
