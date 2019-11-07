/**
* \ file set2.h
* \ author Aida Ouangraoua (Oct. 2019)
* \ brief Ce fichier contient les fonctions 
* \ du type set à coder
*/

#ifndef set2_h
#define set2_h

/////////////////////////////////////////////////
// set
// fonctions privees
/////////////////////////////////////////////////

template <typename TYPE>
typename set<TYPE>::cellule* set<TYPE>::insert(typename set<TYPE>::cellule* ap, const TYPE& X)
{
  /*... a effacer et completer ...*/
  return ap;
}

template <typename TYPE>
typename set<TYPE>::cellule* set<TYPE>::erase(typename set<TYPE>::cellule* C){
  /*... a effacer et completer ...*/
  return C;
}

/////////////////////////////////////////////////
// set
// fonctions publiques
/////////////////////////////////////////////////

template <typename TYPE>
set<TYPE>::set(const set<TYPE>& source) : set()
{
  /*... a completer ...*/
}

template <typename TYPE>
set<TYPE>::~set()
{
  /*... a completer ...*/
}

template <typename TYPE>
void set<TYPE>::clear()
{
  /*... a completer ...*/
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::find(const TYPE& X)
{
  /*... a effacer et completer ...*/
  return iterator();
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::lower_bound(const TYPE& X)
{
  /*... a effacer et completer ...*/
  return iterator();
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::upper_bound(const TYPE& X)
{
  /*... a effacer et completer ...*/
  return iterator();
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::insert(iterator it, const TYPE& X)
{
  /*... a effacer et completer ...*/
  return iterator();
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(const TYPE& VAL)
{
  /*... a effacer et completer ...*/
  return iterator();
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(iterator it)
{
  /*... a effacer et completer ...*/
  return iterator();
}

#endif
