package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class RedoCommand extends Command {

	CommandLog commandLog;

	public RedoCommand(CommandLog c) {
		commandLog = c;
	}
	
	@Override
	public void execute() {
		
		EditDocumentCommand c = commandLog.removeLast();
		if (c != null) {

			c.undo();

		}
		
	}

	

}
