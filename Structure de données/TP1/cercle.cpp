/**
* \ file cercle.cpp
* \ author Aida Ouangraoua
* \ brief Ce fichier contient l'implementation
* des operations du type Cercle
*/

#include "cercle.h"

Cercle::Cercle(Point p, float ray): centre(p), rayon(ray) {
}

void Cercle::translater(Point vector) {
    /*... a completer ...*/
    centre.translater(vector);
}

void Cercle::tournerOrigine(float angle_degres){
    /*... a completer ...*/
    centre.tournerOrigine(angle_degres);
}

void Cercle::tourner(Point p, float angle_degres){
    /*... a completer ...*/
    centre.tourner(p,angle_degres);
}

float Cercle::calculerCirconference(){
    float  circonference = 2 * PI * rayon;
    return circonference;
}

float Cercle::calculerAire(){
  /*... a completer ...*/
  float aire = PI * pow(rayon,2);
  return aire;
}

void Cercle::afficher(void){
  cout << "Centre  ";
  centre.afficher();
  cout << ", Rayon  " << rayon  << endl;;
}
