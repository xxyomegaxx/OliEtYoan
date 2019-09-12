/**
* \ file triangle.cpp
* \ author Olivier Cote et Yoan David
* \ brief Ce fichier contient l'implementation
* des operations specifiques au type Rectangle
*/

#include "rectangle.h"


Rectangle::Rectangle(Point p1, Point p2, Point p3, Point p4){
    nb_sommets = 4;
    sommets[0] = p1;
    sommets[1] = p2;
    sommets[2] = p3;
    sommets[3] = p4;
}

float Rectangle::calculerAire(void){

    // float abcisse0 = sommets[0].calculerAbcisse();
    // float ordonnee0 = sommets[0].calculerOrdonnee();
    // float abcisse1 = sommets[1].calculerAbcisse();
    // float ordonnee1 = sommets[1].calculerOrdonnee();
    // float abcisse2 = sommets[2].calculerAbcisse();
    // float ordonnee2 = sommets[2].calculerOrdonnee();


    // int dist1 = sommets[0].calculerDistance(sommets[1]);
    // int dist2 = sommets[0].calculerDistance(sommets[2]);
    // int dist3 = sommets[1].calculerDistance(sommets[2]);
    //
    // int allDist[3] = {dist1,dist2,dist3};
    //
    // int smallest;
    // int secondSmallest;
    //
    // if(allDist[0] < allDist[1]){
    //     allDist[0] = smallest;
    //     allDist[1] = secondSmallest;
    // }
    // else {
    //     allDist[0] = secondSmallest;
    //     allDist[1] = smallest;
    // }
    //
    // if(smallest > allDist[2]){
    //     secondSmallest = smallest;
    //     smallest = allDist[2];
    //
    // }
    //
    // float aire = smallest * secondSmallest;
    //
    // return aire;

}
