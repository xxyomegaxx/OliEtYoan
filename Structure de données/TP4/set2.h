/**
* \ file set2.h
* \ author Aida Ouangraoua (Oct. 2019)
* \ brief Ce fichier contient les fonctions 
* \ du type set � coder
*/

#ifndef set2_h
#define set2_h

/////////////////////////////////////////////////
// set
// fonctions privees
/////////////////////////////////////////////////


/**
* \ brief Fonction d'insertion d'une valeur avant un certaine cellule.
* \ param [in] Une cellule de certain TYPE
* \ 		   Une variable d'un certain TYPE
* \ return return La nouvelle cellule d'un certain TYPE
*/
template<typename TYPE>
typename set<TYPE>::cellule *set<TYPE>::insert(typename set<TYPE>::cellule *ap, const TYPE &X) {
    /*... a effacer et completer ...*/
    //
    size_t hauteurAvant = DEBUT->HAUTEUR;
    size_t hauteur = tirer_hauteur_au_hasard();
    TYPE *y = new TYPE(X);
    cellule *nouveau = new cellule(*y, hauteur);

    if (hauteur > hauteurAvant) {
        cellule **nouveauDebut = new cellule *[hauteur];
        cellule **nouveauFin = new cellule *[hauteur];
        for (size_t i = 0; i < hauteurAvant; i++) {
            nouveauDebut[i] = DEBUT->SUIV[i];
            nouveauFin[i] = DEBUT->PREC[0]->PREC[i];
        }
        delete[] DEBUT->PREC[0]->PREC;
        delete[] DEBUT->SUIV;

        DEBUT->SUIV = nouveauDebut;
        DEBUT->PREC[0]->PREC = nouveauFin;
        DEBUT->HAUTEUR = hauteur;
        DEBUT->PREC[0]->HAUTEUR = hauteur;


        for (size_t i = hauteurAvant; i < hauteur; i++) {
            DEBUT->SUIV[i] = nouveau;
            nouveau->PREC[i] = DEBUT;

            DEBUT->PREC[0]->PREC[i] = nouveau;
            nouveau->SUIV[i] = DEBUT->PREC[0];
        }

    }

    cellule *p = ap->PREC[0];
    //cellule* ap;
    for (int i = 0; i < min(hauteurAvant, hauteur); i++) {
        while (p->HAUTEUR <= i) {
            p = p->PREC[p->HAUTEUR - 1];
        }
        nouveau->PREC[i] = p;
        p->SUIV[i] = nouveau;
        while (ap->HAUTEUR <= i) {
            ap = ap->SUIV[ap->HAUTEUR - 1];
        }
        nouveau->SUIV[i] = ap;
        ap->PREC[i] = nouveau;
    }
    SIZE++;
    return nouveau;
}

/**
* \ brief Fonction de supression d'une certaine cellule
* \ param [in] Une cellule de certain TYPE
* \ return return La nouvelle cellule d'un certain TYPE qui remplace celle qu'onn vient de supprimer
*/
template<typename TYPE>
typename set<TYPE>::cellule *set<TYPE>::erase(typename set<TYPE>::cellule *C) {
    /*... a effacer et completer ...*/

    cellule *p = C->PREC[0]; //cellule avant celle qu'on veut supprimer
    cellule *ap = C->SUIV[0]; //cellule apres celle qu'on veut supprimer
    cellule *retour = C->SUIV[0]; //cellule apres celle qu'on veut supprimer qu'on va retourner a l'uttilisateur

	//Pour toute la hauteur de la cellule a retirer
    for (int i = 0; i < C->HAUTEUR; i++) {

		// si la hauteur de la cellule avant est plus petite que la valeur de la hauteur actuelle, on recule le precedent
        while (p->HAUTEUR <= i) {
            p = p->PREC[p->HAUTEUR - 1];
        }
		// si la hauteur de la cellule apres est plus petite que la valeur de la hauteur actuelle, on avance le suivant 
        while (ap->HAUTEUR <= i) {
            ap = ap->SUIV[ap->HAUTEUR - 1];
        }
		//on fait les connexions entre le suivant et le precedant
        p->SUIV[i] = ap;
        ap->PREC[i] = p;
    }
	//on supprime C
    delete C;
	// la taille de la liste diminue
    SIZE--;

	//on retourne la case juste apres celle qu'on vient de suppprimer.
    return retour;
}

/////////////////////////////////////////////////
// set
// fonctions publiques
/////////////////////////////////////////////////

