/**
* \ file list2.h
* \ author Aida Ouangraoua (Oct. 2019)
* \ brief Ce fichier contient les fonctions 
* \ du type list
*/

#ifndef list2_h
#define list2_h

/**
* \ brief Fonction d'insertion d'une certaine cellule à l'aide d'un pointeur vers une cellule a 
* \ une certaine position.
* \ param [in] Un pointeur vers une structure de type cellule
* \ 		   Un liste TYPE X 	
* \ return return Le position de l'element de la cellule insere
*/
template <typename TYPE>
typename list<TYPE>::cellule* list<TYPE>::insert(cellule* C,const TYPE& X){
	//Creation d'une cellule contenant la valeur X
	cellule *nouveau = new cellule(X);
	//Associe le precedent de la nouvelle cellule au precedent de la position a laquelle on veut inserer.
	nouveau->PREC = C->PREC;
	//Associe le suivant de la nouvelle cellule au suivant de la position a laquelle on veut inserer.
	nouveau->SUIV = C;
	//Change la position du suivant du precedent de C par nouveau
	C->PREC->SUIV = nouveau;
	//Change la position du precedent de C par nouveau
	C->PREC = nouveau;
	//Incremente la dimension de la list de 1
	SIZE++;
	//Retourne la position de la nouvelle cellule
    return nouveau;
}
/**
* \ brief Fonction de suppression d'une certaine cellule à l'aide d'un pointeur vers une cellule
* \ param [in] Un pointeur vers une structure de type cellule
* \ return Le position de l'element suivant de la cellule supprime
*/
template <typename TYPE>
typename list<TYPE>::cellule* list<TYPE>::erase(cellule* C){
  /*... a effacer et completer ...*/
	//Le contenu de la l'element suivant de la cellule C.
	cellule *retour = C->SUIV;
	//Associe le suivant de la cellule au suivant de son precedent.
	C->PREC->SUIV = C->SUIV;
	//Associe le precedent de la cellule au precedent de son suivant.
	C->SUIV->PREC = C->PREC;
	//Effectue la suppression de la cellule C.
	delete C;
	//Decremente la dimension de la liste
	SIZE--;
	//Retourne la position du suivant de la cellule precedemment supprime
  return retour;
}

/**
* \ brief Classe reverse_iterateur qui pointe vers la position de debut(derniere cellule) 
* \ et la position de fin(premiere cellule).
*/
template <typename TYPE>
class list<TYPE>::reverse_iterator{
	friend class list<TYPE>;
private:
	cellule* POINTEUR;
public:
	reverse_iterator(cellule*C = nullptr) :POINTEUR(C) {}
	TYPE& operator*()const { return POINTEUR->CONTENU; } //*i
	TYPE* operator->()const { return &(POINTEUR->CONTENU); } //i->
	reverse_iterator& operator++() { POINTEUR = POINTEUR->PREC; return *this; } //++i
	reverse_iterator operator++(int) { reverse_iterator it(*this); POINTEUR = POINTEUR->PREC; return it; } //i++
	reverse_iterator& operator--() { POINTEUR = POINTEUR->SUIV; return *this; } //--i
	reverse_iterator operator--(int) { reverse_iterator ret(*this); POINTEUR = POINTEUR->SUIV; return ret; } //i--  
	bool operator==(const reverse_iterator& IT)const {
		return POINTEUR == IT.POINTEUR;
	}
	bool operator!=(const reverse_iterator& IT)const {
		return POINTEUR != IT.POINTEUR;
	}
};

/**
* \ brief Fonction d'iteration inverse qui commence au debut de la list 
* \ param [in] none
* \ return La position de la premiere cellule de la list
*/
template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::rbegin(){
  return reverse_iterator(DEBUT->PREC);
}

/**
* \ brief Fonction d'iteration inverse qui commence a la fin de la list
* \ param [in] none
* \ return La position de la derniere cellule de la list
*/
template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::rend(){
  return reverse_iterator(DEBUT->SUIV);
}

/**
* \ brief Fonction d'insertion d'une certaine cellule à l'aide d'un iterateur inverses
* \ d'une une cellule a une certaine position.
* \ param [in] Un iterateur inverse quelconque
* \ 		   Un TYPE X 	
* \ return Le position de l'element de la cellule insere
*/
template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::insert(reverse_iterator i,const TYPE& x){
  return reverse_iterator(insert(i.POINTEUR,x));
}

/**
* \ brief Fonction de suppression d'une certaine cellule à l'aide d'un iterateur inverse
* \ param [in] Un iterateur inverse quelconque
* \ return Le position de l'element suivant de la cellule supprime
*/
template <typename TYPE>
typename list<TYPE>::reverse_iterator list<TYPE>::erase(reverse_iterator i){
  return reverse_iterator(erase(i.POINTEUR));;
}

