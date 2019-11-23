package labo7.ui;

import javax.swing.JLabel;

import labo7.model.DocumentObserver;
import labo7.model.EditableDocument;

public class EditorLabel extends JLabel implements DocumentObserver{

	private EditableDocument model;
	private static final long serialVersionUID = 1L;

	
	@Override
	public void update() {
		setText("Character count:"+model.getText().length());		
	}
	
	public void setModel(EditableDocument d){
		model=d;
		model.attach(this);
	}

}
