/**
* \ file point.h
* \ author Aida Ouangraoua
* \ brief Ce fichier contient les specifications
* du type Point (Ensemble des points d'un plan)
*/

#ifndef POINT_H
#define POINT_H
#include <iostream>
#include <cmath>

using namespace std;

#define PI 3.14159265

/**
* \ brief Classe Point
*/

class Point
{

 public:
    // constructeurs
    Point(float, float);

    // re-initialisation a partir de coordonnees cartesiennes
    void initDeCoordCartesiennes(float, float);

    // re-initialisation a partir de coordonnees polaires (rayon et angle en degés)
    void initDeCoordPolaires(float, float);

    // calcul de l'abcisse en coordonnees cartesiennes
    float calculerAbcisse();

    // calcul de l'ordonnee en coordonnees cartesiennes
    float calculerOrdonnee();

    // calcul du rayon en coordonnees polaires
    float calculerRayon();

    // calcul de l'angle (degres) en coordonnees polaires
    float calculerAngle();

    // translation suivant un vecteur represente par un Point
    void translater(Point);

    // rotation autour de l'origine du plan suivant un angle donne en degres
    void tournerOrigine(float);

    // rotation autour d'un Point donne suivant un angle donne en degres
    void tourner(Point, float);

    // calcul de la distance entre deux points
    float calculerDistance(Point);

    // affichage des coordonnees
    void afficher(void);
  private:

    // representation
    float abcisse,ordonnee;
};
#endif
