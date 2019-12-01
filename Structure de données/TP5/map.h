/**
* \ file map.h
* \ author Jean Goulet (Aut. 2016), modified by Aida Ouangraoua (Aut. 2019)
* \ brief Ce fichier contient les specifications du type map
*/

#ifndef map_h
#define map_h
#include <vector>
#include <string>
#include <cassert>
#include <cmath>

/**
* \ brief Classe map
*/

template <typename Tclef, typename Tvaleur>
  class map{
 public:
  typedef std::pair<const Tclef,Tvaleur> PAIRE;
  //classe d'iterateur
  class iterator;
  friend class iterator;
  
 private:
  // definition du type noeud(encapsulation de chaque element)
  struct noeud{
    PAIRE* CONTENU;
    noeud *PARENT,*GAUCHE,*DROITE;
    int INDICE;
    // constructeurs de noeud
  noeud(const Tclef& c,noeud* PAR,noeud*GAU=nullptr,noeud*DRO=nullptr)
  :PARENT(PAR),GAUCHE(GAU),DROITE(DRO),INDICE(0){
    CONTENU=new PAIRE(c,Tvaleur());}
  noeud():CONTENU(nullptr),PARENT(nullptr),GAUCHE(nullptr),DROITE(nullptr),INDICE(0){}
    // destructeur de noeuds
    ~noeud(){delete CONTENU;CONTENU=nullptr;PARENT=GAUCHE=DROITE=nullptr;}
  };
  noeud *APRES;
  
  //fonctions privees
  noeud*& reference(noeud*);//reference a un noeud*
  void vider(noeud*&);//libere l'espace memoire
  void copier(noeud*,noeud*&,noeud*); // copie vers une reference
  
  bool insert(const Tclef&,noeud*&,iterator&);//ajout general a partir de cle
  bool ajoute_gauche(const Tclef&,noeud*&,iterator&);//ajout dans sous-arbre gauche
  bool ajoute_droite(const Tclef&,noeud*&,iterator&);//ajout dans sous-arbre droit
  
  bool erase(const Tclef&,noeud*&,noeud*&);//suppression general a partir de cle
  bool enleve_gauche(const Tclef&,noeud*&,noeud*&);//supression dans sous-arbre gauche
  bool enleve_droite(const Tclef&,noeud*&,noeud*&);//supression dans sous-arbre droit
  bool eliminer(noeud*&,noeud*&);//suppression fictive
  
  void allonger_a_gauche(noeud*&);//incrementer indice et effectuer rotations au besoin
  void allonger_a_droite(noeud*&);//decrementer indice et effectuer rotations au besoin
  void rotation_gauche_droite(noeud*&);//rotation simple de la gauche vers la droite
  void rotation_droite_gauche(noeud*&);//rotation simple de la droite vers la gauche

  //affichage
  void afficher(noeud*,int,std::vector<std::string>&,double&,int&)const;
  void afficher_barres(std::vector<std::string>&,int)const;
 public:
  
  //initialisation d'iterateurs
  iterator begin()const;
  iterator end()const;

  // constructeurs et destructeur  
  map();
  map(const map&);
  ~map();

  // permutation
  void swap(map&);
  //affectateur
  map& operator=(const map&);
  // liberation de l'espace memoire
  void clear();
  
  //selecteur : taille
  size_t size()const{return APRES->INDICE;}
  //selecteur : est vide ?
  bool empty()const{return APRES->INDICE==0;}

  // premiere occurence d'un élément
  iterator find(const Tclef&)const;
  // premier element >=
  iterator lower_bound(const Tclef&)const;
  // acces par cle 
  Tvaleur& operator[](const Tclef&);
  // acces robuste par cle 
  Tvaleur& at(const Tclef&);
  //insertion par cle
 std::pair<iterator,bool> insert(const Tclef&);
 //insertion avec iterateur a verifier
  iterator insert(iterator,const Tclef&);
  //suppression par cle
  size_t erase(const Tclef&);
  //suppression avec iterateur
  iterator erase(iterator);
  
  
  //fonction de mise au point
  int verifier_hauteurs(noeud* =nullptr)const;
  void afficher()const;
};

/////////////////////////////////////////////////
// map
// iterateur
/////////////////////////////////////////////////

