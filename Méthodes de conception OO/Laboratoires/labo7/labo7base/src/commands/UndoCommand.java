package commands;

public class UndoCommand extends Command {

	CommandLog commandLog;

	public UndoCommand(CommandLog c) {
		commandLog = c;
	}

	@Override
	public void execute() {

		commandLog.undo();

	}

}
