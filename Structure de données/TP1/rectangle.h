/**
* \ file rectangle.h
* \ author Olivier Cote et Yoan David
* \ brief Ce fichier contient les specifications
* du type Rectangles (Ensemble de rectangles)
*/

#ifndef RECTANGLE_H
#define RECTANGLE_H
#include "polygone.h"

/**
* \ brief Classe rectangle
*/

class Rectangle : public Polygone{

 public:

  //Constructeur
  Rectangle(Point, Point, Point, Point);
    
  // calcul de l'aire
  float calculerAire();
  
};
#endif