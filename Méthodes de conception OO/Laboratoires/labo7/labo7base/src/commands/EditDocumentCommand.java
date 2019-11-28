package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public abstract class EditDocumentCommand extends Command implements Cloneable {

	EditableDocument editDoc;
	EditorTextArea editTextArea;
	CommandLog commandLog;
	String savedText = "";

	public EditDocumentCommand(EditableDocument doc, EditorTextArea txt,CommandLog com) {
		editDoc = doc;
		editTextArea = txt;
		commandLog = com;
	}
	
	public void undo()
	{
		editDoc.setText(savedText);
	}
	
	@Override
	public void execute()
	{
		savedText = editDoc.getText();
		saveState();
		implExecute();
		String newText = editDoc.getText();
		if(!savedText.equals(newText))
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
