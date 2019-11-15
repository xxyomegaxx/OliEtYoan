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
    size_t hauteurAvant = DEBUT->HAUTEUR; //hauteur initiale
    size_t hauteur = tirer_hauteur_au_hasard(); //hauteur tirer haut hasard
    TYPE *y = new TYPE(X); //Copie de x
    cellule *nouveau = new cellule(*y, hauteur);//Creation nouvelle cellule

    //Si la hauteur est plus grande que la hauteur iniatiale
    if (hauteur > hauteurAvant) {
        cellule **nouveauDebut = new cellule *[hauteur]; //Creation d'une nouvelle cellule de DEBUT
        cellule **nouveauFin = new cellule *[hauteur]; //Creation d'une nouvelle cellule FIN
        //Pour tous les niveaux jusqu'a hauteurAvant
        for (size_t i = 0; i < hauteurAvant; i++) {
            //Assigner les adresses memoires de la cellule DEBUT a la cellule nouveauDebut
            nouveauDebut[i] = DEBUT->SUIV[i];
            //Assigner les adresses memoires de la cellule FIN a la cellule nouveauFin
            nouveauFin[i] = DEBUT->PREC[0]->PREC[i];
        }

        //Liberer l'espace memoire de la vielle cellule FIN
        delete[] DEBUT->PREC[0]->PREC;
        //Liberer l'espace memoire de la vielle cellule DEBUT
        delete[] DEBUT->SUIV;

        //Assignation necessaire pour realouer memoire par celle de nouveauDebut et nouveauFin
        DEBUT->SUIV = nouveauDebut;
        DEBUT->PREC[0]->PREC = nouveauFin;
        DEBUT->HAUTEUR = hauteur;
        DEBUT->PREC[0]->HAUTEUR = hauteur;

        //Pour tous les niveaux superieur a hauteurAvant
        for (size_t i = hauteurAvant; i < hauteur; i++) {
            //Assigner les adresses memoire de les niveaux a la nouvelle cellule vers DEBUT
            //et DEBUT vers la nouvelle cellule.
            DEBUT->SUIV[i] = nouveau;
            nouveau->PREC[i] = DEBUT;
            //Assigner les adresses memoire de les niveaux a la nouvelle cellule vers FIN
            //et FIN vers la nouvelle cellule.
            DEBUT->PREC[0]->PREC[i] = nouveau;
            nouveau->SUIV[i] = DEBUT->PREC[0];
        }

    }

    //Assignation de la cellule a la cellule qui precedent ap
    cellule *p = ap->PREC[0];

    //De 0 a la valeur minimale entre hauteurAvant et hauteur
    for (int i = 0; i < min(hauteurAvant, hauteur); i++) {
        //Tant que la i est plus petit que la hauteur de p
        while (p->HAUTEUR <= i) {
            //Assigner les cellules plus haute que la nouvelle cellule
            p = p->PREC[p->HAUTEUR - 1];
        }
        //Assigner les cellules precedente de la nouvelle cellule vers p
        nouveau->PREC[i] = p;
        //Assigner les cellules suivante de p vers la nouvelle cellule
        p->SUIV[i] = nouveau;
        //Tant que i est plus petit que la hauteur de ap
        while (ap->HAUTEUR <= i) {
            //Assigner les cellules plus haute que la nouvelle cellule
            ap = ap->SUIV[ap->HAUTEUR - 1];
        }
        //Assigner les cellules suivante de la nouvelle cellule vers ap
        nouveau->SUIV[i] = ap;
        //Assigner les cellules precedente de ap vers la nouvelle cellule
        ap->PREC[i] = nouveau;
    }
    //Incrementer le size du set
    SIZE++;
    //retourner la nouvelle cellule
    return nouveau;
}

/**
* \ brief Fonction de supression d'une certaine cellule
* \ param [in] Une cellule de certain TYPE
* \ return return La nouvelle cellule d'un certain TYPE qui remplace celle qu'onn vient de supprimer
*/
template<typename TYPE>
typename set<TYPE>::cellule *set<TYPE>::erase(typename set<TYPE>::cellule *C) {

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
	// c est la cellule de recherche, on l'initialise au debut
	cellule* c = DEBUT;
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

			}
			else reachEnd = true;// moment pour changer d'etage
		}
		reachEnd = false;
	}
    //Si la contenu de x au niveau 0 est egal a X
    if (c->SUIV[0]->CONTENU == X) {
        //Assigner c a la cellule suivante
        c = c->SUIV[0];
    }
	// Retourne le suivant de c qui va necesairement etre plus grand que x
    return iterator(c->SUIV[0]);
}

/**
* \ brief Fonction public d'insertion qui s'assure que que la contenu pointer par
*         l'iterateur passer en parametre est bonne
* \ param [in] Un iterator
 *             Une valeur X d'un certain TYPE
* \ return return Si la position est bonne elle retourne un iterator qui appel la fonction privee
 *                Sinon elle retourne un iterateur appele par la fonction public qui trouve l
 *                a position approprie
*/
template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::insert(iterator it, const TYPE &X) {
    cellule *p = it.POINTEUR;//Creation d'une cellule pointer par le pointeur
    //Si le contenu de p est plus grand que X et que le contenu du precedent de p est plus petit
    if (p->CONTENU > X && p->PREC[0]->CONTENU < X) {
        //retourne un iterator appeler par la fonction privee d'insertion
        return iterator(insert(it.POINTEUR, X));
    } else {
        //Sinon est retourne un iterateur retourner par l'autre fonction public d'insertion.
        return insert(X).first;
    }
}

/**
* \ brief Fonction public de suppresion dans le cas ou on lui passe une valeur en parametre.
* \ param [in] Un valeur avec un certain TYPE
* \ return return un iterator qui appel la fonction de suppression privee
*/
template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(const TYPE &VAL) {
    /*... a effacer et completer ...*/
    iterator it = find(VAL); //Creation d'un iterator avec la position de la valeur
    //Si la valeur n'est pas dans la set
    if (it == nullptr) {
        //retourner le lowerbound de la valeur
        return lower_bound(VAL);
    } else {
        //retourner un iterateur qui appel fonction de suppression privee
        return iterator(erase(it.POINTEUR));
    }
}

/**
* \ brief Fonction public de suppresion dans le cas ou on lui passe un iterator en parametre.
* \ param [in] un iterator
* \ return return un iterator qui appel la fonction de suppression privee
*/

template<typename TYPE>
typename set<TYPE>::iterator set<TYPE>::erase(iterator it) {
    //retourner un iterateur qui appel la fonction de suppression privee
    return iterator(erase(it.POINTEUR));
}

#endif
