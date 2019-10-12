/**
* \ file vector.h
* \author Olivier Cote et Yoan David
* \ brief Ce fichier contient les fonctions du type vector à coder
*/

#ifndef _vector2_h
#define _vector2_h

template <typename TYPE>
vector<TYPE>::vector(size_t D)
{
	if (D == 0)
	{
		DEBUT = FIN_DIM = FIN_CAP = nullptr; // si vector vide, on pointe vers null 
	}
	else
	{
		DEBUT = new TYPE[D];
		FIN_CAP = &DEBUT[D];
		FIN_DIM = DEBUT;

	}
	

}

template <typename TYPE>
void vector<TYPE>::reserve(size_t nCAP)
{
	if (nCAP > FIN_CAP - DEBUT) // si nouvelle capacite > capacite
	{
		TYPE *ntab = new TYPE[nCAP]; //on cree nouveau tableau
		size_t size = FIN_DIM - DEBUT;
		for (size_t i = 0; i < size; i++)
		{
			ntab[i] = DEBUT[i];  // ajout de l'ancien tableau dans le nouveau 
		}
		clear(); // supression de l'ancien tableau
		DEBUT = &ntab[0]; //debut pointe vers premier element
		FIN_DIM = (DEBUT + size); // pointe vers le dernier element
		FIN_CAP = &ntab[nCAP]; //pointe vers la fin de la capacité
	}



}

template <typename TYPE>
TYPE& vector<TYPE>::back()
{

	return *(FIN_DIM/*-1*/); // retour du dernier element(pour avoir la valeur du dernier element ajouter -1)

}

template <typename TYPE>
const TYPE& vector<TYPE>::back()const
{

	return *(FIN_DIM/*-1*/);// retour du dernier element(pour avoir la valeur du dernier element ajouter -1)
}

template <typename TYPE>
TYPE& vector<TYPE>::front()
{

	return *DEBUT; // retour premier element

}

template <typename TYPE>
const TYPE& vector<TYPE>::front()const
{

	return *DEBUT; //retour premier element

}

template <typename TYPE>
TYPE& vector<TYPE>::operator[](size_t i)
{


	return at(i); //renvoie la valeur au ieme element
}

template <typename TYPE>
const TYPE& vector<TYPE>::operator[](size_t i)const
{

	return at(i); //renvoie la valeur au ieme element


}


#endif
