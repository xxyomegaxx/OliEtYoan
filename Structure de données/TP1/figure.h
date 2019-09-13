/**
* \ file figure.h
* \ author Aida Ouangraoua
* \ brief Ce fichier contient les specifications
* du type Figure (Ensemble de figures geometriques)
*/

#ifndef FIGURE_H
#define FIGURE_H
#include "point.h"

/**
* \ brief Classe Figure
*/

class Figure
{
  
 public:
  // constructeur
  Figure(){};
  
  // destructeur
  ~Figure() = default;
  
  // translation suivant un vecteur represente par un Point
  virtual void translater(Point){};
  
  // rotation autour de l'origine du plan suivant un angle donne en degres
  virtual void tournerOrigine(float){};
  
  // rotation autour d'un Point donne suivant un angle donne en degres
  virtual void tourner(Point, float){};
  
  // calcul de la circonference
  virtual float calculerCirconference(){return 0.0;};
  
  // calcul de l'aire
  virtual float calculerAire(){return 0.0;};
  
  // affichage des coordonnees
  virtual void afficher(void){};
  
};
#endif
