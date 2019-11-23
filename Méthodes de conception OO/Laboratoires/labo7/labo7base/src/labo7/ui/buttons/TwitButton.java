package labo7.ui.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import labo7.model.EditableDocument;

public class TwitButton extends EditorButton {

	
	private static final long serialVersionUID = 1L;

	public TwitButton(String label, JTextArea area,EditableDocument doc) {
		super(label, area,doc);		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(model.getText().length()>140){
			model.setText(model.getText().substring(0, 140));
		}
	}

}
