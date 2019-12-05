/**
* \ file set2.h
* \ author Aida Ouangraoua (Nov. 2019)
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
            root = root->GAUCHE;
        }
        else {
            if(root->DROITE == nullptr){
                it = new iterator(root);
                return *it;
            }
            root = root->DROITE;
        }

    }
}


template <typename Tclef, typename Tvaleur>
  typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::insert(iterator j,const Tclef& c){
  /*... a effacer et completer ...*/
  return iterator();
}

template <typename Tclef, typename Tvaleur>
  size_t map<Tclef,Tvaleur>::erase(const Tclef& c){
  /*... a effacer et completer ...*/
  return 0;  
}

template <typename Tclef, typename Tvaleur>
  typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::erase(iterator i){
  /*... a effacer et completer ...*/
  return iterator();
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
    int nip = -ix - std::max(0, -ix) - 1 + ip;
    int nix = ix - std::max(0, -nip) - 1;
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
