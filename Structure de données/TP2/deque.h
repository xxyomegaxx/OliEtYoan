/**
* \ file deque.h
* \ author Aida Ouangraoua (Sept. 2019)
* \ brief Ce fichier contient les specifications du type deque
*/

#ifndef _deque_h
#define _deque_h

#include <cassert>
#include <iostream>
using namespace std;

/**
 * \ brief Classe deque
 */

template <typename TYPE>
class deque
{
 private:
  TYPE *DEBUT_CAP, *FIN_CAP, *FRONT, *BACK;
  
 public:
  // constructeurs et destructeur
  deque();
  deque(size_t);
  deque(const deque&);
  ~deque();
  
  // permutation
  void swap(deque&);
  //affectateur
  void operator=(const deque&);
  //changement de dimension
  void resize(size_t);
  //augmentation de capacite
  void reserve(size_t);
  // mise a zero de la capacite
  void clear();
  
  //ajouter element au debut
  void push_front(const TYPE&);
  //retirer premier element
  void pop_front();
  //ajouter element a la fin
  void push_back(const TYPE&);
  //retirer dernier element
  void pop_back();
  
  //selecteur : taille
  size_t size()const;
  //selecteur : est vide ?
  bool empty()const;
  //selecteur : dernier element
  TYPE& back();
  //selecteur const : dernier element
  const TYPE& back()const;
  //selecteur : premier element
  TYPE& front();
  //selecteur const : premier element
  const TYPE& front()const;
  //selecteur : ieme element
  TYPE& operator[](size_t);
  //selecteur const : ieme element
  const TYPE& operator[](size_t)const;
  //selecteur robuste : ieme element
  TYPE& at(size_t);
  //selecteur robuste const : ieme element
  const TYPE& at(size_t)const;
  //affichage
  void afficher()const;
};

template <typename TYPE>
ostream& operator<<(ostream&, const deque<TYPE>& );

template <typename TYPE>
deque<TYPE>::deque() :deque(0)
{
}

template <typename TYPE>
deque<TYPE>::~deque()
{
  clear();
}

template <typename TYPE>
void deque<TYPE>::resize(size_t nDIM)
{
  reserve(nDIM);
  size_t CAP = FIN_CAP-DEBUT_CAP;
  if(nDIM == 0)
    FRONT = BACK = nullptr;
  else{
    if(FRONT == nullptr)
      FRONT = DEBUT_CAP;
    BACK = DEBUT_CAP + (((FRONT-DEBUT_CAP)+nDIM)%(CAP+1));
  }
}

template <typename TYPE>
void deque<TYPE>::pop_front()
{
  if(FRONT == BACK)
    FRONT = BACK = nullptr;
  else{
    if(FIN_CAP - FRONT > 1)
      FRONT = FRONT + 1;
    else
      FRONT = DEBUT_CAP;
  }
}

template <typename TYPE>
void deque<TYPE>::pop_back()
{
  resize(size() - 1);
}

template <typename TYPE>
TYPE& deque<TYPE>::back()
{
  return *(BACK);
}

template <typename TYPE>
const TYPE& deque<TYPE>::back()const
{
  return *(BACK);
}

template <typename TYPE>
TYPE& deque<TYPE>::front()
{
  return *FRONT;
}

template <typename TYPE>
const TYPE& deque<TYPE>::front()const
{
  return *FRONT;
}

template <typename TYPE>
void deque<TYPE>::afficher()const
{
  size_t DIM = size();
  size_t CAP = FIN_CAP - DEBUT_CAP;
  cout << endl;
  cout << "DIM: " << DIM  << endl;
  cout << "CAP: " << CAP <<  endl;
  for (size_t i = 0; i<DIM; i++)
    cout << " " << i << ": " << at(i) << endl;
  
}

template <typename TYPE>
ostream& operator<<(ostream& out, const deque<TYPE>& V)
{
  size_t S = V.size();
  char delim = '[';
  if(S== 0)
    out << delim;
  for (size_t i = 0; i<S; i++)
    {
      out << delim << V[i];
      delim = ',';
    }
  out << "]";
  return out;
}

//Inclusion de deque2.h
#include "deque2.h"

#endif
