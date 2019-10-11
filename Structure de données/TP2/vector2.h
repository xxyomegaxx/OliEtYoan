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
	DEBUT = new TYPE[D];
	FIN_CAP = &DEBUT[D];
	FIN_DIM = DEBUT;


}

template <typename TYPE>
void vector<TYPE>::reserve(size_t nCAP)
{
  /*... a completer ...*/
	if (nCAP > FIN_CAP - DEBUT)
	{
		TYPE *ntab = new TYPE[nCAP];
		size_t size = FIN_DIM - DEBUT;
		for (size_t i = 0; i < size; i++)
		{
			ntab[i] = DEBUT[i];
		}
		clear();
		DEBUT = &ntab[0];
		FIN_DIM = (DEBUT + size);
		FIN_CAP = &ntab[nCAP];
	}



}

template <typename TYPE>
TYPE& vector<TYPE>::back()
{
  /*... a effacer et completer ...*/
	return *(FIN_DIM);

}

template <typename TYPE>
const TYPE& vector<TYPE>::back()const
{
  /*... a effacer et completer ...*/
  /*TYPE* x = new TYPE();
  return *x;*/
	return *(FIN_DIM);
}

template <typename TYPE>
TYPE& vector<TYPE>::front()
{
  /*... a effacer et completer ...*/
  /*TYPE* x = new TYPE();
  return *x;*/
	return *DEBUT;

}

template <typename TYPE>
const TYPE& vector<TYPE>::front()const
{
  /* a effacer et completer ...*/
	return *DEBUT;

}

template <typename TYPE>
TYPE& vector<TYPE>::operator[](size_t i)
{
  /*... a effacer et completer ...*/


	return at(i);
}

template <typename TYPE>
const TYPE& vector<TYPE>::operator[](size_t i)const
{
  /*... a effacer et completer ...*/
	/*TYPE* x = new TYPE();
	x = DEBUT + i;
	return x;*/
	return at(i);


}


#endif
