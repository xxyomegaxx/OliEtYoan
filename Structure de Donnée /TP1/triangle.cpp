/**
* \ file triangle.cpp
* \ author Aida Ouangraoua
* \ brief Ce fichier contient l'implementation
* des operations specifiques au type Triangle
*/

#include "triangle.h"

Triangle::Triangle(Point p1, Point p2, Point p3){
  nb_sommets = 3;
  sommets[0] = p1;
  sommets[1] = p2;
  sommets[2] = p3;
}

float Triangle::calculerAire(void){
  //Calcul des coordonnes de Sommets s0 et s1
  float abcisse0 = sommets[0].calculerAbcisse();
  float ordonnee0 = sommets[0].calculerOrdonnee();
  float abcisse1 = sommets[1].calculerAbcisse();
  float ordonnee1 = sommets[1].calculerOrdonnee();
  //Calcul de l'angle entre entre le segment [s0,s1] et l'horizontal
  Point p(abcisse1-abcisse0,ordonnee1-ordonnee0);
  float angle_entre_sommets_0_et_1 = p.calculerAngle();
  //Rotation de la figure pour ramener [s0,s1] a l'horizontal
  tourner(sommets[0], -angle_entre_sommets_0_et_1);
  //Calcul de la base du triangle 
  float nouvel_abcisse1 = sommets[1].calculerAbcisse();
  float base = abs(abcisse0 - nouvel_abcisse1);
  //Calcul de la hauteur du triangle 
  float ordonnee2 = sommets[2].calculerOrdonnee();
  float hauteur = abs(ordonnee0 - ordonnee2);
  //Rotation inverse pour ramener la figure a sa place initiale
  tourner(sommets[0], angle_entre_sommets_0_et_1);
  float aire = base * hauteur / 2 ;
  return aire; 
}
