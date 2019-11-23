package labo7.model;

/*
 * Interface pour les observateurs du document.
 */
public interface DocumentObserver {
	
	public void update();
	public void setModel(EditableDocument doc);

}
