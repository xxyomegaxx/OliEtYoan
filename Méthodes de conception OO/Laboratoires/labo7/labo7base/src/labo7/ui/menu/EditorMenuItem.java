package labo7.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import commands.Invoker;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import commands.Command;
import labo7.model.EditableDocument;

public class EditorMenuItem extends JMenuItem implements Invoker, ActionListener {

	private static final long serialVersionUID = 1L;
	protected EditableDocument model;
	protected JTextArea textBox;
	private Command command;

	public EditorMenuItem(String label, EditableDocument doc, JTextArea txt) {
		super(label);
		model = doc;
		textBox = txt;
		addActionListener(this);
	}

	@Override
	public void storeCommand(Command c) {
		command = c;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command.execute();
	}

}
