/**
* \ file vector.h
* \ author Jean Goulet (Sept. 2016), modified by Aida Ouangraoua (Sept. 2019)
* \ brief Ce fichier contient les fonctions du type vector à coder
*/

#ifndef _vector2_h
#define _vector2_h

template <typename TYPE>
vector<TYPE>::vector(size_t D)
{
  /*... a completer ...*/
	TYPE tab[D];
	DEBUT= &tab[0];
	FIN_CAP = &tab[D];
	FIN_DIM = DEBUT;


}

template <typename TYPE>
void vector<TYPE>::reserve(size_t nCAP)
{
  /*... a completer ...*/
	TYPE ntab[nCAP];
	int size = FIN_DIM - DEBUT;
	for (int i = 0; i < size)
	{
		ntab[i] = *DEBUT;
		DEBUT++;
	}
	DEBUT = &nTab[0];
	FIN_DIM = DEBUT + size;
	FIN_CAP = &ntab[nCAP - 1];


}

template <typename TYPE>
TYPE& vector<TYPE>::back()
{
  /*... a effacer et completer ...*/
  TYPE* x = new TYPE();
  return *x;
}

template <typename TYPE>
const TYPE& vector<TYPE>::back()const
{
  /*... a effacer et completer ...*/
  /*TYPE* x = new TYPE();
  return *x;*/
	return FIN_DIM;
}

template <typename TYPE>
TYPE& vector<TYPE>::front()
{
  /*... a effacer et completer ...*/
  /*TYPE* x = new TYPE();
  return *x;*/
	

}

template <typename TYPE>
const TYPE& vector<TYPE>::front()const
{
  /*... a effacer et completer ...*/
  //TYPE* x = new TYPE();
  return DEBUT;
}

template <typename TYPE>
TYPE& vector<TYPE>::operator[](size_t i)
{
  /*... a effacer et completer ...*/
  TYPE* x = new TYPE();
  x = DEBUT + i;

  return *x;
}

template <typename TYPE>
const TYPE& vector<TYPE>::operator[](size_t i)const
{
  /*... a effacer et completer ...*/
  TYPE* x = new TYPE();
  return *x;
}


#endif
