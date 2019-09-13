/**
* \ file polygone.cpp
* \ author Aida Ouangraoua
* \ brief Ce fichier contient l'implementation
* des operations du type Polygone
*/

#include "polygone.h"

Polygone::Polygone() : Polygone(0){
}

Polygone::Polygone(int nb) : nb_sommets(nb){
}

void Polygone::translater(Point vector){
  /*... a completer ...*/
}

void Polygone::tournerOrigine(float angle_degres){
  /*... a completer ...*/
}

void Polygone::tourner(Point p, float angle_degres){
  /*... a completer ...*/
}

float Polygone::calculerCirconference(){
  float  circonference = 0.0;
  /*... a completer ...*/
  return circonference;
}

void Polygone::afficher(void){
  cout << "Sommets ";
  for(int i = 0; i < nb_sommets; i++){
    cout << " ";
    sommets[i].afficher();
  }
  cout << endl;

}
