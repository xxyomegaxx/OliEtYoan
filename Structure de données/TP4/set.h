/**
* \ file set.h
* \ author Jean Goulet (Aut. 2016), modified by Aida Ouangraoua (Aut. 2019)
* \ brief Ce fichier contient les specifications du type set
*/

#ifndef set_h
#define set_h

#include <iostream>
#include <sstream>
#include <cstdlib>
#include <vector>
#include <string>
#include <random>
#include <chrono>

using namespace std;

/**
* \ brief Classe set
*/

template <typename TYPE>
class set
{
private:
  // definition du type cellule (encapsulation de chaque element)
  struct cellule
  {
    TYPE CONTENU;
    size_t HAUTEUR;
    cellule** PREC;
    cellule** SUIV;
    // constructeur de cellule
    cellule(TYPE& C, size_t H){CONTENU = C;HAUTEUR = H;SUIV = new cellule*[H];PREC = new cellule*[H];}
    // destructeur de cellule
    ~cellule(){delete [] PREC; delete [] SUIV;}
  };
  cellule* DEBUT;
  size_t SIZE;

  //fonctions generatrices privees  
  static size_t tirer_hauteur_au_hasard(); //tirer au hasard la hauteur d'une  cellule
  cellule* insert(cellule*,const TYPE&); //insertion avant cette cellule 
  cellule* erase(cellule*); //supression de cette cellule

 public:
  //classes d'iterateur de set
  class iterator;

  //initialisation d'iterateurs
  iterator begin() const;
  iterator end() const;

  // constructeurs et destructeur  
  set();
  set(const set&);
  ~set();

  // permutation
  void swap(set&);
  //affectateur
  void operator=(const set&);
  // liberation de l'espace memoire
  void clear();

  //selecteur : taille
  size_t size()const;
  //selecteur : est vide ?
  bool empty()const;

  // nombre d'occurrences d'un élément (0 ou 1)
  size_t count(const TYPE&)const;
  // première occurence d'un élément
  iterator find(const TYPE&);
  // premier élément >=
  iterator lower_bound(const TYPE&);
  // premier élément >
  iterator upper_bound(const TYPE&);

  //insertion avec iterateur a verifier
  std::pair<iterator, bool> insert(const TYPE&);
  //insertion par valeur seulement
  iterator insert(iterator, const TYPE&);
  //suppression par valeur
  iterator erase(const TYPE&);
  //suppression avec iterateur
  iterator erase(iterator);

  //affichage
  template < typename T > static string to_string(const T& n) {
    ostringstream stm;
    stm << n;
    return stm.str();
  }
  void afficher(string = "")const;
};


/////////////////////////////////////////////////
// set
// iterateur
/////////////////////////////////////////////////

