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
	friend class list<TYPE>;
private:
	cellule* POINTEUR;
public:
	reverse_iterator(cellule*C = nullptr) :POINTEUR(C) {}
	TYPE& operator*()const { return POINTEUR->CONTENU; } //*i
	TYPE* operator->()const { return &(POINTEUR->CONTENU); } //i->
	iterator& operator++() { POINTEUR = POINTEUR->PREC; return *this; } //++i
	iterator operator++(int) { iterator it(*this); POINTEUR = POINTEUR->PREC; return it; } //i++
	iterator& operator--() { POINTEUR = POINTEUR->SUIV; return *this; } //--i
	iterator operator--(int) { iterator ret(*this); POINTEUR = POINTEUR->SUIV; return ret; } //i--  
	bool operator==(const iterator& IT)const {
		return POINTEUR == IT.POINTEUR;
	}
	bool operator!=(const iterator& IT)const {
		return POINTEUR != IT.POINTEUR;
	}
};

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::rbegin(){
  return reverse_iterator(DEBUT->PREC->PREC);
}

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::rend(){
  return reverse_iterator(DEBUT);
}

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::insert(reverse_iterator i,const TYPE& x){
  return reverse_iterator(insert(i.POINTEUR,x));
}

template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::erase(reverse_iterator i){
  return reverse_iterator(erase(i.POINTEUR));;
}

template <typename TYPE>
void list<TYPE>::operator=(list<TYPE>& L){
	clear();
	iterator it(L.begin());
	TYPE *c;
	while (it != L.end())
	{
		c = new TYPE(it.POINTEUR->CONTENU);
		push_back(*c);
		it++;
	}
}

template <typename TYPE>
void list<TYPE>::resize(size_t N,const TYPE& X){
	iterator it(DEBUT);
	int initSize = SIZE;
	if(N<SIZE && N>0)
	{
		for (int i = 0; i <= N; i++)it++;
		while (it != end()) it = erase(it);
	}
	if (N > SIZE)
	{
		for (int i = 0; i < N - initSize; i++)push_back(X);
	}
}

template <typename TYPE>
void list<TYPE>::splice(iterator i,list& L){
	i.POINTEUR->PREC->SUIV = L.DEBUT->SUIV;
	L.DEBUT->SUIV->PREC = i.POINTEUR->PREC;
	i.POINTEUR->PREC = L.DEBUT->PREC->PREC;
	L.DEBUT->PREC->PREC->SUIV = i.POINTEUR;

	L.DEBUT->SUIV = L.DEBUT->PREC;
	L.DEBUT->SUIV->PREC = L.DEBUT;
	SIZE += L.SIZE;
	L.SIZE = 0;
  
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
