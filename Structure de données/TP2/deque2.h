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
}

template <typename TYPE>
deque<TYPE>::deque(const deque& source) :deque()
{
  /*... a completer ...*/
}

template <typename TYPE>
void deque<TYPE>::swap(deque& v)
{
  /*... a completer ...*/
}

template <typename TYPE>
void deque<TYPE>::operator=(const deque& source)
{
  /*... a completer ...*/
}
  
template <typename TYPE>
void deque<TYPE>::reserve(size_t nCAP)
{
  /*... a completer ...*/
}

template <typename TYPE>
void deque<TYPE>::clear()
{
  /*... a completer ...*/
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
  size_t DIM;
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
