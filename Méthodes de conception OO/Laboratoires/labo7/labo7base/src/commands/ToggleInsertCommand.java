package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorCheckBox;

public class ToggleInsertCommand extends Command{
	EditorCheckBox box;
	EditableDocument doc;
	
	public ToggleInsertCommand(EditorCheckBox b,EditableDocument d)
	{
		box = b;
		doc = d;
	}

	@Override
	public void execute() {
		
		if (box.isSelected()) {
			doc.enableInsert();
		} else {
			doc.disableInsert();
		}
		
	}
	
	

}

