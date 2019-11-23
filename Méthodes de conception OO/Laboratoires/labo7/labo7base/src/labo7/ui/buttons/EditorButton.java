package labo7.ui.buttons;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import commands.Command;
import commands.Invoker;
import labo7.model.EditableDocument;


public class EditorButton extends JButton implements ActionListener,Invoker{

	private static final long serialVersionUID = 1L;
	private Command command = null;
	
	public EditorButton(String label)
	{
		super(label);
		setMaximumSize(new Dimension(120,20));
		this.addActionListener(this);
	}	

	@Override
	public void actionPerformed(ActionEvent evt){
		if(command!=null)
		{
			command.execute();
		}
		
		else System.err.println("Erreur: Fonctionnalité non implantée");
	}

	@Override
	public void storeCommand(Command c) {
		command = c;
		
	}

	

}
