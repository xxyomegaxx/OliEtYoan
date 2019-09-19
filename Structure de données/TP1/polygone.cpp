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
  for(int i = 0; i < nb_sommets; i++){
    sommets[i].translater(vector);
  }

}

void Polygone::tournerOrigine(float angle_degres){
  /*... a completer ...*/
  for(int i = 0; i < nb_sommets; i++){
    sommets[i].tournerOrigine(angle_degres);
  }

}

void Polygone::tourner(Point p, float angle_degres){
  /*... a completer ...*/
  for(int i = 0; i < nb_sommets; i++){
    sommets[i].tourner(p,angle_degres);
  }
}

float Polygone::calculerCirconference(){
  float  circonference;
  /*... a completer ...*/
  for(int i = 0; i <= nb_sommets-1; i++){
    if(i == nb_sommets-1){
      circonference += sommets[i].calculerDistance(sommets[0]);
    }
    else{
     circonference += sommets[i].calculerDistance(sommets[i+1]);
    }
  }

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
