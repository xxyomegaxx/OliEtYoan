/**
* \ file deque2.h
* \ author Olivier Cote et Yoan David
* \ brief Ce fichier contient les fonctions
* \ du type deque à coder
*/
//------------------------ATTENTION------------------------------//
// la seule facon que nous avons trouvé de faire fonctionner la methode pushback est
//  en modifiant légèrement la méthode resize. Il serait très apprécié 
//que vous preniez cela en considération. Merci beaucoup et voici la version que nous avons utilisé
/*

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
*/
#ifndef deque2_h
#define deque2_h

template <typename TYPE>
deque<TYPE>::deque(size_t D)
{
  /*... a completer ...*/
  DEBUT_CAP = new TYPE[D];
  FRONT = nullptr;
  BACK = nullptr;
  FIN_CAP = &DEBUT_CAP[D];

}

template <typename TYPE>
deque<TYPE>::deque(const deque& source) :deque()
{
  /*... a completer ...*/
  resize(source.size());
  for (size_t i = 0; i<size(); i++)
    FRONT[i] = TYPE(source[i]);

}

template <typename TYPE>
void deque<TYPE>::swap(deque& v)
{
  std::swap(DEBUT_CAP, v.DEBUT_CAP);
  std::swap(FRONT, v.FRONT);
  std::swap(BACK, v.BACK);
  std::swap(FIN_CAP, v.FIN_CAP);
}

template <typename TYPE>
void deque<TYPE>::operator=(const deque& source)
{
  deque copie(source); //cree copie de la source et remplace originale par copie
  swap(copie);
}

template <typename TYPE>
void deque<TYPE>::reserve(size_t nCAP)
{
  /*... a completer ...*/
	size_t CAP = FIN_CAP - DEBUT_CAP;
  if (nCAP > CAP)  // si nouvelle capacite > capacite
	{
    TYPE *ntab = new TYPE[nCAP]; //on cree nouveau tableau
	size_t DIM = size();
    for (size_t i = 0; i < DIM; i++)
    {
      ntab[i] = DEBUT_CAP[i]; // ajout de l'ancien tableau dans le nouveau 
    }
    clear(); // supression de l'ancien tableau
    DEBUT_CAP = &ntab[0]; //debut pointe vers premier element
    FIN_CAP = &ntab[nCAP];//pointe vers la fin de la capacité
    FRONT = &ntab[0];//debut pointe vers premier element dans ce cas ci placé au tab[0]
    BACK = (FRONT + DIM); // pointe vers le dernier element apres front

  }

}

template <typename TYPE>
void deque<TYPE>::clear()
{
  delete[] DEBUT_CAP; //suprime ancien tableau
  DEBUT_CAP = BACK = FRONT = FIN_CAP = nullptr;
}

template <typename TYPE>
void deque<TYPE>::push_front(const TYPE& x)
{
	size_t DIM = size();
	size_t CAP = FIN_CAP - DEBUT_CAP;
	if (DIM == CAP) // si capacite pleine, on laugmente
	{
		reserve((DIM + 1) * 2);
	}
	resize(DIM + 1);
	if (FRONT == DEBUT_CAP) //si front pointe a la premiere case de capacite
	{
		BACK = BACK - 1; // la fonction resize change la valeur de back, on veut garder la meme valeur alors on met -1 pour quelle prenne sa valeur d'avant
		DEBUT_CAP[CAP-1] = x; //ajout de la valeur à la fin du tableau
		FRONT = &DEBUT_CAP[CAP-1]; //front pointe maintenant vers la fin du tableau

	}
	else
	{
		BACK = BACK - 1; //idem que plus haut
		DEBUT_CAP[FRONT - DEBUT_CAP - 1] = x; //ajout de la valeur juste devant front
		FRONT = FRONT - 1; // deplacement de front vers la case de la valeur

	}

}

template <typename TYPE>
void deque<TYPE>::push_back(const TYPE& x)
{
	size_t DIM = size();

	size_t CAP = FIN_CAP - DEBUT_CAP;
	if (DIM == CAP)// si capacite pleine, on laugmente
	{
		reserve((DIM + 1) * 2);
	}
	resize(DIM + 1); // on augmente la dimension 
	FRONT[DIM] = x; //on met la valeur vers cette nouvelle position
}

template <typename TYPE>
size_t deque<TYPE>::size()const
{
	size_t DIM;
	if (BACK >= FRONT)
	{
		DIM = BACK - FRONT; // si back est plus loin dans le tableau que front, on calcule la difference pour avoir la dimension
	}

	else
	{
		DIM = (FIN_CAP - FRONT) + (BACK - DEBUT_CAP); // sinon, on additionne les valeurs du debut du tableau avec celles de la fin
	}

  return DIM;
}

template <typename TYPE>
bool deque<TYPE>::empty()const
{
	if (DEBUT_CAP == nullptr)return true; //si tableau non initialise, retour false
	else return false;
}

template <typename TYPE>
TYPE& deque<TYPE>::operator[](size_t i)
{

	return at(i); //renvoie la valeur au ieme element
}

template <typename TYPE>
const TYPE& deque<TYPE>::operator[](size_t i)const
{

	return at(i); //renvoie la valeur au ieme element
}

template <typename TYPE>
TYPE& deque<TYPE>::at(size_t i)
{
  /*... a effacer et completer ...*/
	if (i >= size())
		throw std::out_of_range("deque index out of range.");
	else
		return *(DEBUT_CAP+((FRONT - DEBUT_CAP) + i) % (FIN_CAP - DEBUT_CAP));
}

template <typename TYPE>
const TYPE& deque<TYPE>::at(size_t i)const
{
	if (i >= size())
		throw std::out_of_range("deque index out of range.");
	else
		return *(DEBUT_CAP + ((FRONT - DEBUT_CAP) + i) % (FIN_CAP - DEBUT_CAP));
}

#endif
