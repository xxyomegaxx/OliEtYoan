package labo7.ui.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import labo7.model.EditableDocument;

public class CutButton extends EditorButton{
	
	
	private static final long serialVersionUID = 1L;

	public CutButton(String label, JTextArea area,EditableDocument doc) {
		super(label, area,doc);		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
		model.cut(textBox.getSelectionStart(),textBox.getSelectionEnd());	
	}

}