/**
* \ brief Constructeur par copie
* \ param [in] Liste a copier
*/
template<typename TYPE>
set<TYPE>::set(const set<TYPE> &source) : set() {
	//Insert les elements de la source un par un
    for (const TYPE &x:source) insert(x);

}
/**
* \ brief Destructeur
*/
template<typename TYPE>
set<TYPE>::~set() {
	//Vide le tableau
	clear();
	// Supprime la cellule de fin et de debut
	delete DEBUT->PREC[0];
	delete DEBUT;

}
/**
* \ brief Foncionne qui vide le tableau de tous ces elements
* \ param [in] void
* \ return void
*/
template<typename TYPE>
void set<TYPE>::clear() {
	//initialise le tableau au premier element
    iterator it = begin();
	// pour chaque element jusqu'a la fin, on supprime l'element
    while (it != end()) {
        it = erase(it);
    }

}
/**
* \ brief Fonction qui recherche une valeur dans la skipList
* \ param [in] une valeur X à trouver
* \ return return un iterator pointant vers la cellule de la valeur x ou un iterator null si on ne trouve pas la valeur
*/
template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::find(const TYPE &X) {
    iterator *it;
	// h est la hauteur de la liste
    int h = DEBUT->HAUTEUR;
	// c est la cellule de recherche, on l'initialise au debut
    cellule *c = DEBUT;
	//On continue la recherche tant qu'on a pas trouve la valeur ou qu'on a pas parcouru toute la hauteur de la skipList
    while (h > 0 && c->CONTENU != X) {
		// si le contenu du suivant de c est plus petit que x a l'etage de la boucle et qu'il n'est pas egal a la fin, c = au suivant de ce etage
        if (c->SUIV[h - 1]->CONTENU <= X && c->SUIV[h - 1] != DEBUT->PREC[0]) {
            c = c->SUIV[h - 1];

        } else {
			// sinon, on baisse d'etage
            h--;
        }
    }
	// si on a trouve x, on retourne un iteartor pointant vers c (cellule de x)
    if (c->CONTENU == X) {
        it = new iterator(c);
        return *it;
    } else {
		// sinon on retourne un iterator null
        it = new iterator(nullptr);
        return iterator(*it);
    }
}
/**
* \ brief Fonction qui recherche la valeur plus grande ou egale a la valeur de la skiplist
* \ param [in] une valeur X dont on cherche le lowerbound
* \ return return un iterator pointant vers la cellule du lowerbound de x 
*/
template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::lower_bound(const TYPE &X) {

	// c est la cellule de recherche, on l'initialise au debut
    cellule *c = DEBUT;
	// h est la hauteur de la liste
    size_t h = c->HAUTEUR;
	//valeur qui nous permet de dire si on a atteint la fin de letage
    bool reachEnd = false;
	// pour tous les etages partant du plus haut
    for (int i = h - 1; i >= 0; i--) {
		// si le contenu du suivant de c est plus petit que x a l'etage de la boucle et qu'il n'est pas egal a la fin, c = au suivant de ce etage
        while (c->SUIV[i]->CONTENU < X && !reachEnd) {
            if (c->SUIV[i] != DEBUT->PREC[0]) {
                c = c->SUIV[i];

            } else reachEnd = true;// moment pour changer d'etage
        }
        reachEnd = false;
    }
	// Retourne le suivant de c qui va necesairement etre plus grand ou egal a x
    return iterator(c->SUIV[0]);
}

template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::upper_bound(const TYPE &X) {
    cellule *c = DEBUT;
    size_t h = c->HAUTEUR;
    bool reachEnd = false;
    for (int i = h - 1; i >= 0; i--) {

        while (c->SUIV[i]->CONTENU < X && !reachEnd) {
            if (c->SUIV[i] != DEBUT->PREC[0]) {
                c = c->SUIV[i];
            } else reachEnd = true;
        }
        reachEnd = false;
    }
    if (c->SUIV[0]->CONTENU == X) c = c->SUIV[0];
    return iterator(c->SUIV[0]);
}

template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::insert(iterator it, const TYPE &X) {
    /*... a effacer et completer ...*/
    cellule *p = it.POINTEUR;

    if (p->CONTENU > X && p->PREC[0]->CONTENU < X) {

        return iterator(insert(it.POINTEUR, X));
    } else {
        return insert(X).first;
    }
}

template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(const TYPE &VAL) {
    /*... a effacer et completer ...*/
    iterator it = find(VAL);
    if (it == nullptr) {
        return lower_bound(VAL);
    } else {
        return iterator(erase(it.POINTEUR));

    }
}

template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(iterator it) {
    return iterator(erase(it.POINTEUR));
}

#endif
