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
  /*... a completer ...*/
  deque copie(source);
  swap(copie);
}

template <typename TYPE>
void deque<TYPE>::reserve(size_t nCAP)
{
  /*... a completer ...*/
	size_t CAP = FIN_CAP - DEBUT_CAP;
  if (nCAP > CAP)
	{
    TYPE *ntab = new TYPE[nCAP];
	size_t DIM = size();
    for (size_t i = 0; i < DIM; i++)
    {
      ntab[i] = DEBUT_CAP[i];
    }
    clear();
    DEBUT_CAP = &ntab[0];
    FIN_CAP = &ntab[nCAP];
    FRONT = &ntab[0];
    BACK = (FRONT + DIM);

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
	size_t DIM = size();
	size_t CAP = FIN_CAP - DEBUT_CAP;
	if (DIM == CAP)
	{
		reserve((DIM + 1) * 2);
	}
	TYPE *temp = BACK;
	resize(DIM + 1);
	if (FRONT == DEBUT_CAP)
	{
		
		/*if (BACK != nullptr && temp != nullptr)
		{
			BACK = temp;
		}*/
		DEBUT_CAP[CAP-1] = x;
		FRONT = &DEBUT_CAP[CAP-1];
		/*if (BACK != nullptr && temp == nullptr)
		{
			BACK = FRONT;
		}*/
		//cout << "whats up" << endl;
	}
	else
	{
		/*if (BACK != nullptr && temp != nullptr)
		{
			BACK = temp;
		}*/
		DEBUT_CAP[FRONT - DEBUT_CAP - 1] = x;
		FRONT = FRONT - 1;
		//cout << "whats up" << endl;
	}

}

template <typename TYPE>
void deque<TYPE>::push_back(const TYPE& x)
{
  /*... a completer ...*/
	size_t DIM = size();

	size_t CAP = FIN_CAP - DEBUT_CAP;
	if (DIM == CAP)
	{
		reserve((DIM + 1) * 2);
	}
	resize(DIM + 1);
	FRONT[DIM] = x;
}

template <typename TYPE>
size_t deque<TYPE>::size()const
{
  /*... a effacer et completer ...*/
	size_t DIM;
	if (BACK >= FRONT) DIM = BACK - FRONT;
	else
	{
		DIM = (FIN_CAP - FRONT) + (BACK - DEBUT_CAP);
	}

  return DIM;
}

template <typename TYPE>
bool deque<TYPE>::empty()const
{
  /*... a effacer et completer ...*/
	if (DEBUT_CAP == nullptr)return true;
	else return false;
}

template <typename TYPE>
TYPE& deque<TYPE>::operator[](size_t i)
{
  /*... a effacer et completer ...*/

	return at(i);
}

template <typename TYPE>
const TYPE& deque<TYPE>::operator[](size_t i)const
{
  /*... a effacer et completer ...*/

	return at(i);
}

template <typename TYPE>
TYPE& deque<TYPE>::at(size_t i)
{
  /*... a effacer et completer ...*/
	if (i >= size())
		throw std::out_of_range("vecteur index out of range.");
	else
		return *(DEBUT_CAP+((FRONT - DEBUT_CAP) + i) % (FIN_CAP - DEBUT_CAP));
}

template <typename TYPE>
const TYPE& deque<TYPE>::at(size_t i)const
{
	if (i >= size())
		throw std::out_of_range("vecteur index out of range.");
	else
		return *(DEBUT_CAP + ((FRONT - DEBUT_CAP) + i) % (FIN_CAP - DEBUT_CAP));
}

#endif
