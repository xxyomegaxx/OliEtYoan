package labo7.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import labo7.model.EditableDocument;

public class EditorCheckBox extends JCheckBox implements ActionListener{

	private EditableDocument model;

	public EditorCheckBox(String label,EditableDocument doc) {
		super(label);
		addActionListener(this);
		model=doc;
	}

	private static final long serialVersionUID = 1L;	

	@Override
	public void actionPerformed(ActionEvent evt) {

		if (isSelected()) {
			model.enableInsert();
		} else {
			model.disableInsert();
		}
	}

}
