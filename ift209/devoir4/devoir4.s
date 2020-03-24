.include "macros.s" //"
.global main
main:
	adr 	x0, fmtLecture
	adr 	x1, chaine
	bl 		scanf

	//Lire Commande
	adr x0, fmtNombre
	adr x1, nombre
	bl scanf
	ldr x19, nombre


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

	b 		End


OperationUn:

	b 		End			// On va direct a la fin



OperationDeux:

	b 		End			// On va direct a la fin



OperationTrois:

	b		 End		// On va direct a la fin



OperationQuatre:

	b 		End			// On va direct a la fin

OperationCinq:

	mov		x0, 0
	adr		x19, chaine
	bl 		Permuter

	b		End			// On va direct a la fin

End:
	mov		x0, 0
	bl 		exit

////////////////////////////////////////////
Permuter:

	SAVE

	mov 	x21, -2
	mov		x22, x0
	adr		x19, chaine
CompterCarac:
	ldrb	w20, [x19],1
	cmp 	x20,0
	add		x21,x21,1
	b.ne	CompterCarac


	cmp		x21,x22
	b.eq	EndRecursive

	mov 	x23, x22//cur
	add		x23, x23, 1//cur+1
	mov		x24, x22//compteur
	adr 	x19, chaine

BoucleRecursive:
	ldrb	w25, [x19,x22]//temp
	ldrb	w26, [x19,x24]
	strb	w26, [x19,x22]
	strb	w25, [x19,x24]
	mov 	x0, x22
	add		x0, x0, 1
	bl		Permuter
	ldrb	w25, [x19,x22]
	ldrb	w26, [x19,x24]
	strb	w26, [x19,x22]
	strb	w25, [x19,x24]
	cmp		x24, x21
	add		x24,x24,1
	b.lo	BoucleRecursive
	b EndQuestionCinq


EndRecursive:
	adr		x19, chaine
EndRecursiveBoucle:

	adr 	x0, fmtAffichage
	ldrb	w20, [x19],1
	mov		w1, w20
	bl printf
	cmp 	x20, 0
	b.ne	EndRecursiveBoucle

	adr		x0, space
	bl		printf
	b EndQuestionCinq

EndQuestionCinq:
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

.section ".bss"
nombre:		.skip 1
