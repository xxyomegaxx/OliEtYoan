/**
* \ file cercle.cpp
* \ author Aida Ouangraoua
* \ brief Ce fichier contient l'implementation
* des operations du type Cercle
*/

#include "cercle.h"

Cercle::Cercle(Point p, float ray) : centre(p), rayon(ray){
}

void Cercle::translater(Point vector){
    /*... a completer ...*/
}

void Cercle::tournerOrigine(float angle_degres){
    /*... a completer ...*/
}

void Cercle::tourner(Point p, float angle_degres){
    /*... a completer ...*/
}

float Cercle::calculerCirconference(){
    float  circonference = 2 * PI * rayon;
    return circonference;
}

float Cercle::calculerAire(){
  float  aire = 0.0;
  /*... a completer ...*/
  return aire;
}

void Cercle::afficher(void){
  cout << "Centre  ";
  centre.afficher();
  cout << ", Rayon  " << rayon  << endl;;
}
