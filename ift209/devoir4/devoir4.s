.include "macros.s" //"
.global main
main:
							//Lire String
	adr 	x0, fmtLecture
	adr 	x1, chaine
	bl 		scanf
							//Lire Commande
	adr		x0, fmtNombre
	adr 	x1, nombre
	bl 		scanf
	ldr 	x19, nombre

							//On redirige vers la bonne operation en comparant le code avec les differentes options
	cmp x19,0
	b.eq OperationZero
	cmp x19,1
	b.eq OperationUn
	cmp x19,2
	b.eq OperationDeux
	cmp x19,3
	b.eq OperationTrois
	cmp x19,4
	b.eq OperationQuatre
	cmp x19,5
	b.eq OperationCinq

/*******************************************************************************
  Opération 0
  Usage: x19 - i    x21 -   NUM_LIGNES - 4
         x20 - j    x22 - NUM_COLONNES - 4
*******************************************************************************/
OperationZero:

	b 		End					// On va direct a la fin du programme




/*******************************************************************************
  Opération 1
  Usage: x19 - Adresse du string
		 x20 - Valeur de la variable
		 x21 - Position de la variable
		 x22 - Variable de retour isPair
         x28 - Nombre de caracteres

*******************************************************************************/
OperationUn:
	bl		CompterCarac
	adr 	x19, chaine
	mov 	x28, x0				// x20 = Nombre de caracteres
	add		x20, x28, -1		

	mov		x21, 1				// x21 = Position de l'element dans la string 

BoucleOne:
	ldrb	w20, [x19], 1		// Lit un caractere dans x20

	mov		x0, x21				// Position passe en parametre a la fct PairOuImpaire
	bl		IsPair
	mov		x22, x0				// Variable Pair(1) ou Impaire(0)

	mov		x0, x22				// Variable pair ou impaire passe en parametre
	mov		x1, x20				// Valeur de la variable passe en parametre
	bl		PairImpaire
	mov		x20, x1				// Retourne la variable un fois change

	mov		x0, x20				// variable passe en parametre
	bl		Voyelles
	mov		x20, x0				// Retourne la nouvelle variabale

	adr		x0, fmtAffichage	// On affiche la string
	mov		x1, x20
	bl		printf

	cmp		x21, x28			// Tant que x21 n'est pas egal a x28 refaire BoucleOne
	add		x21, x21, 1			// Incremente la position de la variable

	b.ne	BoucleOne
	b 		End					// On va direct a la fin du programme

/*******************************************************************************
  Opération 2
  Usage: x19 - Adresse du string		x22 - Somme total en decimal
         x20 - Longueur de la string	x23 - Multiplicateur de 16
		 x21 - Variable puissance
*******************************************************************************/

OperationDeux:
	bl		CompterCarac
	adr 	x19, chaine
	mov 	x20, x0				// x20 = CompterCarac-1
	add		x20, x20, -1
	add		x19, x19, x20		// On ajoute x20 a x19 pour commencer a la fin du string

	mov		x21, 1				// La puissance (a incrementer)
	mov		x22, 0				// Somme total
	mov		x23, 16				// Multiplicateur de 16

BoucleDeux:

	ldrb	w20, [x19], -1		// Lit un caractere (On commence a la fin) dans x20
	
	cmp		x20, 120			// Si x20 = 120 soit x
	b.eq	DeuxEnd				// Condition de sorti de la boucle

	mov		x0, x20				// Mettre x20 comme parametre de HexaConvert
	bl		HexaConvert			// Convertir le x20 en nombre decimal
	mov		x20, x0				// Mettre la valeur de retour dans x20

	mul		x20, x20, x21		// Multiplier par la puissance
	add		x22, x22, x20		// Incrementer le total

	mul		x21, x21, x23		// Mettre a jour la puissance

	b		BoucleDeux

DeuxEnd:
	adr		x0, fmtNombre		//On affiche le total
	mov		x1, x22
	bl		printf

	b 		End					// On va direct a la fin du programme

/*******************************************************************************
  Opération 3
  Usage: x19 - adresse du string
         x20 - caractere lu transforme en sa valeur numerique decimale
		 x21 - caractere precedant x20
		 x22 - puissances de 2 qu'on multiplie
		 x23 - nombre total (somme de tous le x20)
		 x24 - registre contenant la valeur 2
		 x25 - nombre de caractere -1
*******************************************************************************/
OperationTrois:

	bl		CompterCarac
	adr		x19, chaine
	mov 	x25, x0				// x25 = CompterCarac-1
	add		x25, x25, -1
	add		x19, x19, x25		// On ajoute x25 a x19 pour commencer a la fin du string

	mov		x22, 1				// On initialise le multiplicateur a 1
	mov 	x23, 0				// On initialise le total a 0
	mov		x24, 2				// On associe la valeur 2 a x24