template <typename TYPE>
class set<TYPE>::iterator
{
  friend class set<TYPE>;
 private:
  cellule* POINTEUR;
 public:
 iterator(cellule*p = nullptr) :POINTEUR(p) {}
  TYPE& operator*()const { return POINTEUR->CONTENU;}//*i
  iterator& operator++(){POINTEUR=POINTEUR->SUIV[0];return *this;}     //++i
  iterator operator++(int){iterator it(POINTEUR); POINTEUR=POINTEUR->SUIV[0]; return it;}  //i++
  iterator& operator--(){POINTEUR=POINTEUR->PREC[0];return *this;}     //--i
  iterator operator--(int){iterator it(POINTEUR);POINTEUR=POINTEUR->PREC[0];return it;};  //i--
  bool operator==(const iterator&i2)const { return POINTEUR == i2.POINTEUR; }
  bool operator!=(const iterator&i2)const { return POINTEUR != i2.POINTEUR; }
};

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::begin() const
{
    return iterator(DEBUT->SUIV[0]);
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::end() const
{
    return iterator(DEBUT->PREC[0]);
}


/////////////////////////////////////////////////
// set
// fonctions privees
/////////////////////////////////////////////////

template <typename TYPE>
size_t set<TYPE>::tirer_hauteur_au_hasard()
{
    //tirer au hasard le nombre de couches
    //prob 1/2 d'avoir 1
    //prob 1/4 d'avoir 2
    //prob 1/8 d'avoir 3 etc.
    //
    // Si vous voulez générer la même séquence pour vos tests, vous pouvez modifier la valeur de seed pour qu'elle soit égale à 0
    // static auto seed = 0;
    static auto seed = chrono::system_clock::now().time_since_epoch().count();
    static minstd_rand0 generator(static_cast<unsigned int>(seed));
    size_t i = 1;
    auto g = generator();
    for (; g % 2 != 0; ++i)
        g /= 2;
    return i;
}


/////////////////////////////////////////////////
// set
// fonctions publiques
/////////////////////////////////////////////////

template <typename TYPE>
set<TYPE>::set()
{
    SIZE = 0;
    TYPE X;
    DEBUT = new cellule(X,1);
    cellule* FIN = new cellule(X,1);
    DEBUT->PREC[0] = FIN;
    DEBUT->SUIV[0] = FIN;
    FIN->PREC[0] = DEBUT;
    FIN->SUIV[0] = nullptr;
}

template <typename TYPE>
void set<TYPE>::swap(set<TYPE>& source)
{
    std::swap(SIZE, source.SIZE);
    std::swap(DEBUT, source.DEBUT);
}

template <typename TYPE>
void set<TYPE>::operator=(const set<TYPE>& source)
{
    if (this != &source)
    {
        set copie(source);
        swap(copie);
    }
}


template <typename TYPE>
size_t set<TYPE>::size()const
{
    return SIZE;
}

template <typename TYPE>
bool set<TYPE>::empty()const
{
    return SIZE == 0;
}

template <typename TYPE>
size_t set<TYPE>::count(const TYPE& t)const
{
    set<TYPE>::iterator it = find(t);
    if (it == end())
        return 0;
    else
        return 1;
}

template <typename TYPE>
std::pair<typename set<TYPE>::iterator, bool> set<TYPE>::insert(const TYPE& X)
{
    iterator it = lower_bound(X);
    if (it == end() || X < *it)
        return std::make_pair(iterator(insert(it.POINTEUR, X)), true);
    else
        return std::make_pair(it, false);
}


///////////////////////////////////////////////////
// affichage
// fonctionnel uniquement pour les types primitifs
// et les string (max 4 caracteres par element)
///////////////////////////////////////////////////

template <typename TYPE>
string vers_string(TYPE VAL)
{
    return set<TYPE>::to_string(VAL);
}

//cas specialise double
template <>
string vers_string<double>(double VAL)
{
    string retour = set<double>::to_string(VAL);
    if (retour.find('.') == string::npos)
        return retour;
    for (auto i = retour.rbegin(); i != retour.rend(); ++i)
        if (*i == '0')
            retour.pop_back();
        else
            break;
    if (retour.back() == '.')
        retour.pop_back();
    return retour;
}

//cas specialise float
template <>
string vers_string<float>(float VAL)
{
    return vers_string(double(VAL));
}

//cas specialise string
template <>
string vers_string<string>(string VAL)
{
    return VAL;
}

template <typename TYPE>
void set<TYPE>::afficher(string st)const
{
    cout << endl;
    cout << st << "------------------------------------------" << endl;
    if (DEBUT == nullptr)
    {
        cout << "le set a ete detruit" << endl;
        return;
    }
    size_t NBNIV = DEBUT->HAUTEUR;
    string une_clef, lignes, clefs;
    TYPE elem;
    cellule *p;
    lignes = set<TYPE>::to_string(SIZE) + " elements";
    for (size_t nb = NBNIV; nb>0;)
    {
        string clefs = " ";
        nb--;
        //afficher toute la couche nb
        cout << lignes << endl;
        lignes = "|";
        for (iterator it = begin(); it != end(); ++it)
        {
            if (it.POINTEUR->HAUTEUR>nb)
            {
                p = it.POINTEUR;
                elem = p->CONTENU;
                une_clef = "-----" + vers_string(elem);
                clefs += une_clef.substr(une_clef.size() - 6, 6);
                lignes += "     |";
            }
            else
            {
                clefs += "------";
                lignes += "      ";
            }
        }
        clefs += "--";
        cout << clefs << endl;
        lignes += "  |";
    }
    cout << endl << endl;
}

//Inclusion de set2.h
#include "set2.h"

#endif /* set_h */
