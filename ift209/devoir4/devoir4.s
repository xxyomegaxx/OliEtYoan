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



OperationZero:

	b 		End				// On va direct a la fin du programme


OperationUn:

	b 		End				// On va direct a la fin du programme



OperationDeux:

	b 		End				// On va direct a la fin du programme



OperationTrois:




	b		 End			// On va direct a la fin du programme



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

	cmp 	x21, 65			//Si plus haut que 41 (A)
	b.hs	QuatreAffichage	//On va afficher
	add		x21, x21, 26	//Sinon on ajoute 26 pour faire un bon circulaire

QuatreAffichage:
	adr 	x0, fmtAffichage//Affichage caractere
	mov 	x1, x21
	bl 		printf
	b		BoucleQuatre 	//On recommence pour tous les caracteres




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

////////////////////////////////////////////
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

	cmp		x24, x21		//Tant que compteur < taille string
	add		x24,x24,1		//Incrementation du compteur
	b.lo	BoucleRecursive
	b EndQuestionCinq		//On va a la fin de la fonction


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
	b EndQuestionCinq

EndQuestionCinq:
	RESTORE
	ret						//rien à retourner




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
//////////////////////////////////////



.section ".data"
// Mémoire allouée pour une chaîne de caractères d'au plus 1024 octets
chaine: .skip 1024
.section ".rodata"
// Format pour lire une chaîne de caractères d'une ligne (incluant des espaces)
fmtLecture: .asciz "%[^\n]s"

fmtAffichage: .asciz "%c"
fmtNombre:    .asciz "%lu"
space:		  .asciz " "
sautDeLigne:	  .asciz "\n"

.section ".bss"
nombre:		.skip 1
