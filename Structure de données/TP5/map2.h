/**
* \ file set2.h
* \ author Aida Ouangraoua (Nov. 2019)
* \ brief Ce fichier contient les fonctions 
* \ du type map à coder
*/


#ifndef map2_h
#define map2_h


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
}

template <typename Tclef, typename Tvaleur>
void map<Tclef,Tvaleur>::rotation_droite_gauche(noeud*& p){
  /*... a completer ...*/
}


#endif /* map2_h */
