package labo7.model;

import java.util.ArrayList;

/*
 * Représente le modèle sur lequel l'interface veut faire des modifications.
 * Le modèle est actuellement un document de texte simple avec un presse-papiers
 * (clipboard) et un mode d'insertion (lorsqu'on colle, on écrase ou non?)
 * Le document est observable, par des DocumentObserver. C'est un lien 
 * observateur de type pull (l'observateur doit questionner la source).
 */
public class EditableDocument {

	private String text;
	private String clipBoard = "";
	private boolean insert = true;
	private ArrayList<DocumentObserver> observers = new ArrayList<DocumentObserver>();

	public void attach(DocumentObserver o) {
		observers.add(o);
	}

	private void notifyObservers() {
		for (DocumentObserver o : observers) {
			o.update();
		}
	}

	public void copy(int start, int end) {

		clipBoard = text.substring(start, end);
	}

	public void paste(int start) {

		if (insert) {
			setText(text.substring(0, start) + clipBoard + text.substring(start, text.length()));
		}

		else {
			String temp = text.substring(0, start) + clipBoard;
			if (temp.length() > text.length()) {
				setText(temp);
			} else {
				setText(temp + text.substring(start + clipBoard.length(), text.length()));
			}
		}
	}

	public void cut(int start, int end) {
		clipBoard = text.substring(start, end);
		setText(text.substring(0, start) + text.substring(end, text.length()));
	}

	public void capitalize(int start, int end) {
		String temp = text.substring(start, end).toUpperCase();
		setText(text.substring(0, start) + temp + text.substring(end, text.length()));
	}

	public void minimize(int start, int end) {
		String temp = text.substring(start, end).toLowerCase();
		setText(text.substring(0, start) + temp + text.substring(end, text.length()));
	}

	public void enableInsert() {
		insert = true;
	}

	public void disableInsert() {
		insert = false;
	}

	public String getText() {
		return text;
	}

	public void setText(String newVal) {
		text = newVal;
		notifyObservers();
	}

}
