/**
* \ file set2.h
* \ author Aida Ouangraoua (Oct. 2019)
* \ brief Ce fichier contient les fonctions 
* \ du type set � coder
*/

#ifndef set2_h
#define set2_h

/////////////////////////////////////////////////
// set
// fonctions privees
/////////////////////////////////////////////////

template <typename TYPE>
typename set<TYPE>::cellule* set<TYPE>::insert(typename set<TYPE>::cellule* ap, const TYPE& X){
  /*... a effacer et completer ...*/
size_t hauteurAvant = DEBUT->HAUTEUR;
size_t hauteur = tirer_hauteur_au_hasard();
TYPE *y = new TYPE(X);
cellule* nouveau = new cellule(*y,hauteur);

if(hauteur > hauteurAvant){
  cellule** nouveauDebut = new cellule*[hauteur];
  cellule** nouveauFin = new cellule*[hauteur]; 
  for(size_t i = 0; i < hauteurAvant; i++){
	  nouveauDebut[i] = DEBUT->SUIV[i];
	  nouveauFin[i] = DEBUT->PREC[0]->PREC[i];
  }
  delete [] DEBUT->PREC[0]->PREC;
  delete [] DEBUT->SUIV;

  DEBUT->SUIV=nouveauDebut;
  DEBUT->PREC[0]->PREC=nouveauFin;
  DEBUT->HAUTEUR = hauteur;
  DEBUT->PREC[0]->HAUTEUR = hauteur;


  for(size_t i = hauteurAvant;i < hauteur; i++){
    DEBUT->SUIV[i] = nouveau;
    nouveau->PREC[i] = DEBUT;

    DEBUT->PREC[0]->PREC[i] = nouveau;
    nouveau->SUIV[i] = DEBUT->PREC[0];
  }


  
  }
else; //hauteurAvant = hauteur;

	cellule* p = ap->PREC[0];
	//cellule* ap;
	for (int i = 0; i < hauteurAvant; i++) {
		while (p->HAUTEUR <= i) {
			p = p->PREC[p->HAUTEUR];
		}
		nouveau->PREC[i] = p;
		p->SUIV[i] = nouveau;
		while (ap->HAUTEUR <= i) {
			ap = ap->SUIV[ap->HAUTEUR];
		}
		nouveau->SUIV[i] = ap;
		ap->PREC[i] = nouveau;
    }
    SIZE++;
    return nouveau;
}


template <typename TYPE>
typename set<TYPE>::cellule* set<TYPE>::erase(typename set<TYPE>::cellule* C){
  /*... a effacer et completer ...*/

  cellule* p = C->PREC[0];
  cellule* ap = C->SUIV[0];

  for(int i = 0; i < C->HAUTEUR; i++){

	  while (p->HAUTEUR <= i) {
		  p = p->PREC[p->HAUTEUR - 1];
	  }
	  while (ap->HAUTEUR <= i) {
		  ap = ap->SUIV[ap->HAUTEUR - 1];
	  }
	   p->SUIV[i] = ap->PREC[i];
       ap->PREC[i] = p->SUIV[i];
  }
  delete C;

  SIZE--;
  return p;
}

/////////////////////////////////////////////////
// set
// fonctions publiques
/////////////////////////////////////////////////

template <typename TYPE>
set<TYPE>::set(const set<TYPE>& source) : set()
{
  /*... a completer ...*/
    for(const TYPE & x:source) insert(x);
    
}

template <typename TYPE>
set<TYPE>::~set()
{
  /*... a completer ...*/
    clear();
	delete  DEBUT->PREC[0];
    delete  DEBUT;

}

template <typename TYPE>
void set<TYPE>::clear()
{
       set<TYPE>::iterator it = begin();
        while(it != end()){
        it=erase(it);
	    }
    
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::find(const TYPE& X)
{
  iterator *it;

  int h = DEBUT->HAUTEUR;
  cellule* c = DEBUT;
  while(h-1 >= 0 && c->CONTENU != X){
    if(c->SUIV[h-1]->CONTENU < X){
		if(c->SUIV[h]!= DEBUT->PREC[0]) c = c->SUIV[h];
     
    } else {
      h--;
    }
  }
  if(c->CONTENU == X){
    it = new iterator(c);
    return *it;
  } else {
    it = new iterator(nullptr);
    return iterator(*it);    
  }
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::lower_bound(const TYPE& X)
{
  cellule* c = DEBUT; 
  size_t h = c->SUIV[0]->HAUTEUR;
  bool reachEnd = false;
  for(int i = h-1; i >= 0; i--){
	  
		  while (c->SUIV[i]->CONTENU < X && !reachEnd) {
			  if (c->SUIV[i] != DEBUT->PREC[0])
			  {
				  c = c->SUIV[i];
			  }
			  else reachEnd = true;
    }
  }
  return iterator(c->SUIV[0]);
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::upper_bound(const TYPE& X)
{
	cellule* c = DEBUT;
	size_t h = c->SUIV[0]->HAUTEUR;
	bool reachEnd = false;
	for (size_t i = h - 1; i >= 0; i--) {

		while (c->SUIV[i]->CONTENU < X && !reachEnd) {
			if (c->SUIV[i] != DEBUT->PREC[0])
			{
				c = c->SUIV[i];
			}
			else reachEnd = true;
		}
	}
	if(c->CONTENU == X)
	return iterator(c->SUIV[0]);
  }

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::insert(iterator it, const TYPE& X)
{
  /*... a effacer et completer ...*/
    cellule* p = it.POINTEUR;
    
  if(*it > X && p->PREC[0]->CONTENU < X){
    return iterator(insert(it.POINTEUR,X));
  } else {
    return insert(X).first;
  }
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(const TYPE& VAL)
{
  /*... a effacer et completer ...*/
	iterator it = find(VAL);
	if (it == nullptr)
	{
		return lower_bound(VAL);
	}
	else
	{
		return iterator(erase(it.POINTEUR));

	}  
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(iterator it)
{
	return iterator(erase(it.POINTEUR));
}

#endif
