/**
* \ file main.cpp
* \ author Aida Ouangraoua
* \ brief Ce fichier contient des tests des opérations
* des types Point, Cercle, Polygone, Rectangle, Triangle
* et Plan
*/


#include "cercle.h"
#include "rectangle.h"
#include "triangle.h"
#include "plan.h"

int main(){

  //Test de Rectangle
  cout << "Test de Rectangle " << endl ;
  cout << "=============" << endl ;


  // Creation d'un rectangle
  Rectangle r(Point(1,1),Point(4,1),Point(4,3),Point(1,3));
  cout << "r : " ;
  r.afficher();
  cout << endl<< endl;

  //Aire
  cout << "Aire de r : "  << r.calculerAire() << endl ;
  cout << endl<< endl;

  //Translation rectangle
  cout << "Translation vers Point(3,1) :" <<endl;
  r.translater(Point(3,1));
  r.afficher();
  cout << endl<< endl;

  //Tourner a l'origine pour un rectangle
  r.tournerOrigine(90);
  cout << "p1 apres tournerOrigine(90): " ;
  r.afficher();
  cout << endl<< endl;

  //Rotation autour d'un Point donne
  r.tourner(Point(0,2),-180);
  cout << "r apres tourner(Point(0,2),-180): " ;
  r.afficher();
  cout << endl<< endl;




  //Test de Point
  cout << "Test de Point " << endl ;
  cout << "==============" << endl ;

  //Creation de points
  Point p1(1,1) ,p2(1,2);
  cout << "p1 : " ;
  p1.afficher();
  cout << endl;
  cout << "p2 : " ;
  p2.afficher();
  cout << endl<< endl;

  //Initialisation a partir de coordonnes cartesiennes
  p1.initDeCoordCartesiennes(1,2);
  cout << "p1 apres p1.initDeCoordCartesiennes(1,2): " ;
  p1.afficher();
  cout << endl<< endl;

  //Initialisation a partir de coordonnes polaires
  p1.initDeCoordPolaires(sqrt(2), 135);
  cout << "p1 apres p1.initDeCoordPolaires(sqrt(2), 135): " ;
  p1.afficher();
  cout << endl<< endl;



  //Translation suivant un vecteur
  p1.translater(Point(3,1));
  cout << "p1 apres translater(Point(3,1)): " ;
  p1.afficher();
  cout << endl<< endl;

  //Rotation autour de l'origine du plan
  p1.tournerOrigine(90);
  cout << "p1 apres tournerOrigine(90): " ;
  p1.afficher();
  cout << endl<< endl;

  //Rotation autour d'un Point donne
  p1.tourner(Point(0,2),-180);
  cout << "p1 apres tourner(Point(0,2),-180): " ;
  p1.afficher();
  cout << endl<< endl;

  //Distance entre deux Points
  cout << "Distance entre p1 et p2 : " <<  p1.calculerDistance(p2) << endl ;
  cout << endl<< endl;

  //Test de Cercle
  cout << "Test de Cercle " << endl ;
  cout << "===============" << endl ;

  //Creation de cercle
  Cercle c1(Point(-1,1),2);
  cout << "c1 : " ;
  c1.afficher();
  cout << endl;

  //Translation suivant un vecteur
  c1.translater(Point(3,1));
  cout << "c1 apres translater(Point(3,1)): " ;
  c1.afficher();
  cout << endl;

  //Rotation autour de l'origine du plan
  c1.tournerOrigine(90);
  cout << "c1 apres tournerOrigine(90): " ;
  c1.afficher();
  cout << endl;

  //Rotation autour d'un Point donne
  c1.tourner(Point(0,2),-180);
  cout << "c1 apres tourner(Point(0,2),-180): ";
  c1.afficher();
  cout << endl;

  //Circonference
  cout << "Circonference de c1 : "  << c1.calculerCirconference() << endl ;
  cout << endl;

  Cercle c3(Point(2,2),4);
  //Circonference
  cout << "Circonference de c3 : "  << c3.calculerCirconference() << endl ;
  cout << endl;


  //Circonference
  cout << "Aire de c1 : "  << c1.calculerAire() << endl ;
  cout << endl;

  //Test de Triangle
  cout << "Test de Triangle " << endl ;
  cout << "=================" << endl ;


  //Creation de triangle
  Triangle t1(Point(0,0), Point(1,-1), Point(1,1));
  cout << "t1 : " ;
  t1.afficher();
  cout << endl;

  //Translation suivant un vecteur
  t1.translater(Point(3,1));
  cout << "t1 apres translater(Point(3,1)): "  ;
  t1.afficher();
  cout << endl;

  //Rotation autour de l'origine du plan
  t1.tournerOrigine(90);
  cout << "t1 apres tournerOrigine(90): "   ;
  t1.afficher();
  cout << endl;

  //Rotation autour d'un Point donne
  t1.tourner(Point(0,2),-180);
  cout << "t1 apres tourner(Point(0,2),-180): "   ;
  t1.afficher();
  cout << endl;

  //Circonference
  cout << "Circonference de t1 : "  << t1.calculerCirconference() << endl ;
  cout << endl;

  //Aire
  cout << "Aire de t1 : "  << t1.calculerAire() << endl ;
  cout << endl;


  //Test de Plan
  cout << "Test de Plan " << endl ;
  cout << "=============" << endl ;

  //Creation de plan
  Plan p;
  cout << "p : " ;
  p.afficher();
  cout << endl;




  //Ajout de figures
  p.ajouterFigure(&c1);


  Cercle c2(p2,4);
  p.ajouterFigure(&c2);

  // p.ajouterFigure(&t1);

  // Triangle t2(Point(0,0), Point(1,0), Point(0,1));
  // p.ajouterFigure(&t2);
  cout << "p apres p.ajouter_figure(...) pour &c1, &t1, et &t2 : " ;
  p.afficher();
  cout << endl;

  // //Ajout de figure avec capacite atteinte
  // Cercle c2(p2,2);
  // cout << "tentative d'ajout de figure avec capacite max atteinte : " ;
  // p.ajouterFigure(&c2);
  // cout << endl;


  cout << "Circonference totale : " << p.calculerCirconference();
  cout << endl;

  //Suppression de figure a position non atteinte
  cout << "tentative de suppression en dehors de dimension :  p.supprimer_figure(5) :" ;
  p.supprimerFigure(5);
  cout << endl;

  //Suppression de figure
  p.supprimerFigure(1);
  cout << "p apres p.supprimer_figure(1) : " ;
  p.afficher();
  cout << endl;

  //Ajout de figure
  p.ajouterFigure(&c2);
  cout << "p apres p.ajouter_figure(&c2) : " ;
  p.afficher();
  cout << endl;

  return 0;





}
