package labo5.followers;

import labo5.Labo5Main;
import labo5.ui.MessageView;

public class Reposter {

	protected MessageView viewport;
	private String name;
	
	/*
	 * Un reposter est un fan de la c�l�brit�. 
	 */
	public Reposter(Labo5Main ui,String n){
		
		name=n;
		viewport = new MessageView(ui,name+" news feed");		
	}
		
	
	/*
	 * LABO4: code de mise � jour de l'affichage.
	 * Un reposter est un fan qui ne fait que r�afficher ce que la vedette
	 * a dit dans son propre fil de nouvelles
	 */
	public void update(String message){
		
		//Ajoute tout simplement le message dans la bo�te de texte.
		viewport.appendMessage(message);
	}
	
}
