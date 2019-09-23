package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import labo2.Matrice;
import labo2.Vecteur;

public class TestMatrice {

	double[][] s1 = {{ 3, 5, -3, 15 },
			{ 7, 10, 1, 2 },
			{ -3, 2, -5, 6 }};			
	Matrice l1 = new Matrice(s1);	
	
	
	@Before
	public void setup(){
		
				
		l1 = new Matrice(s1);	
		
	}
	@Test
	public void testToString() {

		double[][] s1l1 = {{ 3, 5, -3, 15 },
				{ 7, 10, 1, 2 },
				{ -3, 2, -5, 6 }};		

		Matrice l1 = new Matrice(s1l1);	
		
		String resAttendu = "[3.0 5.0 -3.0 15.0]\n" + 
				"[7.0 10.0 1.0 2.0]\n" + 
				"[-3.0 2.0 -5.0 6.0]\n";
		
		assertTrue(l1.toString().equals(resAttendu));
		
		
		
	}
	
	@Test
	public void testEquals() {

		double[][] s2 = {{ 3, 5, -3, 15 },
				{ 7, 10, 1, 2 },
				{ -3, 2, -5, 6 }};	

		Matrice l1 = new Matrice(s1);	
		
		Matrice resAttendu = new Matrice(s2);	
		
		assertEquals(l1,resAttendu);		
		
	}
	

}
