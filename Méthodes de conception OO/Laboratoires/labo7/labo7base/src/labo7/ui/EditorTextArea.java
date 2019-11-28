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
	// mod�le, et doit �galement
	// se mettre � jour lorsque le mod�le change. Un appel � model.setText
	// causerait une boucle infinie.
	private boolean mute = false;

	public EditorTextArea(int i, int j, EditableDocument doc) {
		super(i, j);
		setMaximumSize(new Dimension(i, j));
		setMinimumSize(new Dimension(i, j));
		// Attention! Le texte interne du textArea n'est pas la m�me chose
		// que le EditableDocument. La fa�on de d�terminer si le texte interne a
		// �t�
		// modifi� est d'utiliser un DocumentListener, qui se voit signal�
		// lorsque le texte dans le contr�le d'interface est modifi�.
		getDocument().addDocumentListener(this);
		addMouseListener(this);

		// Est �galement un observateur du EditableDocument!
		model = doc;
		model.attach(this);
	}

	@Override
	public void setModel(EditableDocument doc) {
		model = doc;
		model.attach(this);
	}

	/*
	 * Mises � jour du champ de texte interne du JTextArea. Lorsque cela se produit,
	 * on doit mettre � jour le texte du mod�le.
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
	 * M�thode invoqu�e lorsque le mod�le change. Le texte doit �tre mis � jour dans
	 * la bo�te de texte selon le texte se trouvant dans le mod�le. Cause un
	 * �v�nement insertUpdate. Pourrait causer une boucle infinie sans la sourdine.
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
	 * Mises � jour du champ de texte interne du JTextArea. Lorsque cela se produit,
	 * ont doit mettre � jour le texte du mod�le. Cette mise � jour cause une
	 * notification chez (setText chez EditableDocument), ce qui envoie un signal �
	 * tous les observateurs, dont l'instance actuelle. Causerait des mises � jour
	 * en boucle sans la sourdine.
	 */
	private void modifyDocument(DocumentEvent evt) {
		if (!mute) {
			mute = true;

			// Attention! Le texte interne du textArea n'est pas la m�me
			// chose que le EditableDocument.
			// getText() obtient le texte se trouvant dans le contr�le
			// d'interface. model.setText modifie le texte du c�t� du
			// EditableDocument.
			model.setText(this.getText());

			mute = false;
		}
	}

	/*
	 * M�thode invoqu�e lorsqu'un bouton de la souris est rel�ch�.
	 */

	@Override
	public void mouseReleased(MouseEvent evt) {
		// Si c'est le d�clencheur de menu (clic droit sur PC, ctrl-clic sur
		// Mac)...
		if (evt.isPopupTrigger() || SwingUtilities.isRightMouseButton(evt) || evt.isControlDown()) {

			EditorMenu menu = new EditorMenu(model, this);
			menu.createEditorItem();
			add(menu);
			if(menu != null) {
				menu.show(this, evt.getX(), evt.getY());
			}
			
		}

	}

	@Override
	public void changedUpdate(DocumentEvent evt) {
		// jamais d�clench�
	}

	/*
	 * Autres m�thodes de gestion de la souris. Ne causent aucun �v�nement dans
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
