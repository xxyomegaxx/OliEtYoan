/**
* \ file vector.h
* \ author Jean Goulet (Sept. 2016), modified by Aida Ouangraoua (Sept. 2019)
* \ brief Ce fichier contient les specifications du type vector
*/

#ifndef _vector_h
#define _vector_h

#include <cassert>
#include <iostream>
using namespace std;

/**
* \ brief Classe vector
*/

template <typename TYPE>
class vector
{
 private:
  TYPE *DEBUT, *FIN_DIM, *FIN_CAP;
  
 public:
  // constructeurs et destructeur
  vector();
  vector(size_t);
  vector(const vector&);
  ~vector();
  
  // permutation
  void swap(vector&);
  //affectateur
  void operator=(const vector&);
  //changement de dimension
  void resize(size_t);
  //augmentation de capacite
  void reserve(size_t);
  // mise a zero de la capacite
  void clear();
  
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
ostream& operator<<(ostream&, const vector<TYPE>& V);

template <typename TYPE>
vector<TYPE>::vector() :vector(0)
{
}

template <typename TYPE>
vector<TYPE>::vector(const vector& source) :vector()
{
  resize(source.size());
  for (size_t i = 0; i<size(); i++)
    DEBUT[i] = TYPE(source[i]);
}

template <typename TYPE>
vector<TYPE>::~vector()
{
  clear();
}

template <typename TYPE>
void vector<TYPE>::swap(vector& v)
{
  std::swap(DEBUT, v.DEBUT);
  std::swap(FIN_DIM, v.FIN_DIM);
  std::swap(FIN_CAP, v.FIN_CAP);
}

template <typename TYPE>
void vector<TYPE>::operator=(const vector& source)
{
  vector copie(source);
  swap(copie);
}
  
template <typename TYPE>
  void vector<TYPE>::resize(size_t nDIM)
  {
    reserve(nDIM);  
    FIN_DIM = DEBUT + nDIM;
  }

template <typename TYPE>
void vector<TYPE>::clear()
{
  delete[] DEBUT;
  DEBUT = FIN_DIM = FIN_CAP = nullptr;
}

template <typename TYPE>
void vector<TYPE>::push_back(const TYPE& x)
{
  size_t DIM = size();
  size_t CAP = FIN_CAP - DEBUT;
  if (DIM == CAP)
  {
	  reserve((DIM + 1) * 2);
  }   
  resize(DIM + 1);
  DEBUT[DIM] = x;
}

template <typename TYPE>
void vector<TYPE>::pop_back()
{
  resize(size() - 1);
}

template <typename TYPE>
size_t vector<TYPE>::size()const
{
  return FIN_DIM - DEBUT;
}

template <typename TYPE>
bool vector<TYPE>::empty()const
{
  return DEBUT == FIN_DIM;
}

template <typename TYPE>
TYPE& vector<TYPE>::at(size_t i)
{
  if (i >= size())
    throw std::out_of_range("vecteur index out of range.");
  else
    return DEBUT[i];
}
template <typename TYPE>
const TYPE& vector<TYPE>::at(size_t i)const
{
  if (i >= size())
    throw std::out_of_range("vecteur index out of range.");
  else
    return DEBUT[i];
}

template <typename TYPE>
void vector<TYPE>::afficher()const
{
  size_t DIM = size();
  size_t CAP = FIN_CAP - DEBUT;
  cout << endl;
  cout << "DIM: " << DIM  << endl;
  cout << "CAP: " << CAP <<  endl;
  for (size_t i = 0; i<DIM; i++)
    cout << " " << i << ": " << at(i) << endl;
  
}

template <typename TYPE>
ostream& operator<<(ostream& out, const vector<TYPE>& V)
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

//inclusion de vector2.h 
#include "vector2.h"

#endif
