/**
* \ file deque2.h
* \ author Aida Ouangraoua (Sept. 2019)
* \ brief Ce fichier contient les fonctions
* \ du type deque à coder
*/

#ifndef deque2_h
#define deque2_h

template <typename TYPE>
deque<TYPE>::deque(size_t D)
{
  /*... a completer ...*/
  DEBUT_CAP = new TYPE[D];
  if(D>=2)FRONT= &DEBUT_CAP[1];
  else FRONT= &DEBUT_CAP[0];
  BACK = FRONT;
  FIN_CAP = &FIN_CAP[D];

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
  /*... a completer ...*/
  Deqque copie(source);
  swap(copie);
}

template <typename TYPE>
void deque<TYPE>::reserve(size_t nCAP)
{
  /*... a completer ...*/
  if (nCAP > FIN_CAP - DEBUT)
	{
    TYPE *ntab = new TYPE[nCAP];
    size_t size = FIN_DIM - DEBUT;
    for (size_t i = 0; i < size; i++)
    {
      ntab[i] = DEBUT[i+1];
    }
    clear();
    DEBUT_CAP = &ntab[0];
    FIN_CAP = &ntab[nCAP];
    FRONT = ntab[1];
    BACK = (DEBUT + size);

  }

}

template <typename TYPE>
void deque<TYPE>::clear()
{
  /*... a completer ...*/
  delete[] DEBUT_CAP;
  DEBUT_CAP = BACK = FRONT = FIN_CAP = nullptr;
}

template <typename TYPE>
void deque<TYPE>::push_front(const TYPE& x)
{
  /*... a completer ...*/
}

template <typename TYPE>
void deque<TYPE>::push_back(const TYPE& x)
{
  /*... a completer ...*/
}

template <typename TYPE>
size_t deque<TYPE>::size()const
{
  /*... a effacer et completer ...*/

  return DIM;
}

template <typename TYPE>
bool deque<TYPE>::empty()const
{
  /*... a effacer et completer ...*/
  return true;
}

template <typename TYPE>
TYPE& deque<TYPE>::operator[](size_t i)
{
  /*... a effacer et completer ...*/
  TYPE* x = new TYPE();
  return *x;
}

template <typename TYPE>
const TYPE& deque<TYPE>::operator[](size_t i)const
{
  /*... a effacer et completer ...*/
  TYPE* x = new TYPE();
  return *x;
}

template <typename TYPE>
TYPE& deque<TYPE>::at(size_t i)
{
  /*... a effacer et completer ...*/
  TYPE* x = new TYPE();
  return *x;
}

template <typename TYPE>
const TYPE& deque<TYPE>::at(size_t i)const
{
  /*... a effacer et completer ...*/
  TYPE* x = new TYPE();
  return *x;
}

#endif
