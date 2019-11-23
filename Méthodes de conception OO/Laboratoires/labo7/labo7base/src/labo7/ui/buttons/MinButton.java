package labo7.ui.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import labo7.model.EditableDocument;

public class MinButton extends EditorButton {

	private static final long serialVersionUID = 1L;

	public MinButton(String label, JTextArea area,EditableDocument doc) {
		super(label, area,doc);		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		model.minimize(textBox.getSelectionStart(),textBox.getSelectionEnd());	

	}

}