template <typename Tclef, typename Tvaleur>
class map<Tclef,Tvaleur>::iterator{
private:
    noeud* POINTEUR;
    static void avancer(noeud*&);
    static void reculer(noeud*&);
    friend class map<Tclef,Tvaleur>;
public:
    explicit iterator(noeud*p=nullptr):POINTEUR(p){}
    iterator(const iterator&)=default;
    iterator& operator=(const iterator&)=default;
    PAIRE& operator*()const{return *(POINTEUR->CONTENU);}
    PAIRE* operator->()const{return POINTEUR->CONTENU;}
    iterator& operator++(){avancer(POINTEUR);return *this;}                         //++i
    iterator operator++(int){iterator copie(*this);avancer(POINTEUR);return copie;} //i++
    iterator& operator--(){reculer(POINTEUR);return *this;}                         //--i
    iterator operator--(int){iterator copie(*this);reculer(POINTEUR);return copie;} //i--
    bool operator==(const iterator& dr)const{return POINTEUR==dr.POINTEUR;}
    bool operator!=(const iterator& dr)const{return POINTEUR!=dr.POINTEUR;}
};

template <typename Tclef, typename Tvaleur>
typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::begin()const{
    noeud* p=APRES;
    while(p->GAUCHE!=nullptr)p=p->GAUCHE;
    return iterator(p);
}

template <typename Tclef, typename Tvaleur>
typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::end()const{
    return iterator(APRES);
}

//////////////////////////////////////////////////
// fonctions supplementaires de la class iterator
// fonctions statiques, sans paramètre imlicite
/////////////////////////////////////////////////

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::iterator::avancer(noeud*& p){
    //AVANCER le pointeur p vers le prochain noeud
    if(p->DROITE!=nullptr)
        for(p=p->DROITE;p->GAUCHE!=nullptr;p=p->GAUCHE);
    else{
        noeud* pa;
        for(pa=p->PARENT;pa->GAUCHE!=p;p=pa,pa=p->PARENT);
        p=pa;
    }
}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::iterator::reculer(noeud*& p){
    //RECULER le pointeur p vers le noeud precedent
    if(p->GAUCHE!=nullptr)
        for(p=p->GAUCHE;p->DROITE!=nullptr;p=p->DROITE);
    else{
        noeud* pa;
        for(pa=p->PARENT;pa->DROITE!=p;p=pa,pa=p->PARENT);
        p=pa;
    }
}

/////////////////////////////////////////////////
// map
// fonctions privees
/////////////////////////////////////////////////


template <typename Tclef, typename Tvaleur>
typename map<Tclef,Tvaleur>::noeud*& map<Tclef,Tvaleur>::reference(noeud*p){
    if(p==APRES)return APRES;
    else if(p->PARENT->GAUCHE==p)return p->PARENT->GAUCHE;
    else return p->PARENT->DROITE;
}


template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::vider(noeud*& p){
    if(p==nullptr)return;
    vider(p->GAUCHE);
    vider(p->DROITE);
    delete p;
    p=nullptr;
}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::copier(noeud* source,noeud*& dest,noeud* parent){
    if(source==nullptr)return;
    dest=new noeud(*source);
    dest->PARENT=parent;
    dest->INDICE=source->INDICE;
    copier(source->DROITE,dest->DROITE,dest);
    copier(source->GAUCHE,dest->GAUCHE,dest);
}

/////////////////////////////////////////////////
// fonctions privees d'insertion
/////////////////////////////////////////////////

template <typename Tclef, typename Tvaleur>
bool map<Tclef,Tvaleur>::insert(const Tclef& c,noeud*& p,iterator& r){
    if(c<p->CONTENU->first)return ajoute_gauche(c,p,r);
    else if(p->CONTENU->first<c)return ajoute_droite(c,p,r);
    else r=iterator(p);
    return false;
}

template <typename Tclef, typename Tvaleur>
bool map<Tclef,Tvaleur>::ajoute_gauche(const Tclef& c,noeud*& p,iterator& r){
    if(p->GAUCHE==nullptr){         //nouvelle feuille
        p->GAUCHE=new noeud(c,p);
        r=iterator(p->GAUCHE);
        ++APRES->INDICE;
        return(++(p->INDICE)==1);
    }
    else if(insert(c,p->GAUCHE,r)){ //ajout general a gauche
        allonger_a_gauche(p);
        return p->INDICE!=0;
    }
    else return false;
}

