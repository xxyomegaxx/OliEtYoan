package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import labo2.Matrice;
import labo2.Vecteur;

public class TestVecteur {


	double[] s1 = { 1, 2, 3, 14 };			
	Vecteur l1 = new Vecteur(s1);	
	
	
	@Before
	public void setup(){
		
				
		l1 = new Vecteur(s1);	
		
	}
	@Test
	public void testToString() {

		double[] s1l1 = { 1, 2, 3, 14 };		

		Vecteur l1 = new Vecteur(s1l1);	
		
		String resAttendu = "[1.0 2.0 3.0 14.0]";
		
		assertTrue(l1.toString().equals(resAttendu));
		
		
		
	}
	
	@Test
	public void testEquals() {

		double[] s2 = { 1, 2, 3, 14 };	
		
		Vecteur resAttendu = new Vecteur(s2);	
		
		assertEquals(l1,resAttendu);		
		
	}

	@Test
	public void testSousVecteur()
	{
		
		int l = 2;
		Vecteur test = l1.sousVecteur(l);		
		double[] s2 = { 1, 2};	
		
		Vecteur resAttendu = new Vecteur(s2);
		assertEquals(test,resAttendu);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSousVecteurException()
	{
		
		int l = 8;
		l1.sousVecteur(l);
	}
	
	@Test
	public void testVecNul()
	{
		Vecteur v= Vecteur.creerVecteurNul(4);
		double[] s2 = { 0, 0, 0, 0 };	
		
		Vecteur resAttendu = new Vecteur(s2);	
		
		assertEquals(v,resAttendu);		
		
	}
	

}
