package labo2;

/*
 * Obtention de donn�es test:
 * http://planetcalc.com/3571/
 * Classe "preuve de concept". D�montre l'utilisation des fonctions
 * et classes de base du labo. Exemple d'une mauvaise fa�on d'�crire 
 * des tests (surtout quand le code prend de l'expansion).
 * Contient des tests extr�mement minimaux, ne prouve pas beaucoup 
 * le bon fonctionnement du code �crit.
 */

public class UtilitairesAlgebre {

	public static void main(String[] args) {

		/*
		 * Ensemble de donn�es d�clar�es pour effectuer des tests.
		 */
		Equation eq = Equation.lireEquation("3x+6y+7zj+9w=9");
		System.out.println(eq);

	}


}