/**
* \ brief Surchage de l'operateur d'affection
* \ param [in] Une liste de TYPE
* \ return none
*/
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

/**
* \ brief L’opérateur resize qui diminue la dimension de la list en supprimant des éléments à la
fin, ou qui augmente la dimension en ajoutant des éléments à la fin. Dans ce cas, la valeur
des éléments ajoutés est donnée par le second paramètre explicite de l’opérateur ;
* \ param [in] La nouvelle dimension desiree
* \			   Une valeur X TYPE
* \ return none
*/
template <typename TYPE>
void list<TYPE>::resize(size_t N,const TYPE& X){
	//Nouvelle iterateur pointant au debut de la liste
	iterator it(DEBUT);
	//Affectation de la dimension initiale par SIZE
	int initSize = SIZE;
	//Si N est plus petit que SIZE et N est plus grand que 0
	if(N<SIZE && N>0)
	{	
		//Iterer au travers de la liste jusqu'a N
		for (int i = 0; i <= N; i++)it++;
		//Supprimer les valeurs de it > N jusqu'a la fin de la liste
		while (it != end()) it = erase(it);
	}
	//Si N > SIZE
	if (N > SIZE)
	{
		//Ajouter la valeur de X a la fin de la liste jusqu'a la nouvelle dimension - SIZE
		for (int i = 0; i < N - initSize; i++)push_back(X);
	}
}

/**
* \ brief — l’opérateur splice qui insère une list L dans la list courante à
une position indiquée par un itérateur. La taille de la list L est remise à zéro sans
supprimer les cellules qu’elle contenait
* \ param [in] iterator quelconque
* \			   Une liste 
* \ return none
*/
template <typename TYPE>
void list<TYPE>::splice(iterator i,list& L){
	//Pour chaque iteration, le suivant du precedent de l'iterateur sera affecte l'element de la liste
	i.POINTEUR->PREC->SUIV = L.DEBUT->SUIV;
	L.DEBUT->SUIV->PREC = i.POINTEUR->PREC;
	//Pour chaque iteration, le precedent du suivant de l'iterateur sera affecte l'element de la liste
	i.POINTEUR->PREC = L.DEBUT->PREC->PREC;
	L.DEBUT->PREC->PREC->SUIV = i.POINTEUR;

	L.DEBUT->SUIV = L.DEBUT->PREC;
	L.DEBUT->SUIV->PREC = L.DEBUT;
	SIZE += L.SIZE;
	L.SIZE = 0;
  
}

/**
* \ brief — l’opérateur reverse, inverse l’ordre des éléments de la list
* \ return none
*/
template <typename TYPE>
void list<TYPE>::reverse(){
  /*... a completer ...*/
	cellule *cell; //Pointeur vers la contenu de la cellule cell
	cellule *temp; //Pointeur vers la contenu de la cellule temp

	//
	DEBUT->PREC->SUIV = DEBUT->PREC->PREC;
	DEBUT->PREC->PREC = DEBUT;
	cell = DEBUT->PREC->SUIV;
	while (DEBUT != cell)
	{
		temp = cell->PREC;
		cell->PREC = cell->SUIV;
		cell->SUIV = temp;
		cell = cell->SUIV;

	}
	temp = DEBUT->SUIV;
	DEBUT->SUIV = nullptr;
	DEBUT = DEBUT->PREC;
	DEBUT->PREC->PREC = temp;


}

/**
* \ brief — La fonction sort, utilise la methode de tri bulle pour trier les elements d'une liste
* \ return none
*/
template <typename TYPE>
void list<TYPE>::sort(iterator DEB,iterator FIN){
  /*... a completer ...*/

	iterator it(DEB); //iterateur commencant au debut
	iterator it2(DEB); //iterateur commencant au debut
	
	TYPE temp; //variable temporaire de type TYPE
	//iterer jusqu'a la fin de la liste
	while (it2 != FIN)
	{
		//iterer jusqu'a la fin de la liste 
		while (it != FIN)
		{	
			//Si le contenu de l'iterateur est plus grand que le contenu du suivant
			if (it.POINTEUR->CONTENU > it.POINTEUR->SUIV->CONTENU)
			{
				//swap les deux cellules
				temp = it.POINTEUR->CONTENU;
				it.POINTEUR->CONTENU = it.POINTEUR->SUIV->CONTENU;
				it.POINTEUR->SUIV->CONTENU = temp;
			}
			//Incrementer it1
			it++;
		}
		//Permet de reiterer au travers de la liste
		it = DEB;
		//Incrementer l'iterateur it2
		it2++;
	}
}

#endif
