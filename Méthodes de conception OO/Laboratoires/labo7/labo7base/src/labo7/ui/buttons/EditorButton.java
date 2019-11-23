package labo7.ui.buttons;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import labo7.model.EditableDocument;


public class EditorButton extends JButton implements ActionListener{

	private static final long serialVersionUID = 1L;
	protected EditableDocument model;
	protected JTextArea textBox;
	
	public EditorButton(String label,JTextArea area,EditableDocument doc)
	{
		super(label);
		setMaximumSize(new Dimension(120,20));
		this.addActionListener(this);
		textBox=area;
		model=doc;
	}	

	@Override
	public void actionPerformed(ActionEvent evt){
		System.err.println("Erreur: Fonctionnalité non implantée");
	}

	

}
