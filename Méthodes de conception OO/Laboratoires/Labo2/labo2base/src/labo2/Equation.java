package labo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Equation extends Vecteur{
	private ArrayList<Character> variables;
    int constante = 0;
	/*
	 * Fonction qui transforme en String contenant une �quation lin�aire une liste de
	 * variables et de coefficients s�par�es.
	 */
	@Override
	public  String toString() {

		String res = "";
		res += (int)valeurs[0] + "" + variables.get(0);

		for (int i = 1; i < variables.size(); i++) {

			int coeff = (int) valeurs[i];
			if (coeff < 0) {
				res += " - ";
			} else {
				res += " + ";
			}

			res += Math.abs(coeff) + "" + variables.get(i);
		}

		res += " = " + constante;
		return res;

	}


	
	public ArrayList<Character> getVariables()
	{
		return variables;
	}
	
	public int getConstante()
	{
		return constante;
	}
	
	
	/*
	 * Fonction qui extrait une liste de variables (une lettre par variable) et
	 * une liste des coefficients qui leur correspond d'une String contenant une
	 * une �quation lin�aire. Extrait �galement la constante � droite du =.
	 * Lance des exceptions dans plusieurs des cas probl�matiques.
	 */
	
	public static Equation lireEquation(String source)
	{
		 ArrayList<Character> variablestemp = new ArrayList<Character>();
		 ArrayList<Integer> coefficientstemp = new ArrayList<Integer>() ;
		 int constantetemp=0;

		final String delims = "+-=";
		HashMap<Character, Integer> signes = new HashMap<Character, Integer>();
		signes.put('+', 1);
		signes.put('-', -1);

		int lastSign = 1;
		boolean complete = false;

		StringTokenizer tokenizer = new StringTokenizer(source, delims, true);

		while (tokenizer.hasMoreTokens()) {

			String val = tokenizer.nextToken().trim();
			if (val.length() == 0)
				continue;

			if (isOperator(val)) {

				lastSign = signes.get(val.charAt(0));

			} else if (isOperatorEquals(val)) {

				if (!tokenizer.hasMoreTokens())
					throw new IllegalArgumentException("Equation: �quation mal form�e (constante manquante � la fin");

				constantetemp = Integer.parseInt(tokenizer.nextToken().trim());
				complete = true;
				break;

			} else {

				int coeff = Integer.parseInt(val.substring(0, val.length() - 1));
				char var = val.charAt(val.length() - 1);

				if (variablestemp.contains(var))
					throw new IllegalArgumentException("Equation: �quation mal form�e (variable dupliqu�e");

				variablestemp.add(var);
				coefficientstemp.add(coeff * lastSign);
			}
		}
		if (tokenizer.hasMoreTokens())
			throw new IllegalArgumentException("Equation: �quation mal form�e (expression continue apr�s la constante");
		if (!complete)
			throw new IllegalArgumentException("Equation: �quation mal form�e (manque = constante � la fin)");
		
		return new Equation(variablestemp,coefficientstemp,constantetemp);


	}
	
	
	public Equation(ArrayList<Character> variables, ArrayList<Integer> coefficients,
			int constante) {
		super(coefficients);
		this.variables = variables;
		this.constante=constante;
		
	}
	
	/*
	 * Fonctions utilitaires pour reconna�tre les op�rateurs.
	 */

	private static boolean isOperator(String c) {

		if (c.length() == 1) {
			return c.charAt(0) == '+' || c.charAt(0) == '-';
		}

		return false;

	}

	private static boolean isOperatorEquals(String c) {

		if (c.length() == 1) {
			return c.charAt(0) == '=';
		}

		return false;
	}
	
	@Override
	public boolean equals(Object eq)
	{
		if(constante==((Equation)eq).constante) {
			if(super.equals(eq))
			{
				if(variables.equals(((Equation)eq).variables))
				{
					return true;
				}
				else return false;
			}
			else return false;
			
				
		}
		
		else return false;

	}

}