template <typename Tclef, typename Tvaleur>
bool map<Tclef,Tvaleur>::ajoute_droite(const Tclef& c,noeud*& p,iterator& r){
    if(p->DROITE==nullptr){         //nouvelle feuille
        p->DROITE=new noeud(c,p);
        r=iterator(p->DROITE);
        ++APRES->INDICE;
        return(--(p->INDICE)==-1);
    }
    else if(insert(c,p->DROITE,r)){ //ajout general a droite
        allonger_a_droite(p);
        return p->INDICE!=0;
    }
    else return false;
}

/////////////////////////////////////////////////
// fonctions privees de suppression
/////////////////////////////////////////////////

template <typename Tclef, typename Tvaleur>
bool map<Tclef,Tvaleur>::erase(const Tclef& c,noeud*& p,noeud*& aRemplacer){
    if(c<p->CONTENU->first)
        return enleve_gauche(c,p,aRemplacer);
    else if(p->CONTENU->first<c)
        return enleve_droite(c,p,aRemplacer);
    else{
        aRemplacer=p;
        return enleve_gauche(c,p,aRemplacer);
    }
}

template <typename Tclef, typename Tvaleur>
bool map<Tclef,Tvaleur>::enleve_gauche(const Tclef& c,noeud*& p,noeud*& aRemplacer){
    if(p->GAUCHE==nullptr)
        return eliminer(p,aRemplacer);
    else if(erase(c,p->GAUCHE,aRemplacer)){
        allonger_a_droite(p);
        return p->INDICE==0;
    }
    else
        return false;
}

template <typename Tclef, typename Tvaleur>
bool map<Tclef,Tvaleur>::enleve_droite(const Tclef& c,noeud*& p,noeud*& aRemplacer){
    if(p->DROITE==nullptr)
        return eliminer(p,aRemplacer);
    else if(erase(c,p->DROITE,aRemplacer)){
        allonger_a_gauche(p);
        return p->INDICE==0;
    }
    else
        return false;
}

template <typename Tclef, typename Tvaleur>
bool map<Tclef,Tvaleur>::eliminer(noeud*& p,noeud*& aRemplacer){
    if(aRemplacer!=nullptr){
        delete aRemplacer->CONTENU;
        aRemplacer->CONTENU=p->CONTENU;
        p->CONTENU=nullptr;
        aRemplacer=p;
        if(p->GAUCHE==nullptr)p=p->DROITE;else p=p->GAUCHE;
        if(p!=nullptr)p->PARENT=aRemplacer->PARENT;
        --APRES->INDICE;
        return true;
    }
    else
        return false;
}

///////////////////////////////////////////////////
// fonctions privees pour la gestion de l'equilibre
///////////////////////////////////////////////////

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::allonger_a_gauche(noeud*& p){
    ++(p->INDICE);
    if(p->INDICE!=2)return;
    if(p->GAUCHE->INDICE==-1)rotation_droite_gauche(p->GAUCHE);
    rotation_gauche_droite(p);
    
}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::allonger_a_droite(noeud*& p){
    --(p->INDICE);
    if(p->INDICE!=-2)return;
    if(p->DROITE->INDICE==1)rotation_gauche_droite(p->DROITE);
    rotation_droite_gauche(p);
}


////////////////////////
// map
// fonctions publiques
////////////////////////

template <typename Tclef, typename Tvaleur>
map<Tclef,Tvaleur>::map():APRES(new noeud){}

template <typename Tclef, typename Tvaleur>
map<Tclef,Tvaleur>::map(const map& source):map(){
    copier(source.APRES->GAUCHE,APRES->GAUCHE,nullptr);
    APRES->INDICE=source.size();
}

template <typename Tclef, typename Tvaleur>
map<Tclef,Tvaleur>::~map(){
    clear();
    delete APRES;
    APRES=nullptr;
}


template <typename Tclef, typename Tvaleur>
map<Tclef,Tvaleur>& map<Tclef,Tvaleur>::operator=(const map& source){
    if(this!=&source){
        map<Tclef,Tvaleur> copie(source);
        this->swap(copie);
    }
    return *this;
}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::swap(map& source){
    std::swap(APRES,source.APRES);
}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::clear(){
    vider(APRES->GAUCHE);
    APRES->INDICE=0;
}


template <typename Tclef, typename Tvaleur>
typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::find(const Tclef& c)const{
    iterator retour=lower_bound(c);

    if(retour==end() || c<retour->first)return end();
    return retour;
}

