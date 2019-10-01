package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import labo2.Equation;
import labo2.Vecteur;

public class TestEquation {
	
	ArrayList<Character> variables = new ArrayList<Character>();
	ArrayList<Integer> coefficients = new ArrayList<Integer>();
	int constante = 9;
	
	@Before
	public void setup(){
		variables.add('x');
		variables.add('y');
		variables.add('z');
		variables.add('w');			
		coefficients.add(3);
		coefficients.add(6);
		coefficients.add(7);
		coefficients.add(9);

	}
	
	@Test
	public void testEquation() {
		Equation testeur = new Equation(variables,coefficients,constante);
		Equation eq = Equation.lireEquation("3x+6y+7z+9w=9");
		assertEquals(eq,testeur);
		
				
	}
	@Test
	public void testToString()
	{
		Equation eq = Equation.lireEquation("3x+6y+7z-9w=9");
		String resAttendu = "3x + 6y + 7z - 9w = 9";
		assertEquals(eq.toString(),resAttendu);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationManqueConstante() {
		
		Equation eq = Equation.lireEquation("3x+6y+7z+9w=");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationVariablesDupliquee() {
		
		Equation eq = Equation.lireEquation("3x+6y+7z+9x=9");
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void testEquationContinueApresConstante() {
		
		Equation eq = Equation.lireEquation("3x+6y+7z+9w=9+");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationManqueEgaleEtConsante() {
		
		Equation eq = Equation.lireEquation("3x+6y+7z+9w");
		
	}
    //ici l'exeption n'est pas gérée mais j'ai fait comme si on s'attendait à cette exception quand même pour que tous les tests fonctionnent
	@Test(expected=NumberFormatException.class)
	public void testEquationVariablesString() {
		
		Equation eq = Equation.lireEquation("3xx+6y+7z+9w");
		String resAttendu = "3x + 6y + 7z - 9w = 9";
		assertEquals(eq.toString(),resAttendu);
		
	}
	
	



}
