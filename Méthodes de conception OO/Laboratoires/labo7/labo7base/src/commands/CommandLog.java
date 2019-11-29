package commands;

import java.util.ArrayList;
import java.util.Stack;

public class CommandLog {

	ArrayList<EditDocumentCommand> commandList;
	int lastCommand = 0;

	public CommandLog() {
		commandList = new ArrayList<EditDocumentCommand>();
	}

	public void add(EditDocumentCommand c) {
		commandList.add(c);
		lastCommand++;
	}

	public void undo() {

		if (lastCommand > 0) {
			lastCommand--;

			commandList.get(lastCommand).undo();
		}

	}

	public void redo() {

		if (lastCommand < commandList.size()) {
			commandList.get(lastCommand).redo();
			lastCommand++;

		}

	}

	public ArrayList<EditDocumentCommand> getUndolist() {
		return commandList;
	}

	public void trim() {
		while (lastCommand<commandList.size())
		{
			commandList.remove(lastCommand);
		}
		
	}

}
