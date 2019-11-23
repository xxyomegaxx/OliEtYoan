package labo7.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import labo7.model.DocumentObserver;
import labo7.model.EditableDocument;
import labo7.ui.menu.EditorMenu;
import labo7.ui.menu.EditorMenuItem;

public class EditorTextArea extends JTextArea implements DocumentListener, DocumentObserver, MouseListener {

	private static final long serialVersionUID = 1L;

	private EditableDocument model;

	// Sourdine de modifications: Cette classe peut causer des modifications au
	// modèle, et doit également
	// se mettre à jour lorsque le modèle change. Un appel à model.setText
	// causerait une boucle infinie.
	private boolean mute = false;

	public EditorTextArea(int i, int j, EditableDocument doc) {
		super(i, j);
		setMaximumSize(new Dimension(i, j));
		setMinimumSize(new Dimension(i, j));
		// Attention! Le texte interne du textArea n'est pas la même chose
		// que le EditableDocument. La façon de déterminer si le texte interne a
		// été
		// modifié est d'utiliser un DocumentListener, qui se voit signalé
		// lorsque le texte dans le contrôle d'interface est modifié.
		getDocument().addDocumentListener(this);
		addMouseListener(this);

		// Est également un observateur du EditableDocument!
		model = doc;
		model.attach(this);
	}

	@Override
	public void setModel(EditableDocument doc) {
		model = doc;
		model.attach(this);
	}

	/*
	 * Mises à jour du champ de texte interne du JTextArea. Lorsque cela se
	 * produit, on doit mettre à jour le texte du modèle.
	 */
	@Override
	public void insertUpdate(DocumentEvent evt) {
		modifyDocument(evt);
	}

	@Override
	public void removeUpdate(DocumentEvent evt) {
		modifyDocument(evt);
	}

	/*
	 * Méthode invoquée lorsque le modèle change. Le texte doit être mis à jour
	 * dans la boîte de texte selon le texte se trouvant dans le modèle. Cause
	 * un événement insertUpdate. Pourrait causer une boucle infinie sans la
	 * sourdine.
	 */
	@Override
	public void update() {
		if (!mute) {
			mute = true;
			setText(model.getText());
			mute = false;
		}
	}

	/*
	 * Mises à jour du champ de texte interne du JTextArea. Lorsque cela se
	 * produit, ont doit mettre à jour le texte du modèle. Cette mise à jour
	 * cause une notification chez (setText chez EditableDocument), ce qui
	 * envoie un signal à tous les observateurs, dont l'instance actuelle.
	 * Causerait des mises à jour en boucle sans la sourdine.
	 */
	private void modifyDocument(DocumentEvent evt) {
		if (!mute) {
			mute = true;

			// Attention! Le texte interne du textArea n'est pas la même
			// chose que le EditableDocument.
			// getText() obtient le texte se trouvant dans le contrôle
			// d'interface. model.setText modifie le texte du côté du
			// EditableDocument.
			model.setText(this.getText());

			mute = false;
		}
	}

	/*
	 * Méthode invoquée lorsqu'un bouton de la souris est relâché.
	 */

	@SuppressWarnings("serial")
	@Override
	public void mouseReleased(MouseEvent evt) {
		// Si c'est le déclencheur de menu (clic droit sur PC, ctrl-clic sur
		// Mac)...
		if (evt.isPopupTrigger()||SwingUtilities.isRightMouseButton(evt) || evt.isControlDown()) {
			
			EditorMenu menu = new EditorMenu();
			menu.add(new EditorMenuItem("Ceci est un test", model, this) {

				public void actionPerformed(ActionEvent evt) {
					System.out.println("Ceci est un item de menu test");
				}

			});
			add(menu);
			menu.show(this, evt.getX(), evt.getY());
		}

	}

	@Override
	public void changedUpdate(DocumentEvent evt) {
		// jamais déclenché
	}

	/*
	 * Autres méthodes de gestion de la souris. Ne causent aucun événement dans
	 * cette classe.
	 */
	
	@Override
	public void mouseClicked(MouseEvent evt) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}


	@Override
	public void mousePressed(MouseEvent evt) {
		
	}
}
