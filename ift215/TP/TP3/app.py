#########################
from flask import Flask, render_template, url_for, session, redirect
### IMPORTER VOS PACKAGES
#########################
import json
import random


app = Flask(__name__)

###########################
### CONFIGUER LE SECRET KEY
###########################
app.config["SECRET_KEY"] = "OCML3BRawWEUeaxcuKHLpw"

@app.route("/")
def index():
    if 'niveau' in session:
            niveau = session.get('niveau')
    else:
        session['niveau'] = genere_niveau().toJSON()
        niveau = genere_niveau()
    return render_template('home.html', niveau = niveau)


@app.route("/demarrer/")
def demarrer():
    niveau = genere_niveau()
    session['niveau'] = niveau.toJSON()

    return redirect(url_for('index'))

@app.route("/selection/")
def selection():
    print(session['niveau'])
    return "selection"

def init():
    insert()

def insert():
    button1 = document.getElementById("tuile")
    button1.addEventListener('mousedown', pressed)

def pressed():
    para = 'A new paragraph'
    container = document.getElementById('textblock')
    newElm = document.createElement('p')
    newElm.innerText = para
    container.appendChild(newElm)

def genere_tuile():
    #génère un nombre entre  0 et 5 , les deux inclus
    return random.randint(0, 5)