BoucleTrois:
	ldrb	w20, [x19],-1		// Lit un caractere (On commence a la fin) dans x20
	ldrb	w21, [x19]			// On met son precedant dans x21

	add		x20, x20, -48		// Pour avoir les valeurs numeriques de 0 et 1
	mul		x20, x20, x22		// On multiplie par la puissance de 2

	cmp 	x21,98				// On regarde si le percedant est b
	b.eq	TroisEnd			// Si oui fin de la boucle
	add		x23, x23, x20		// Sinon on ajoute x20 au total x23
	mul		x22, x22, x24
	b		 BoucleTrois		// On recommence pour le caractere suivant
TroisEnd:
	sub		x23, x23, x20		//On soustrais la derniere puissance de 2 (entier signe)

	adr		x0, fmtNombre		//On affiche le total	
	mov		x1, x23
	bl		printf

	b 		End



/*******************************************************************************
  Opération 4
  Usage: x19 - adresse du string
  		 x20 - variable temporaire du caractere qu'on lit
		 x21 - caractere resultant des operations
		 x22 - registre temporaire pour separer les parties de x20
		 x23 - IDEM x22
*******************************************************************************/
OperationQuatre:
	adr		x19, chaine		//x19 Adressage de la chaine

BoucleQuatre:
	ldrb	w20, [x19],1	//On lit un caractere et on tasse le pointeur de un caractere
	cmp 	x20,0			//si caractere null
	b.eq	End				// On va direct a la fin du programme


	and		x21, x20, 224	//masque 1 aaa00000
	and		x22, x20, 24	//masque 2 000bb000
	and		x23, x20, 7		//masque 3 00000ccc

	ror		x22, x22, 3		//decallage	000000bb
	ror		w23, w23, 30	//decallage 000ccc00

	orr		x21, x21, x22	//combinaison aaabb000
	orr		x21, x21, x23	//combinaison aaabbccc

	add		x21, x21, -7	//On fait le deplacement de 7

	cmp 	x21, 65			//Si plus haut que 65 (A)
	b.hs	QuatreAffichage	//On va afficher
	add		x21, x21, 26	//Sinon on ajoute 26 pour faire un bon circulaire

QuatreAffichage:
	adr 	x0, fmtAffichage//Affichage caractere
	mov 	x1, x21
	bl 		printf
	b		BoucleQuatre 	//On recommence pour tous les caracteres



/*******************************************************************************
  Opération 5
  Usage: x19 - adresse du string
*******************************************************************************/
OperationCinq:

	mov		x0, 0			//On appelle la fonction permuter avec 0 comme argument
	adr		x19, chaine
	bl 		Permuter

	b		End				// On va direct a la fin du programme

End:
	adr		x0, sautDeLigne
	bl 		printf
	mov		x0, 0
	bl 		exit

/////////////////////////////    FONCTIONS   ///////////////////////////////////

/*******************************************************************************
  Fonction Permuter
  Entrée: x0 Postion dans String
  Sortie: -
  Usage: x19 - Adresse chaine
         x20 - Variable temporaire du caractere pour affichage
		 x21 - Nombre de caractere -1
		 x22 - Argument x0 Postion dans String
		 x24 - Compteur pour tous les caracteres suivant x22
		 x25 - Registre temporaire pour swap de 2 emplacements memoire
		 x26 - IDEM x25
*******************************************************************************/
Permuter:

	SAVE


	mov		x22, x0			//x22 argument (position dans le string)
	adr		x19, chaine		//x19 Adressage de la chaine

	bl CompterCarac
	mov 	x21, x0
	add 	x21, x21, -1	//x21 compteur du (nombre de caractere -1)

	cmp		x21,x22			// si position dans string = longeur string-1
	b.eq	EndRecursive	//On affiche le string actuel


	mov		x24, x22		//x24 est le compteur pour tous les caracteres suivant x22
	adr 	x19, chaine		//x19 Adressage de la chaine

BoucleRecursive:
	ldrb	w25, [x19,x22]	//  ici, on inverse string[x22] et string[x24]
	ldrb	w26, [x19,x24]
	strb	w26, [x19,x22]
	strb	w25, [x19,x24]

	mov 	x0, x22			//on appelle ensuite la fonction avec x22+1
	add		x0, x0, 1
	bl		Permuter

	ldrb	w25, [x19,x22]	//On re-inverse les 2 valeurs de plus haut
	ldrb	w26, [x19,x24]
	strb	w26, [x19,x22]
	strb	w25, [x19,x24]

	cmp		x24, x21		//Tant que compteur < taille string-1
	add		x24,x24,1		//Incrementation du compteur
	b.lo	BoucleRecursive
	b EndPermuter			//On va a la fin de la fonction


EndRecursive:				//lorsque postion dans string = taille string -1
	adr		x19, chaine
EndRecursiveBoucle:			//On ne fait qu'afficher caractere par caractere

	adr 	x0, fmtAffichage
	ldrb	w20, [x19],1
	mov		w1, w20
	bl printf
	cmp 	x20, 0
	b.ne	EndRecursiveBoucle//Tant qu'on est pas au caractere null

	adr		x0, space		  //On ajoute un espace a la fin de chaque string
	bl		printf
	b EndPermuter

