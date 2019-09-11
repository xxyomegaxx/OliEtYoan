/**
* \ file plan.cpp
* \ author Aida Ouangraoua
* \ brief Ce fichier contient l'implementation
* des operations du type Plan
*/


#include <typeinfo>
#include "plan.h"

Plan::Plan() : nb_figures(0){
  for(int i = 0; i < MAXFIGURES; i++){
    figures[i] = nullptr;
    }
}

void Plan::ajouterFigure(Figure *f){
  if(nb_figures < MAXFIGURES){
    figures[nb_figures] = f;
    nb_figures += 1;
  }
  else{
    cout << "Figure non ajoutee : capacite maximum atteinte (" << MAXFIGURES << ")" << endl;
  }
}
  
void Plan::supprimerFigure(int position){
  if(position < nb_figures){
    for(int i = position; i < nb_figures-1; i++){
      figures[i] = figures[i+1];
    }
    figures[nb_figures-1] = nullptr;
    nb_figures -= 1;
  }
  else{
    cout << "Pas de figure a la position indiquee (" << position << ")" << endl;
  }
}

void Plan::translater(Point vector){
  for(int i = 0; i < nb_figures; i++){
    figures[i]->translater(vector);
  }
}

void Plan::tournerOrigine(float angle_degres){
  /*... a completer ...*/
}

void Plan::tourner(Point p, float angle_degres){
  /*... a completer ...*/
}

float Plan::calculerCirconference(){
  float  circonference = 0.0;
  /*... a completer ...*/
  return circonference;
}

float Plan::calculerAire(){
  float  aire = 0.0;
  /*... a completer ...*/
  return aire;
}

void Plan::afficher(void){
  cout << nb_figures << " figure(s) : "<< endl;
  for(int i = 0; i < nb_figures; i++){
    cout << "position "<< i << " : Type " << typeid(*figures[i]).name() << " : ";
    figures[i]->afficher();
  }
}
