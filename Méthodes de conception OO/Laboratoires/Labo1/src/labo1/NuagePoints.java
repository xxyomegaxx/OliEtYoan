package labo1;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class NuagePoints extends Dessin{
	
	private ArrayList<Point> listePoints = new ArrayList<Point>();
	private int nbPoints;
	
	public void dessiner(Graphics2D graph){
		for(int i=0;i<nbPoints;i++)
		{
			listePoints.get(i).dessiner(graph);
			
		}
	}
	
	/*
	 * Fonction qui obtient les coordonn�es du point 
	 * � partir d'un flot d'entiers
	 */
	public void lire(Scanner reader) {		
		nbPoints = reader.nextInt();
		if (nbPoints > 0)
		{
			int minus = 0;
			for(int i=0;i<nbPoints;i++)
			{
				try {
					Point temp = new Point();
					temp.setX(reader.nextInt());
					temp.setY(reader.nextInt());
					boolean ans = listePoints.contains(temp);
				    if(ans)
				    {
				  	      minus++;//est ce qu'on doit delete temp?
				    }
				    else listePoints.add(temp);
				}
				catch(NoSuchElementException e)
				{
					nbPoints =i;
				}

							
			}
			
			nbPoints = nbPoints-minus;
		}
		else ;

		
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
	public int getNbPoints()
	{
		return nbPoints;
	}
	public Point getPointFromList(int i)
	{
		return listePoints.get(i);
	}

}
