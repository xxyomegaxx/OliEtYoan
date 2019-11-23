package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public abstract class Command {
	
	EditableDocument editDoc;
	EditorTextArea editTextArea;
	public Command(EditableDocument doc,EditorTextArea txt)
	{
		editDoc = doc;
		editTextArea = txt;
	}
	public abstract void execute();

}
