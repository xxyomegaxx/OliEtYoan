package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public abstract class EditDocumentCommand extends Command implements Cloneable {

	EditableDocument editDoc;
	EditorTextArea editTextArea;
	CommandLog commandLog;
	String oldText = "";
	String newText = "";

	public EditDocumentCommand(EditableDocument doc, EditorTextArea txt,CommandLog com) {
		editDoc = doc;
		editTextArea = txt;
		commandLog = com;
	}
	
	public void undo()
	{
		editDoc.setText(oldText);
	}
	public void redo()
	{
		editDoc.setText(newText);
	}
	
	@Override
	public void execute()
	{
		commandLog.trim();
		oldText = editDoc.getText();
		saveState();
		implExecute();
		newText = editDoc.getText();
		if(!oldText.equals(newText))
		{
			commandLog.add(clone());
		}
	}

	public EditDocumentCommand clone() {
		try {
			return (EditDocumentCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	protected abstract void implExecute();
	protected abstract void saveState();


}
