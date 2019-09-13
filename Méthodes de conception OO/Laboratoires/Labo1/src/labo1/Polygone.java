package labo1;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Scanner;

public class Polygone extends Dessin {
	
	private NuagePoints nuage = new NuagePoints();
	private ArrayList<Ligne> listeLignes = new ArrayList<Ligne>();

	public void dessiner(Graphics2D graph){
		for (int i =0;i<nuage.getNbPoints();i++)
		{
			listeLignes.get(i).dessiner(graph);
		}


	}
	
	/*
	 * Fonction qui obtient les coordonnées du point 
	 * à partir d'un flot d'entiers
	 */
	public void lire(Scanner reader) {		
		nuage.lire(reader);		
		Ligne temp;
		
		for(int i=0;i<nuage.getNbPoints()-1;i++)
		{
			temp = new Ligne(nuage.getPointFromList(i),nuage.getPointFromList(i+1));
			listeLignes.add(temp);
			
		}
		temp =new Ligne(nuage.getPointFromList(0),nuage.getPointFromList(nuage.getNbPoints()-1));
		listeLignes.add(temp);
	}
	
	
}
