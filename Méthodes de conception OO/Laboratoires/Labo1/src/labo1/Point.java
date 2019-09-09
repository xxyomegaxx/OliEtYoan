package labo1;

import java.awt.Graphics2D;
import java.util.Scanner;

public class Point {
	
	private int x;
	private int y;
	
	public Point(){
		x=0;
		y=0;
	}
	
	/*
	 * Fonction qui dessine le point sur la surface 2D.
	 */
	
	public void dessiner(Graphics2D graph){
		graph.drawLine(x-5, y+5, x+5, y-5);
		graph.drawLine(x+5, y+5, x-5, y-5);

	}
	
	/*
	 * Fonction qui obtient les coordonnées du point 
	 * à partir d'un flot d'entiers
	 */
	public void lire(Scanner reader) {		
		x=reader.nextInt();
		y=reader.nextInt();		
		
	}
	
	
	
	
	
}
