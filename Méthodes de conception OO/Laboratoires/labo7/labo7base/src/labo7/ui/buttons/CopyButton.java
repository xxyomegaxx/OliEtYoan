package labo7.ui.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import labo7.model.EditableDocument;

public class CopyButton extends EditorButton {

	
	private static final long serialVersionUID = 1L;
	public CopyButton(String label, JTextArea area,EditableDocument doc) {
		super(label, area,doc);		
	}	

	@Override
	public void actionPerformed(ActionEvent evt) {
		model.copy(textBox.getSelectionStart(),textBox.getSelectionEnd());	
	}

}