EndPermuter:
	RESTORE
	ret						//rien à retourner

/*******************************************************************************
  Fonction CompterCarac
  Entrée: -
  Sortie: Nombre de caractere dans chaine de caracteres
  Usage: x19 - Adresse chaine
         x20 - Variable temporaire du caractere pour compter
		 x21 - Compteur du nombre de caractere
*******************************************************************************/


CompterCarac:
	SAVE
	adr		x19, chaine
	mov 	X21, -1
CompterCaracBoucle:			//met le nombres de caracteres dans x21
	ldrb	w20, [x19],1	//lis un caractere
	cmp 	x20,0
	add		x21,x21,1		//augmente le compteur
	b.ne	CompterCaracBoucle	//tant qu'on est pas au caractere null
	mov 	x0, x21
	RESTORE
	ret

/*******************************************************************************
  Fonction HexaConvert
  Entrée: x0 caractere hexadecimal en ASCII
  Sortie: x0 caractere en decimal
  Usage: x20 - Variable du caractere
		 x21 - Nombre en decimal
*******************************************************************************/
HexaConvert:

	SAVE

	mov		x20, x0
	mov		x21, 0				// Nombre en decimal

	cmp		x20, 58				// Si x20 est strictement plus petit que 58
	b.lo	LessThanTen

	cmp		x20, 65				// Si x20 = 65 soit A
	b.eq	A

	cmp		x20, 66				// Si x20 = 66 soit B
	b.eq	B

	cmp		x20, 67				// Si x20 = 67 soit C
	b.eq	C

	cmp		x20, 68				// Si x20 = 68 soit D
	b.eq	D

	cmp		x20, 69				// Si x20 = 69 soit E
	b.eq	E

	cmp		x20, 70				// Si x20 = 70 soit F
	b.eq	F

A:
	add		x21, x21, 10		// x21 = 10
	b		EndHexaConvert
B:
	add		x21, x21, 11		// x21 = 11
	b		EndHexaConvert
C:
	add		x21, x21, 12		// x21 = 12
	b		EndHexaConvert
D:
	add		x21, x21, 13		// x21 = 13
	b		EndHexaConvert
E:
	add		x21, x21, 14		// x21 = 14
	b		EndHexaConvert
F:
	add		x21, x21, 15		// x21 = 15
	b		EndHexaConvert

LessThanTen:
	add		x21, x20, -48		// x21 = x20 - 48

EndHexaConvert:
	mov		x0, x21

	RESTORE
	ret

/*******************************************************************************
  Fonction PairImpair
  Entrée: x0 Valeur a verifier
  		  x1 Valeur de la variable ASCII
  Sortie: x0 Valeur de la nouvelle variable
  Usage: x19 - Boolean Pair(1) ou Impaire(0)
		 x20 - Valeur de la variable ASCII
*******************************************************************************/
PairImpaire:
	SAVE

	mov		x19, x0				// Boolean Pair(1) ou Impaire(0)
	mov		x20, x1				// Valeur de la variable ASCII

	cmp 	x20, 65				// Si(x20 < 65) {
	b.lo	isWeird				// 		isWeird }
	cmp		x20, 90				// Si(x20 > 90){
	b.hi	isMinuscule			// 		isMinuscule }

	cmp		x19, 1				// Si( position actuelle = pair){
	b.eq	Fin					// 		Fin }

MajToMin:						// Sinon {
	add		x20, x20, 32		// 		Incrementer x20 de 32
	mov		x1, x20				//		valeur a retourner
	b		Fin					//		Fin }

isMinuscule:					// Si x20 est une minuscule ou autre
	cmp		x20, 97				// Si( x20 < 97 ){
	b.lo	isWeird				//		isWeird }
	cmp		x20, 122			// Si( x20 > 122 ) {
	b.hi	isWeird				//		isWeird }

	cmp		x19, 0				// Si( position acutelle = impair ){
	b.eq	Fin					// 		Fin }

MinToMaj:						// Sinon {
	sub		x20, x20, 32		//		Soustraire 32 a x20
	mov		x1, x20				//		Valeur de retour
	b		Fin					//		Fin }

isWeird:						// IsWeird = Tout se qui nest pas de l'alphabet
	mov		x1, x20				// Valeur de retour

Fin:
	RESTORE
	ret
//////////////////////////////////////

.section ".data"
// Mémoire allouée pour une chaîne de caractères d'au plus 1024 octets
chaine: .skip 1024
.section ".rodata"
// Format pour lire une chaîne de caractères d'une ligne (incluant des espaces)
fmtLecture: .asciz "%[^\n]s"

fmtAffichage: .asciz "%c"
fmtNombre:    .asciz "%ld"
space:		  .asciz " "
sautDeLigne:  .asciz "\n"

.section ".bss"
nombre:		.skip 1
