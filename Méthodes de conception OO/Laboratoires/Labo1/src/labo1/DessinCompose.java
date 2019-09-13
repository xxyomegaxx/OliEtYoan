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
		while(reader.hasNextInt())
		{
			if(reader.nextInt()==-1) temp=new Point();
			else if(reader.nextInt()==-2)temp=new Ligne();	
			else if(reader.nextInt()==-3)temp=new NuagePoints();
			else if(reader.nextInt()==-4)temp=new Polygone();
			temp.lire(reader);
			listeDessins.add(temp);
		}
		
		
	}

}
