package labo1;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Scanner;

public class DessinCompose extends Dessin {
	private ArrayList<Dessin> listeDessins = new ArrayList<Dessin>();
	
	
	public void dessiner(Graphics2D graph){
		for(int i=0;i<listeDessins.size();i++)
		{
			listeDessins.get(i).dessiner(graph);
		}


	}
	

	public void lire(Scanner reader) {	
		Dessin temp = null;
		int value;
		while(reader.hasNextInt())
		{
			value = reader.nextInt();
			if(value==-1) temp=new Point();
			else if(value==-2)temp=new Ligne();	
			else if(value==-3)temp=new NuagePoints();
			else if(value==-4)temp=new Polygone();
			temp.lire(reader);
			listeDessins.add(temp);
		}
		
		
	}

}
