package labo7.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import labo7.model.EditableDocument;

public abstract class EditorMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected EditableDocument model;
	protected JTextArea textBox;

	public EditorMenuItem(String label,EditableDocument doc,JTextArea txt){
		super(label);
		model=doc;
		textBox=txt;
		addActionListener(this);
	}

	@Override
	public abstract void actionPerformed(ActionEvent evt);

}
