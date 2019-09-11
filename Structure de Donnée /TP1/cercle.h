/**
* \ file cercle.h
* \ author Aida Ouangraoua
* \ brief Ce fichier contient les specifications
* du type Cercle (Ensemble de cercles)
*/

#ifndef CERCLE_H
#define CERCLE_H
#include "figure.h"

/**
* \ brief Classe Cercle
*/

class Cercle : public Figure
{

 public:
  // constructeur
  Cercle(Point, float);

  // translation suivant un vecteur represente par un Point
  void translater(Point);
  
  // rotation autour de l'origine du plan suivant un angle donne en degres
  void tournerOrigine(float);
  
  // rotation autour d'un Point donne suivant un angle donne en degres
  void tourner(Point, float);
  
  // calcul de la circonference
  float calculerCirconference();
  
  // calcul de l'aire
  float calculerAire();

  // affichage des coordonnees
  void afficher(void);
  
 private:
  Point centre;
  float rayon;
};
#endif
