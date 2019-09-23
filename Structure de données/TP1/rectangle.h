/**
* \ file triangle.h
* \ author  Olivier Côté(18116913) et Yoan David()
* \ brief Ce fichier contient les specifications
* du type Triangle (Ensemble de triangles)
*/

#ifndef RECTANGLE_H
#define RECTANGLE_H
#include "polygone.h"

/**
* \ brief Classe Rectangle
*/

class Rectangle : public Polygone {

 public:

  //Constructeur
  Rectangle(Point, Point, Point,  Point);

  // calcul de l'aire
  float calculerAire();

};
#endif
