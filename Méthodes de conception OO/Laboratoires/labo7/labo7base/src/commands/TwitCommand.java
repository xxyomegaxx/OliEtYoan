package commands;

import labo7.model.EditableDocument;
import labo7.ui.EditorTextArea;

public class TwitCommand extends EditDocumentCommand{

	public TwitCommand(EditableDocument doc, EditorTextArea txt,CommandLog com) {
		super(doc, txt,com);
	}

	@Override
	public void implExecute() {
		if(editDoc.getText().length()>140){
			editDoc.setText(editDoc.getText().substring(0, 140));
		}
		
	}

	@Override
	protected void saveState() {
		// TODO Auto-generated method stub
		
	}

}
