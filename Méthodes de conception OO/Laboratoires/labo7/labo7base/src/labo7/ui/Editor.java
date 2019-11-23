package labo7.ui;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import labo7.model.EditableDocument;
import labo7.ui.buttons.CopyButton;
import labo7.ui.buttons.CutButton;
import labo7.ui.buttons.EditorButton;
import labo7.ui.buttons.MajButton;
import labo7.ui.buttons.MinButton;
import labo7.ui.buttons.PasteButton;
import labo7.ui.buttons.TwitButton;
import labo7.ui.shortcuts.ShortcutManager;

@SuppressWarnings("serial")
public class Editor extends JFrame{

	
	private EditableDocument model;
	
	
	private ShortcutManager shortcuts;

	
	private EditorLabel charCount;
	private EditorCheckBox insert;
	private EditorTextArea textBox;
	private EditorButton copyButton;
	private EditorButton cutButton;
	private EditorButton pasteButton;
	private EditorButton minButton;
	private EditorButton twitButton;
	private EditorButton majButton;
	
	
	private EditorButton redo;
	private EditorButton undo;

	public Editor(EditableDocument doc) {

		setModel(doc);
		setSize(800,600);
		setTitle("TwitEdit");		

		
		Font font =new Font("Arial",Font.BOLD,20);
		JPanel background = new JPanel();
		background.setLayout(new BoxLayout(background ,BoxLayout.X_AXIS));
		
		//Boîte de texte
		textBox= new EditorTextArea(500, 400,model);
		textBox.setFont(font);	
		textBox.setLineWrap(true);
		textBox.setWrapStyleWord(true);
		
		//Gestionnaire de clavier pour les raccourcis
		shortcuts= new ShortcutManager(); 
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(shortcuts);
		
		
		/*
		 * Initialisation du panneau contenant les boutons
		 */		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
		buttonsPanel.setMaximumSize(new Dimension(130,600));
		
		copyButton = new CopyButton("Copier",textBox,model);
		cutButton = new CutButton("Couper",textBox,model);
		pasteButton = new PasteButton("Coller",textBox,model);
		majButton = new MajButton("MAJUSCULES",textBox,model);
		minButton = new MinButton("minuscules",textBox,model);
		twitButton = new TwitButton("Twitterize",textBox,model);		
		
		buttonsPanel.add(Box.createVerticalGlue());
		buttonsPanel.add(copyButton);
		buttonsPanel.add(cutButton);
		buttonsPanel.add(pasteButton);
		buttonsPanel.add(majButton);
		buttonsPanel.add(minButton);
		buttonsPanel.add(twitButton);			
		buttonsPanel.add(Box.createVerticalGlue());
		
		background.add(buttonsPanel);				
		background.add(Box.createHorizontalGlue());
		
		
		/*
		 * Initialisation du panneau contenant la boîte de texte et les autres contrôles
		 */		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.Y_AXIS));			
			
	
		//Compteur de caractères
		charCount=new EditorLabel();
		charCount.setModel(model);
		charCount.setFont(font);
		textPanel.add(charCount);
		textPanel.add(textBox);
	
		//Panneau avec undo, redo et insere
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.X_AXIS));		
		
		undo = new EditorButton("undo",textBox,model);
		redo = new EditorButton("redo",textBox,model);
		
		insert = new EditorCheckBox("Insertion",model);
		insert.setSelected(true);
		controlPanel.add(undo);
		controlPanel.add(redo);
		controlPanel.add(insert);
		
		textPanel.add(controlPanel);		
		background.add(textPanel);		
		
		
		//Ajout du panneau principal dans le frame
		add(background);		
		
		//Préparations finales
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void setModel(EditableDocument doc) {
		model=doc;		
	}	

}