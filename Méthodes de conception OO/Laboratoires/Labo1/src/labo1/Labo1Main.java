package labo1;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;

public class Labo1Main extends JFrame {

 
	private static final long serialVersionUID = 1L;

	
	
	
	/*
	 * Cette belle fonction initialise le dessin. Elle lit un Point
	 * au clavier ou � partir du fichier
	 */
	
	private Point initDessin(Scanner reader){
		
		Point p = new Point();
		
			p.lire(reader);		
		
		return p;		
		
		
		
	}
	
	
	
	
	/*
	 * Initialisation de la classe de test.
	 * On obtient un nom de fichier des param�tres d'ex�cution.
	 * Si on n'a pas de parametre ou un fichier inexistant, on utilise
	 * le clavier comme flot d'entr�e.
	 */
	
	public Labo1Main(String[] args) {	
		
		Scanner reader = new Scanner(System.in);
		
    	if(args.length ==1){
			
			String file = args[0];		
		
		try {
				System.out.println("Lecture du fichier" + file + "...");
				reader = new Scanner(new FileReader(file));				
		}

		catch (FileNotFoundException e) {
			System.out.println("Fichier non trouv�...");
			reader = new Scanner(new InputStreamReader(System.in));
		}
    	}
		else
		{
			System.out.println("Aucun fichier en param�tre. Entrez les donn�es dans la console.");
			reader = new Scanner(System.in);
		}

        initUI(reader);
    }

	
	/*
	 * Initialisation de la fen�tre.
	 */
    private void initUI(Scanner reader) {
    	
    	//Obtention d'un point � partir du fichier ou du clavier
    	Point lepoint = initDessin(reader);    	

    	//Affichage sur la console de la valeur du point
    	System.out.println(lepoint);
    	
    	//Panneau qui sert de surface pour dessiner.
        PanneauDessin surface = new PanneauDessin(lepoint);
        
        //La surface de dessin est plac�e dans la fen�tre.
        add(surface);
        
        //Configurations relatives � la fen�tre.
        setTitle("Labo 1");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    	
    	
    
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Labo1Main ex = new Labo1Main(args);
                ex.setVisible(true);
            }
        });
    }
}
