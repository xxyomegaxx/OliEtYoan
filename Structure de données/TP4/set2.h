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
cellule** nouveau = new cellule(X,hauteur);

if(hauteur > hauteurAvant){
  cellule** nouveauDebut = new cellule*[hauteur];
  cellule** nouveauFin = new cellule*[hauteur]; 
  for(size_t i = 0; i < hauteurAvant; i++){
    swap(DEBUT->SUIV[i],nouveauDebut[i]);
    swap(DEBUT->PREC->PREC[i],nouveauFin[i]);
  }
  swap(DEBUT->SUIV,nouveauDebut);
  swap(DEBUT->PREC->PREC,nouveauFin);
  ~set(nouveauDebut);
  ~set(nouveauFin);

  for(size_t i = hauteurAvant;i <= hauteur; i++){
    DEBUT->SUIV[i] = nouveau;
    nouveau->PREC[i] = DEBUT;

    DEBUT->PREC[0]->PREC[i] = nouveau;
    nouveau->SUIV[i] = DEBUT->PREC[0];
  }

  cellule* p = ap->PREC[0];
  cellule* ap;
  for(int i = 0; i <= hauteurAvant-1; i++){
    while(p->HAUTEUR <= i){
      p = p->PREC[p->HAUTEUR-1];
      nouveau->PREC[i] = p->SUIV[i];
    }
    while(ap->HAUTEUR <= i){
      ap = ap->SUIV[ap->HAUTEUR-1];
      nouveau->SUIV[i] = ap->PREC[i];
    }
  }
  return nouveau;
  }
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
  iterator *it;

  int h = DEBUT->SUIV->HAUTEUR;
  cellule* c = DEBUT;
  while(h <= 0 && c->CONTENU != X){
    if(c->SUIV[h] <= X){
      c = c->SUIV[h];
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
  /*... a effacer et completer ...*/
  cellule* c = DEBUT; 
  int h = c->SUIV->HAUTEUR;

  for(int i = h-1; i > 0; i--){
    while(c->SUIV[i].CONTENU <= X){
      c = c->SUIV[i];
    }
    return new iterator(c->SUIV[0]);
  }


  
  return iterator();
}

template <typename TYPE>
typename set<TYPE>::iterator set<TYPE>::upper_bound(const TYPE& X)
{
  /*... a effacer et completer ...*/
  cellule* c = DEBUT; 
  int h = c->SUIV->HAUTEUR;

  for(int i = h-1; i > 0; i--){
    while(c->SUIV[i]->CONTENU < X){
      c = c->suiv[i];
    }
    c=c->SUIV[0];
    if(c->CONTENU==X)
    {
      c=c->SUIV[0];
    }

    return new iterator(c);
  }
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
