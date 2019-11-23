package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class TwitCommand extends Command{

	public TwitCommand(EditableDocument doc, EditorTextArea txt) {
		super(doc, txt);
	}

	@Override
	public void execute() {
		if(editDoc.getText().length()>140){
			editDoc.setText(editDoc.getText().substring(0, 140));
		}
		
	}

}
