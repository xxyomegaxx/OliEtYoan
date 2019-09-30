package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import labo2.Equation;

public class TestEquation {
	
	
	
	@Test
	public void testEquation() {
		ArrayList<Character> variables = new ArrayList<Character>();
		variables.add('x');
		variables.add('y');
		variables.add('z');
		variables.add('w');
		ArrayList<Integer> coefficients = new ArrayList<Integer>();
		coefficients.add(3);
		coefficients.add(6);
		coefficients.add(7);
		coefficients.add(9);
		int constante =9; 
		
		Equation eq = new Equation("3x+6y+7z+9w=9");
		assertEquals(eq.getCoef(),coefficients);
		assertEquals(eq.getVariables(),variables);
		assertEquals(eq.getConstante(),constante);		
	}
	@Test
	public void testToString()
	{
		Equation eq = new Equation("3x+6y+7z-9w=9");
		String resAttendu = "3x + 6y + 7z - 9w = 9";
		assertEquals(eq.toString(),resAttendu);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationManqueConstante() {
		
		Equation eq = new Equation("3x+6y+7z+9w=");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationVariablesDupliquee() {
		
		Equation eq = new Equation("3x+6y+7z+9x=9");
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void testEquationContinueApresConstante() {
		
		Equation eq = new Equation("3x+6y+7z+9w=9+");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationManqueEgaleEtConsante() {
		
		Equation eq = new Equation("3x+6y+7z+9w");
		
	}
	@Test(expected=NumberFormatException.class)
	public void testEquationPlusieursOperateurs() {
		
		Equation eq = new Equation("3x+6y+7zk -9w=9");
		String resAttendu = "3x + 6y + 7z - 9w = 9";
		assertEquals(eq.toString(),resAttendu);
		
	}



}
