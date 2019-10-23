/**
* \ file list2.h
* \ author Aida Ouangraoua (Oct. 2019)
* \ brief Ce fichier contient les fonctions 
* \ du type list à coder
*/

#ifndef list2_h
#define list2_h

template <typename TYPE>
typename list<TYPE>::cellule* list<TYPE>::insert(cellule* C,const TYPE& X){
	cellule *nouveau = new cellule(X);
	nouveau->PREC = C->PREC;
	nouveau->SUIV = C;
	C->PREC->SUIV = nouveau;
	C->PREC = nouveau;
	SIZE++;
    return nouveau;
}

template <typename TYPE>
typename list<TYPE>::cellule* list<TYPE>::erase(cellule* C){
  /*... a effacer et completer ...*/
	cellule *retour = C->SUIV;
	C->PREC->SUIV = C->SUIV;
	C->SUIV->PREC = C->PREC;
	delete C;
	SIZE--;
  return retour;
}


template <typename TYPE>
class list<TYPE>::reverse_iterator{
  /*... a effacer et completer ...*/
  int ATTRIBUT;
};

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::rbegin(){
  /*... a effacer et completer ...*/
  return reverse_iterator();
}

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::rend(){
  /*... a effacer et completer ...*/
  return reverse_iterator();
}

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::insert(reverse_iterator i,const TYPE& x){
  /*... a effacer et completer ...*/
  return reverse_iterator();
}

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::erase(reverse_iterator i){
  /*... a effacer et completer ...*/
  return reverse_iterator();
}

template <typename TYPE>
void list<TYPE>::operator=(list<TYPE>& L){
  /*... a completer ...*/
}

template <typename TYPE>
void list<TYPE>::resize(size_t N,const TYPE& X){
  /*... a completer ...*/
}

template <typename TYPE>
void list<TYPE>::splice(iterator i,list& L){
  /*... a completer ...*/
}

template <typename TYPE>
void list<TYPE>::reverse(){
  /*... a completer ...*/
}

  template <typename TYPE>
void list<TYPE>::sort(iterator DEB,iterator FIN){
  /*... a completer ...*/
}

#endif
