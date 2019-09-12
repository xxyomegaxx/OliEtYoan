/**
* \ file point.cpp
* \ author Aida Ouangraoua
* \ brief Ce fichier contient l'implementation
* des operations du type Point
*/

#include "point.h"

Point::Point(float coord1, float coord2){
  abcisse = coord1;
  ordonnee = coord2;
}

void Point::initDeCoordCartesiennes(float abs, float ord){
  abcisse = abs;
  ordonnee = ord;
}

void Point::initDeCoordPolaires(float rayon, float angle_degres){
  //Conversion de l'angle en radians
  float angle_radians = angle_degres*PI/180;
  //Calcul des coordonnees cartesiennes en fonction des polaires
  if(angle_radians == PI/2 or angle_radians == 3*PI/2)
    abcisse = 0;
  else
    abcisse = rayon * cos(angle_radians);
  if(angle_radians == 0 or angle_radians == PI or angle_radians == 2*PI)
    ordonnee = 0;
  else
    ordonnee = rayon * sin(angle_radians);
}

float Point::calculerAbcisse(){
  return abcisse;
}  

float Point::calculerOrdonnee(){
  return ordonnee;
}  

float Point::calculerRayon(){
  //Calcul du rayon en fonction des coordonnees cartesiennes
  float rayon = sqrt(pow(abcisse,2) + pow(ordonnee,2));
  return rayon;
}

float Point::calculerAngle(){
  //Calcul de l'angle en fonction des coordonnees cartesiennes
  float angle = 0.0;
  if(abcisse == 0){
    if(ordonnee > 0){
      angle = PI/2;
    }
    if(ordonnee < 0){
      angle = 3*PI/2;
    }
  }
  if(abcisse > 0){
    if(ordonnee >= 0){
      angle = atan(ordonnee/abcisse);
    }
    else{
      angle = atan(ordonnee/abcisse) + 2*PI;
    }
  }
  if(abcisse < 0){
    angle = atan(ordonnee/abcisse) + PI;
  }
  angle = angle*180/PI;
  return angle;
}


void Point::translater(Point vector){
  
  abcisse = abcisse + vector.calculerAbcisse();
  ordonnee = ordonnee + vector.calculerOrdonnee();

}
void Point::tournerOrigine(float angle_degres){
  //Calcul des coordonnees polaires (rayon, angle)
  float rayon = calculerRayon();
  float angle = calculerAngle();
  //Application du pivot sur la coordonnee angle
  float nouvel_angle = angle + angle_degres;
  //Re-initialisation du point a partir des nouvelles coordonnees polaires
  initDeCoordPolaires(rayon, nouvel_angle); 
}

void Point::tourner(Point centre, float angle_degres){
  //Translation du point afin que le centre du pivot devienne l'origine
  translater(Point(-centre.calculerAbcisse(), -centre.calculerOrdonnee()));
  //Application du pivot
  tournerOrigine(angle_degres);
  //Translation inverse pour replacer par rapport au centre initial du pivot 
  translater(centre);
}

float Point::calculerDistance(Point p){
  //Calcul de la distance euclidienne
  float  diff_abs,diff_ord;
  diff_abs = abcisse - p.abcisse;
  diff_ord = ordonnee - p.ordonnee;
  float dist = sqrt(pow(diff_abs,2) + pow(diff_ord,2));
  return dist;
}

void Point::afficher(void){
  cout << "(" << abcisse << "," << ordonnee << ")";
}
