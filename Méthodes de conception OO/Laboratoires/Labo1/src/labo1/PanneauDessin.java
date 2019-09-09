package labo1;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


/*
 * Panneau qui repr�sente une surface pour dessiner.
 * S'initialise avec un Point � dessiner.
 * Dessine le point.
 */

class PanneauDessin extends JPanel implements ActionListener {

    
	private static final long serialVersionUID = 1L;
	
	//L'objet qui sera dessin� sur la surface.
	private Point contenu;
	
	//La surface de dessin doit �tre cr��e avec un objet � dessiner.
    public PanneauDessin(Point lepoint) {

        contenu = lepoint;
    }    

    /*
     * C'est ici que �a se passe! 
     * Principalement un appel � la fonction de dessin 
     * implant�e par le contenu.
     */
    
    private void doDrawing(Graphics g) {

        Graphics2D graph = (Graphics2D) g;

        graph.setPaint(Color.black);      
        
        contenu.dessiner(graph);
        
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

