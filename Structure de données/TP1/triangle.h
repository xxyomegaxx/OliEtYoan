/**
* \ file triangle.h
* \ author Aida Ouangraoua
* \ brief Ce fichier contient les specifications
* du type Triangle (Ensemble de triangles)
*/

#ifndef TRIANGLE_H
#define TRIANGLE_H
#include "polygone.h"

/**
* \ brief Classe Triangle
*/

class Triangle : public Polygone{

 public:

  //Constructeur
  Triangle(Point, Point, Point);
    
  // calcul de l'aire
  float calculerAire();
  
};
#endif
