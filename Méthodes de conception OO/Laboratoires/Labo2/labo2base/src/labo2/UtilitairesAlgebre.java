package labo2;

/*
 * Obtention de données test:
 * http://planetcalc.com/3571/
 * Classe "preuve de concept". Démontre l'utilisation des fonctions
 * et classes de base du labo. Exemple d'une mauvaise façon d'écrire 
 * des tests (surtout quand le code prend de l'expansion).
 * Contient des tests extrêmement minimaux, ne prouve pas beaucoup 
 * le bon fonctionnement du code écrit.
 */

public class UtilitairesAlgebre {

	public static void main(String[] args) {

		/*
		 * Ensemble de données déclarées pour effectuer des tests.
		 */
		Equation eq = Equation.lireEquation("3x+6y+7zj+9w=9");
		System.out.println(eq);

	}


}
