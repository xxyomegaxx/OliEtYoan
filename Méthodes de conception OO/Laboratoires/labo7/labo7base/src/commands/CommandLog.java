package commands;

import java.util.Stack;

public class CommandLog {

	Stack<EditDocumentCommand> undolist;
	Stack<EditDocumentCommand> redolist;

	public CommandLog() {
		undolist = new Stack<EditDocumentCommand>();
		redolist = new Stack<EditDocumentCommand>();
	}

	public void add(EditDocumentCommand c) {
		undolist.push(c);
	}

	public EditDocumentCommand removeLast() {
		System.out.println(undolist);
		if (undolist.size() > 0) {
			return undolist.pop();
		} else
			return null;
	}
	
	public Stack<EditDocumentCommand> getUndolist() {
		return undolist;
	}

}
