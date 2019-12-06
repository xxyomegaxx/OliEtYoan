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

/**
* \ brief Fonction qui va chercher la valeur >= a celle desiree.
* \ param [in] Une cle
* | return un iterateur
*/
template <typename Tclef, typename Tvaleur>
  typename map<Tclef,Tvaleur>::iterator map<Tclef,Tvaleur>::lower_bound(const Tclef& c)const{

  iterator *it; //nouveau iterateur
  noeud *root = APRES->GAUCHE; // Premier noeud

    //Tant que root n'est pas null, traverse l'arbre de la racine a l'element recherche
    while(root != nullptr){
        //Si le contenu est egal a c
        if(root->CONTENU->first == c){
            //retourner un iterateur a la position de root
            it = new iterator(root);
            return *it;
        }
        //Si le c est plus petit, descendre a gauche
        if(c < root->CONTENU->first) {
            //si null retourner le plus proche
            if(root->GAUCHE == nullptr){
                it = new iterator(root);
                return *it;

            }
            //si c est plus grand que l'enfant gauche
			if (c > root->GAUCHE->CONTENU->first)
			{
			    //Retourner un iterateur a la position actuelle
				it = new iterator(root);
				return *it;
			}

			//incrementer root a gauche
            root = root->GAUCHE;
        }
        //Si c est plus grand que le contenu
        else {

            if(root->DROITE == nullptr){
                
				return end();
            }
            //incrementer root a droite
            root = root->DROITE;
        }

    }
    return iterator();
}


/**
* \ brief Fonction d'insertion d'une valeur avant un certaine cellule a partir de certain endroit.
* \ param [in] Un iterateur
* \ 		   Un noeud de certain TYPE
* \ return return Un iterateur
*/
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
	}

    n = n->PARENT;

	while(n->INDICE == 1 || n->INDICE == -1 || n->INDICE == 0){
        if (n->GAUCHE == n)
        {

            allonger_a_gauche(n);
        }
        else if (n->DROITE == n)
        {
            allonger_a_droite(n);
        }

        n = n->PARENT;
	}
}

/**
* \ brief Fonction de suppression d'un noeud avec la meme valeur
* \ param [in] Une cle
* | return un iterateur
*/
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
  /*... a effacer et completer ...*/
  return iterator();
}


///////////////////////////////////////////////////
// fonctions privees pour la gestion de l'equilibre
///////////////////////////////////////////////////

/**
* \ brief Fonction de rotation de gauche a droite.
* \ param [in] Un noeud
*/
template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::rotation_gauche_droite(noeud*& p){
  /*... a completer ...*/

    noeud* x = p->GAUCHE; //Noeud a gauche de p
	noeud* T2 = x->DROITE; //Noeud a droite de T2
	noeud* parent = p->PARENT; //Noeud parent de p

	int ix = x->INDICE;//indice de x
	int ip = p->INDICE;//indice de p
	int nip = -ix - std::max(0, -ix) - 1 + ip;// nouvel indice de p
	int nix = ix - std::max(0, -nip) - 1;//nouvel indice de x

	//Mettre les nouveaux indices dans x et p
	x->INDICE = nix;
	p->INDICE = nip;

	// Perform rotation  
	x->DROITE = p;
	x->PARENT = p->PARENT;

	//Associer la branche T2 a gauche de p
	p->GAUCHE = T2;
	p->PARENT = x;
	//Si T2 est null
	if (T2 != NULL)
    {
		T2->PARENT = p;
	}
    //Verifie si nous venons de la gauche ou de la droite
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
/**
* \ brief Fonction de rotation de droite a gauche.
* \ param [in] Un noeud
*/
template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::rotation_droite_gauche(noeud*& p){
	noeud* x = p->DROITE; //Noeud a droite de p
	noeud* T2 = x->GAUCHE;//enfant droit de x
	noeud* parent = p->PARENT; //noeud parent de p

    int ix = x->INDICE; //indice de x
    int ip = p->INDICE;//indice de p
	int nip = ip + std::max(0, ix) + 1 - ix; //Nouvel indice de p
    int nix = ix + std::max(0, nip) + 1 ; //Nouvel indice de x

    //Associer les nouveaux indices
    x->INDICE = nix;
    p->INDICE = nip;
    
	// Perform rotation
	x->GAUCHE = p;
	x->PARENT = p->PARENT;

	//Associer T2 a la droite de p
	p->DROITE = T2;
	p->PARENT = x;
	if (T2 != NULL)
	{
		T2->PARENT = p;
	}
    //Verifie de quelle branche nous venons
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
