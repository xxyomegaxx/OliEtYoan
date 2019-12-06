/**
* \ file set2.h
* \ author Olivier Côté (coto2514)
 *         Yoan David (davy3001) (Nov. 2019)
* \ brief Ce fichier contient les fonctions 
* \ du type map à coder
*/


#ifndef map2_h
#define map2_h
#include<algorithm>


/////////////////////////////////////////////////
// map
// fonctions publiques
/////////////////////////////////////////////////

template <typename Tclef, typename Tvaleur>
  typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::lower_bound(const Tclef& c)const{
  /*... a effacer et completer ...*/
  iterator *it;
  noeud *root = APRES->GAUCHE; // Premier noeud
//   iterator *it = new iterator(r);

    while(root != nullptr){
        if(root->CONTENU->first == c){
            it = new iterator(root);
            return *it;
        }
        if(c < root->CONTENU->first) {
            if(root->GAUCHE == nullptr){
                it = new iterator(root);
                return *it;

            }
			if (c > root->GAUCHE->CONTENU->first)
			{
				it = new iterator(root);
				return *it;
			}

            root = root->GAUCHE;
        }
        else {
            if(root->DROITE == nullptr){
                
				return end();
            }
            root = root->DROITE;
        }

    }
    return iterator();
}


template <typename Tclef, typename Tvaleur>
typename map<Tclef, Tvaleur>::iterator map<Tclef, Tvaleur>::insert(iterator j, const Tclef& c) {
	iterator retour;
	noeud *n = j.POINTEUR;
	noeud *x = retour.POINTEUR;

	if (j != lower_bound(c))
	{
		insert(c);
	}
	else
	{
		if (n->GAUCHE == NULL)
		{
			insert(c, n, retour);
			n = n->GAUCHE;
		}

		else
		{
			n = n->GAUCHE;
			while (n->DROITE != NULL)
			{
				n = n->DROITE;
			}

			insert(c, n, retour);
			n = n->DROITE;

		}
		while (n->INDICE == 1 || n->INDICE == -1 || n->INDICE == 0) {
			if (n->PARENT->GAUCHE == n)
			{

				allonger_a_gauche(n->PARENT);
			}
			else if (n->DROITE == n)
			{
				allonger_a_droite(n->PARENT);
			}

			n = n->PARENT;
		}
		return retour;
	}
	
}

	 

template <typename Tclef, typename Tvaleur>
  size_t map<Tclef,Tvaleur>::erase(const Tclef& c){
	  iterator it = new iterator(find (c));

	  if (it == end())
	  {
		  return 0;
	  }
	  else  if (c != it->first)
	  {
		  return 0;
	  }
	  else if (c == it->first)
	  {
		  erase(it);
		  return 1;
	  }
 
  return 0;  
}

template <typename Tclef, typename Tvaleur>
  typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::erase(iterator i){
	  iterator retour;
	  noeud* n = i.POINTEUR;
	  noeud* prec = i--;

	  erase(i->first, prec, n);

	  while (n->INDICE == 2 || n->INDICE == -2 || n->INDICE == 0) {
		  if (n->PARENT->GAUCHE == n)
		  {

			  allonger_a_droite(n->PARENT);
		  }
		  else if (n->DROITE == n)
		  {
			  allonger_a_gauche(n->PARENT);
		  }

		  n = n->PARENT;
	  }
	  
  
}


///////////////////////////////////////////////////
// fonctions privees pour la gestion de l'equilibre
///////////////////////////////////////////////////


template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::rotation_gauche_droite(noeud*& p){
  /*... a completer ...*/

    noeud* x = p->GAUCHE;
	noeud* T2 = x->DROITE;
	noeud* parent = p->PARENT;

	int ix = x->INDICE;
	int ip = p->INDICE;
	int nip = -ix - std::max(0, -ix) - 1 + ip;
	int nix = ix - std::max(0, -nip) - 1;
	x->INDICE = nix;
	p->INDICE = nip;

	// Perform rotation  
	x->DROITE = p;
	x->PARENT = p->PARENT;

	p->GAUCHE = T2;
	p->PARENT = x;
	if (T2 != NULL)
	{
		T2->PARENT = p;
	}

	if (parent->GAUCHE == p)
	{
		parent->GAUCHE = x;
	}
	else if (parent->DROITE == p)
	{
		parent->DROITE = x;
	}

	p = x;

}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::rotation_droite_gauche(noeud*& p){
	noeud* x = p->DROITE;
	noeud* T2 = x->GAUCHE;
	noeud* parent = p->PARENT;

    int ix = x->INDICE;
    int ip = p->INDICE;
	int nip = ip + std::max(0, ix) + 1 - ix;
    int nix = ix + std::max(0, nip) + 1 ;
    x->INDICE = nix;
    p->INDICE = nip;
    
	// Perform rotation
	x->GAUCHE = p;
	x->PARENT = p->PARENT;

	p->DROITE = T2;
	p->PARENT = x;
	if (T2 != NULL)
	{
		T2->PARENT = p;
	}

	if (parent->GAUCHE == p)
	{
		parent->GAUCHE = x;
	}
	else if (parent->DROITE == p)
	{
		parent->DROITE = x;
	}

	p = x;
}


#endif /* map2_h */