template <typename Tclef, typename Tvaleur>
Tvaleur& map<Tclef,Tvaleur>::operator[](const Tclef& c){
    auto r=this->insert(c);
    auto i=r.first;
    PAIRE* pa=i.POINTEUR->CONTENU;
    return pa->second;
}

template <typename Tclef, typename Tvaleur>
Tvaleur& map<Tclef,Tvaleur>::at(const Tclef& c){
    auto p=lower_bound(c);
    return p->second;
}


template <typename Tclef, typename Tvaleur>
std::pair<typename map<Tclef,Tvaleur>::iterator,bool> map<Tclef,Tvaleur>::insert(const Tclef& c){
    iterator retour;
    if(APRES->INDICE==0){  //arbre vide
        APRES->GAUCHE=new noeud(c,APRES);
        APRES->INDICE=1;
        return std::make_pair(begin(),true);
    }
    bool valeur=insert(c,APRES->GAUCHE,retour);
    return std::make_pair(retour,valeur);
}


////////////////////////////////
// fonctions de mise au point
////////////////////////////////

template <typename Tclef, typename Tvaleur>
int map<Tclef,Tvaleur>::verifier_hauteurs(noeud* p)const{
    using namespace std;
    if(p==nullptr){ //pour l'appel initial sans parametres
        if(APRES->GAUCHE==nullptr)return true;
        else p=APRES->GAUCHE;
    }
    int hg=0,hd=0;
    if(p->GAUCHE!=nullptr)hg=verifier_hauteurs(p->GAUCHE);
    if(hg==-1)return -1;
    if(p->DROITE!=nullptr)hd=verifier_hauteurs(p->DROITE);
    if(hd==-1)return -1;
    int ind=hg-hd;
    if(ind > 1 || ind < -1){
        cerr<<"le noeud "<<p->CONTENU->first;
        cerr<<" n'est pas en equilibre: gauche="<<hg;
        cerr<<", droite="<<hd<<endl;
        return -1;
    }
    else return 1+std::max(hg,hd);
}


template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::afficher()const{
    using namespace std;
    double total=0.;
    int max=0;
    cout<<"-------------------------------"<<endl;
    vector<string> barres;
    barres.push_back("    ");
    afficher(APRES->GAUCHE,1,barres,total,max);
    total=total/APRES->INDICE;
    cout<<APRES->INDICE<<" element";if(APRES->INDICE>1)cout<<"s";cout<<endl;
    cout<<"log("<<APRES->INDICE<<"): "<<log2(double(APRES->INDICE))<<endl;
    cout<<"hauteur moyenne: "<<total<<endl;
    cout<<"hauteur maximale: "<<max<<endl;
    cout<<"-------------------------------"<<endl;
}


template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::afficher(map<Tclef,Tvaleur>::noeud* p,int niveau,std::vector<std::string>& barres,double& total,int& max)const{
    using namespace std;
    if(p==0)return;
    total+=niveau;
    if(niveau>max)max=niveau;
    if(niveau>=barres.size())barres.push_back("    ");
    
    afficher(p->DROITE,niveau+1,barres,total,max);
    
    //si on est un enfant de gauche arreter les barres a ce niveau
    if(p->PARENT!=0 && p->PARENT->GAUCHE==p)barres[niveau-1]="    ";
    
    //cout<<niveau;
    afficher_barres(barres,niveau);
    cout<<"---> [";
    cout<<p->CONTENU->first<<","<<p->CONTENU->second<<"] ("<<p->INDICE;
    //cout<<", "<<p;
    //cout<<", par="<<p->PARENT;
    //cout<<", gau="<<p->GAUCHE;;
    //cout<<", dro="<<p->DROITE;
    cout<<")"<<endl;
    
    //si on est un enfant de droite barre a mon niveau
    if(p->PARENT->DROITE==p)barres[niveau-1]="   |";
    
    //si on a un enfant a gauche mettre des barres apres
    if(p->GAUCHE!=0)barres[niveau]="   |";
    else barres[niveau]="    ";
    
    //cout<<niveau;
    afficher_barres(barres,niveau+1);
    cout<<endl;
    
    afficher(p->GAUCHE,niveau+1,barres,total,max);
}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::afficher_barres(std::vector<std::string>& barres,int n)const{
    for(int i=0;i<n;++i)std::cout<<barres[i];
}

#include "map2.h"



#endif /* map_h */
