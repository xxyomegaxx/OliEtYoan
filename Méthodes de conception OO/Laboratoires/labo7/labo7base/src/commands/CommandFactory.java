package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorCheckBox;
import labo7.ui.EditorTextArea;

public class CommandFactory {

	private static CommandFactory INSTANCE = null;

	private CommandLog commandLog = new CommandLog();

	private EditableDocument doc;
	private EditorTextArea txt;

	private EditorCheckBox box;

	private CopyCommand copyCommand;
	private CutCommand cutCommand;
	private MajCommand majCommand;
	private MinCommand minCommand;
	private PasteCommand pasteCommand;
	private ToggleInsertCommand toggleInsertCommand;
	private TwitCommand twitCommand;
	private UndoCommand undoCommand;

	private CommandFactory(EditableDocument d,EditorTextArea t,EditorCheckBox b) {
		doc = d;
		txt = t;
		box = b;
	}

	public static synchronized CommandFactory getInstance(EditableDocument d,EditorTextArea t,EditorCheckBox b) {
		if (INSTANCE == null) {
			INSTANCE = new CommandFactory(d, t, b);
		}
		return INSTANCE;
	}

	public CopyCommand createCopyCommand() {
		copyCommand = new CopyCommand(doc, txt, commandLog);
		return copyCommand;
	}

	public PasteCommand createPasteCommand() {
		pasteCommand = new PasteCommand(doc, txt, commandLog);
		return pasteCommand;
	}

	public CutCommand createCutCommand() {
		cutCommand = new CutCommand(doc, txt, commandLog);
		return cutCommand;
	}

	public MajCommand createMajCommand() {
		majCommand = new MajCommand(doc, txt, commandLog);
		return majCommand;
	}

	public MinCommand createMinCommand() {
		minCommand = new MinCommand(doc, txt, commandLog);
		return minCommand;
	}

	public ToggleInsertCommand createToggleInsertCommand() {
		toggleInsertCommand = new ToggleInsertCommand(box,doc);
		return toggleInsertCommand;
	}
	
	public TwitCommand createTwitCommand() {
		twitCommand = new TwitCommand(doc,txt,commandLog);
		return twitCommand;
	}
	
	public UndoCommand createUndoCommand() {
		undoCommand = new UndoCommand(commandLog);
		return undoCommand;
	}

}
