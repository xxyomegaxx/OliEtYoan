/**
* \ file polygone.h
* \ author Aida Ouangraoua
* \ brief Ce fichier contient les specifications
* du type Polygone (Ensemble de polygones a 4
* sommets maximum)
*/

#ifndef POLYGONE_H
#define POLYGONE_H
#include "figure.h"


/**
* \ brief Classe Polygone
*/

class Polygone : public Figure{

 public:
  // constructeurs
  Polygone();

  Polygone(int);

  // translation suivant un vecteur represente par un Point
  void translater(Point);
  
  // rotation autour de l'origine du plan suivant un angle donne en degres
  void tournerOrigine(float);
  
  // rotation autour d'un Point donne suivant un angle donne en degres
  void tourner(Point, float);
  
  // calcul de la circonference
  float calculerCirconference();
    
  // affichage des coordonnees
  void afficher(void);
  
 protected:
  static const int MAXSOMMETS = 4;
  int nb_sommets;
  Point sommets[MAXSOMMETS] = {Point(0,0), Point(0,0), Point(0,0), Point(0,0)};
};
#endif