class Level:
    def __init__(self):
        #initialiser la grille 
        self.colonnes = 8
        self.lignes = 8
        self.grille = []

    def __str__(self):
        #serialization-JSON encoding-transforme des objets python en des json strings
        return json.dumps(self.toJSON())  

    def toJSON(self):
        return {'colonnes': self.colonnes,
                'lignes': self.lignes,
                'grille': self.grille }

    def fromJSON(self, jsonValue):
        #eserialization-transformer les données JSON en des objets pyton
        if isinstance(jsonValue, str): 
            try:
                jsonObj = json.loads(jsonValue)
            except json.JSONDecodeError:
                return
        else:
            jsonObj = jsonValue

        self.colonnes = jsonObj['colonnes']
        self.lignes = jsonObj['lignes']
        if isinstance(jsonObj['grille'], str):
            self.grille = json.loads(jsonObj['grille'])
        else:
            self.grille = jsonObj['grille']

    def reset(self):
        """"pour chaque case on choisit un type , on met le décalage à 0 
        et on l'initialise comme non selectionnée"""
        
        self.grille = []
        for i in range(0, self.lignes):
            self.grille.append([])
            for j in range(0, self.colonnes):
                self.grille[i].append({'type' : genere_tuile(), 'decalage': 0, 'selectionne': False})
        self.resoudre_sequences()

    def getType(self, ligne, colonne):
        #retourne le type utilisée pour la case de cette grille
        return self.grille[ligne][colonne]['type']

    def setType(self, ligne, colonne, t):
        #définir le type pour une case donnée
        self.grille[ligne][colonne]['type'] = t

    def getDecalage(self, ligne, colonne):
        #retourne le décalage de la case
        return self.grille[ligne][colonne]['decalage']

    def setDecalage(self, ligne, colonne, decalage):
        #définir le décalage pour une case donnée
        self.grille[ligne][colonne]['decalage'] = decalage

    def echange(self, ligne1, colonne1, ligne2, colonne2):
        #echanger les types entre les deux cases
        temp = self.getType(ligne=ligne1, colonne=colonne1)
        self.setType(ligne=ligne1, colonne=colonne1, t=self.getType(ligne=ligne2, colonne=colonne2))
        self.setType(ligne=ligne2, colonne=colonne2, t=temp)

    def decaler_tuiles(self):
        # Décaler tuiles
        for j in range(0, self.colonnes):
            for i in range(self.lignes-1, -1, -1):
                if self.getType(ligne=i, colonne=j) == -1:
                    # insérer une nouvelle tuile aléatoire
                    self.setType(ligne=i, colonne=j, t=genere_tuile())
                else:
                    # échanger la tuile pour la décaler
                    decalage = self.getDecalage(ligne=i, colonne=j)
                    if decalage > 0:
                        self.echange(ligne1=i, colonne1=j, ligne2=i+decalage, colonne2=j)

                # Reset shift
                self.setDecalage(ligne=i, colonne=j, decalage=0)

    def parcours_sequences(self, sequences):
        for sequence in sequences:
            coffset = 0
            loffset = 0
            for _ in range(sequence['longueur']):
                self.setType(ligne=sequence['ligne']+loffset, colonne=sequence['colonne']+coffset, t=-1)
                if sequence['horizontal']:
                    coffset+= 1
                else:
                    loffset+= 1

    def efface_sequences(self, sequences):
        self.parcours_sequences(sequences=sequences)
        for j in range(0, self.colonnes):
            decalage = 0
            for i in range(self.lignes - 1, -1, -1):
                if self.getType(ligne=i, colonne=j) == -1:
                    # Tuile est enlevée , incrémenter le décalage
                    self.setDecalage(ligne=i, colonne=j, decalage=0)
                    decalage += 1
                else:
                    # Set the shift
                    self.setDecalage(ligne=i, colonne=j, decalage=decalage)

    def resoudre_sequences(self, sequences=None):
        if sequences is None:
            sequences = self.trouver_sequences()
        while len(sequences) > 0:
            self.efface_sequences(sequences=sequences)
            self.decaler_tuiles()
            sequences = self.trouver_sequences()

    def trouver_sequences(self):
        sequences = []
        # Trouve les séquences horizontales
        for i in range(0, self.lignes):
            longueur_sequence = 1
            for j in range(0, self.colonnes):
                fin_sequence = False
                if j == self.colonnes-1:
                    fin_sequence = True
                else:
                    if self.getType(ligne=i, colonne=j) == self.getType(ligne=i, colonne=j+1) and self.getType(ligne=i, colonne=j) != -1:
                        longueur_sequence += 1
                    else:
                        fin_sequence = True
                if fin_sequence:
                    if longueur_sequence >= 3:
                        sequences.append({'colonne': j + 1 - longueur_sequence, 'ligne': i, 'longueur': longueur_sequence, 'horizontal': True})

                    longueur_sequence = 1

        # Trouve les séquences verticales
        for j in range(0, self.colonnes):
            longueur_sequence = 1
            for i in range(0, self.lignes):
                fin_sequence = False
                if i == self.lignes-1:
                    fin_sequence = True
                else:
                    if self.getType(i, j) == self.getType(i+1, j) and self.getType(i, j) != -1:
                        longueur_sequence += 1
                    else:
                        fin_sequence = True

                if fin_sequence:
                    if longueur_sequence >= 3:
                        sequences.append({'colonne': j, 'ligne': i + 1 - longueur_sequence, 'longueur': longueur_sequence, 'horizontal': False})
                    longueur_sequence = 1

        return sequences

    def trouver_mouvements_possibles(self):
        mouvements = []
        # Trouver les mouvements horizontaux
        for i in range(0, self.lignes):
            for j in range(0, self.colonnes-1):
                self.echange(i, j, i, j+1)
                sequences = self.trouver_sequences()
                self.echange(i, j, i, j+1)

                if len(sequences) > 0:
                    mouvements.append({'colonne1': j, 'ligne1': i, 'colonne2': j + 1, 'ligne2': i})

        # Trouver les mouvements verticaux
        for j in range(0, self.colonnes):
            for i in range(0, self.lignes-1):
                self.echange(i, j, i+1, j)
                sequences = self.trouver_sequences()
                self.echange(i, j, i+1, j)

                if len(sequences) > 0:
                    mouvements.append({'colonne1': j, 'ligne1': i, 'colonne2': j, 'ligne2': i + 1})

        return mouvements

    def positionValide(self, ligne, colonne):
        #s'assurer qu'il s'agit bien d'une position valide dans la grille
        return 0 <= ligne < self.lignes and 0 <= colonne < self.colonnes

    def execute_echange(self, ligne1, colonne1, ligne2, colonne2):
        # On peut échanger deux tuiles que si elles sont adjacentes l'une à l'autre
        if abs(ligne1-ligne2) == 1 and colonne1 == colonne2 or abs(colonne1 - colonne2) == 1 and ligne1 == ligne2:
            if self.positionValide(ligne=ligne1, colonne=colonne1) and self.positionValide(ligne=ligne2, colonne=colonne2):
                self.echange(ligne1, colonne1, ligne2, colonne2)
                # Est-ce que l'échange a produit une nouvelle séquence?
                sequences = self.trouver_sequences()
                if len(sequences) > 0:
                    # Oui, on résout les séquences et on désélectionne l'ancienne case
                    self.resoudre_sequences(sequences=sequences)
                    self.deselectionne_tout()
                    print('Bonne séquence')
                else:
                    # L'échange n'a rien donné, on annule le mouvement
                    self.echange(ligne1, colonne1, ligne2, colonne2)
                    print('mauvaise séquence')

    def selectionne_tuile(self, ligne, colonne):
        self.deselectionne_tout()
        self.grille[ligne][colonne]['selectionne'] = True

    def deselectionne_tout(self):
        for i in range(self.lignes):
            for j in range(self.colonnes):
                self.grille[i][j]['selectionne'] = False


def genere_niveau():
    """"genere une grille avec des mouvements possibles 
    pour ne pas tomber dans un cas ou le joueur ne peut rien faire"""

    level = Level() 
    valide = False
    while not valide:
        level.reset()
        valide = len(level.trouver_mouvements_possibles()) > 0

    return level

######################
### AJOUTER VOS ROUTES
######################

if __name__ == '__main__':
    app.run(debug=True)

