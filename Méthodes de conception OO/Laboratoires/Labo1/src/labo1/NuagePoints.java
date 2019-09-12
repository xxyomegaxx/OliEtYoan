package labo1;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class NuagePoints {
	
	ArrayList<Point> listePoints = new ArrayList<Point>();
	int nbPoints;
	
	public void dessiner(Graphics2D graph){
		for(int i=0;i<nbPoints;i++)
		{
			listePoints.get(i).dessiner(graph);
			
		}
	}
	
	/*
	 * Fonction qui obtient les coordonnées du point 
	 * à partir d'un flot d'entiers
	 */
	public void lire(Scanner reader) {		
		nbPoints = reader.nextInt();
		for(int i=0;i<nbPoints;i++)
		{
			try {
				Point temp = new Point();
				temp.setX(reader.nextInt());
				temp.setY(reader.nextInt());
			    if(listePoints.contains(temp))
			    {
			  	      nbPoints--;//est ce qu'on doit delete temp?
			    }
			    else listePoints.add(temp);
			}
			catch(NoSuchElementException e)
			{
				nbPoints =i;
			}
						
		}

		
	}
	public String toString()
	{
		String retour = "";
		for(int i=0;i<nbPoints;i++)
		{
			
			retour+=(listePoints.get(i).toString()+"\n");
		}
		
		return retour;
	}

}
