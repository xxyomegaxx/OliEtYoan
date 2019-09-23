/**
* \ file cercle.cpp
* \ author Olivier Côté(18116913) et Yoan David(19089763)
* \ brief Ce fichier contient l'implementation
* des operations du type Cercle
*/

#include "cercle.h"

//Constructeur de cercle prenant un point et un rayon
Cercle::Cercle(Point p, float ray): centre(p), rayon(ray) {
}

//translation d'un cercle
void Cercle::translater(Point vector) {
    /*... a completer ...*/
    //translation a parir du centre
    centre.translater(vector);
}

//Tourner un cercle a partir de l'origine
void Cercle::tournerOrigine(float angle_degres){
    /*... a completer ...*/
    centre.tournerOrigine(angle_degres);
}

//Tourner un cercle par rapport a un point
void Cercle::tourner(Point p, float angle_degres){
    /*... a completer ...*/
    centre.tourner(p,angle_degres);
}

//calculer la circonference d'un cercle
float Cercle::calculerCirconference(){
    float  circonference = 2 * PI * rayon;
    return circonference;
}

//calculer l'aire d'un cercle
float Cercle::calculerAire(){
  /*... a completer ...*/
  float aire = PI * pow(rayon,2);
  return aire;
}

//Fonction pour l'affichage d'un cercle donc sont centre et rayon
void Cercle::afficher(void){
  cout << "Centre  ";
  centre.afficher();
  cout << ", Rayon  " << rayon  << endl;;
}
