package labo1;

import java.awt.Graphics2D;
import java.util.Scanner;

public class Ligne {
	
	private Point a;
	private Point b;
	
	public Ligne(){
		a = new Point();
		b = new Point();
	}
	
	/*
	 * Fonction qui dessine le point sur la surface 2D.
	 */
	
	public void dessiner(Graphics2D graph){
		a.dessiner(graph);
		b.dessiner(graph);
		graph.drawLine(a.getX(), a.getY(), b.getX(), b.getY());


	}
	
	/*
	 * Fonction qui obtient les coordonnées du point 
	 * à partir d'un flot d'entiers
	 */
	public void lire(Scanner reader) {		
		a.setX(reader.nextInt());
		a.setY(reader.nextInt());		
		b.setX(reader.nextInt());
		b.setY(reader.nextInt());
	}
	

}
