#ifndef PLAN_H
/**
* \ file plan.h
* \ author Aida Ouangraoua
* \ brief Ce fichier contient les specifications
* du type Plan 
*/


#define PLAN_H
#include "figure.h"

/**
* \ brief Classe Plan
*/

class Plan{

 public:

  // constructeur
  Plan();

  // ajout d'une nouvelle figure
  void ajouterFigure(Figure*);
  
  // suppression d'une figure existante a partir de son index
  void supprimerFigure(int);

  // translation de toutes les figures suivant un vecteur (Point)
  void translater(Point);
  
  // rotation de toutes les figures  autour l'origine du plan 
  void tournerOrigine(float);
  
  // rotation de toutes les figures autour d'un Point donne 
  void tourner(Point, float);
  
  // calcul de la circonference totale de toutes les figures
  float calculerCirconference();
    
  // calcul de l'aire totale de toutes les figures
  float calculerAire();

  // affichage des coordonnees de toutes les figures
  void afficher(void);

 private:
  static const int MAXFIGURES = 3;
  int nb_figures;
  Figure* figures[MAXFIGURES];
};
#endif
