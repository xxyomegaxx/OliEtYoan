package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorCheckBox;
import labo7.ui.EditorTextArea;

public class CommandFactory {

	private static CommandFactory INSTANCE = null;

	private static CommandLog commandLog = new CommandLog();

	private static EditableDocument doc;
	private static EditorTextArea txt;

	private static EditorCheckBox box;

	private CopyCommand copyCommand = null;
	private CutCommand cutCommand = null;
	private MajCommand majCommand = null;
	private MinCommand minCommand = null;
	private PasteCommand pasteCommand = null;
	private ToggleInsertCommand toggleInsertCommand = null;
	private TwitCommand twitCommand = null;
	private UndoCommand undoCommand = null;
	private RedoCommand redoCommand = null;

	private CommandFactory(EditableDocument d, EditorTextArea t, EditorCheckBox b) {
		doc = d;
		txt = t;
		box = b;
	}

	public static void initFactory(EditableDocument d, EditorTextArea t, EditorCheckBox b) {
		doc = d;
		txt = t;
		box = b;

	}

	public static synchronized CommandFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CommandFactory(doc, txt, box);
		}
		return INSTANCE;
	}
	
	public CommandLog getCommandLog() {
		return commandLog;
	}

	public CopyCommand createCopyCommand() {
		if (copyCommand == null) {
			return copyCommand = new CopyCommand(doc, txt, commandLog);
		} else {
			return copyCommand;
		}

	}

	public PasteCommand createPasteCommand() {
		if (pasteCommand == null) {
			return pasteCommand = new PasteCommand(doc, txt, commandLog);

		} else {
			return pasteCommand;
		}

	}

	public CutCommand createCutCommand() {
		if (cutCommand == null) {
			return cutCommand = new CutCommand(doc, txt, commandLog);

		} else {
			return cutCommand;

		}
	}

	public MajCommand createMajCommand() {
		if (majCommand == null) {
			return majCommand = new MajCommand(doc, txt, commandLog);

		} else {
			return majCommand;

		}
	}

	public MinCommand createMinCommand() {
		if (minCommand == null) {
			return minCommand = new MinCommand(doc, txt, commandLog);

		} else {
			return minCommand;
		}
	}

	public ToggleInsertCommand createToggleInsertCommand() {
		if (toggleInsertCommand == null) {
			return toggleInsertCommand = new ToggleInsertCommand(box, doc);

		} else {
			return toggleInsertCommand;

		}
	}

	public TwitCommand createTwitCommand() {
		if (twitCommand == null) {
			return twitCommand = new TwitCommand(doc, txt, commandLog);

		} else {
			return twitCommand;

		}
	}

	public UndoCommand createUndoCommand() {
		if (undoCommand == null) {
			return undoCommand = new UndoCommand(commandLog);

		} else {
			return undoCommand;

		}
	}
	public RedoCommand createRedoCommand() {
		if (redoCommand == null) {
			return redoCommand = new RedoCommand(commandLog);

		} else {
			return redoCommand;

		}
	}

//	public RedoCommand createRedoCommand() {
//		
//	}

}
