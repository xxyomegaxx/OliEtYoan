package labo7.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import commands.Command;
import commands.Invoker;
import labo7.model.EditableDocument;

public class EditorCheckBox extends JCheckBox implements ActionListener,Invoker{

	private Command command;

	public EditorCheckBox(String label) {
		super(label);
		addActionListener(this);
	}

	private static final long serialVersionUID = 1L;	

	@Override
	public void actionPerformed(ActionEvent evt) {
		command.execute();

	}

	@Override
	public void storeCommand(Command c) {
		command =c;
	}

}
