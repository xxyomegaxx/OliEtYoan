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

		
		Matrice resAttendu = new Matrice(s2);	
		
		assertEquals(l1,resAttendu);		
		
	}
	
	@Test
	public void testGauss()
	{
		double[][] s2 = {{ 1, 0, 0, 4.15625 },
				{ 0, 1, 0, -2.25 },
				{ 0, 0, 1, -4.59375 }};	

		
		Matrice resAttendu = new Matrice(s2);
		l1.Gauss();
		assertEquals(l1,resAttendu);
	}
	
	@Test
	public void testSousMatrice()
	{
		
		int h = 2;
		int l = 2;
		Matrice test = l1.sousMatrice(h,l);		
		double[][] s2 = {{ 3, 5},
				{ 7, 10}};	

		Matrice resAttendu = new Matrice(s2);
		assertEquals(test,resAttendu);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSousMatriceHException()
	{
		
		int h = 7;
		int l = 2;
		l1.sousMatrice(h,l);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSousMatriceLException()
	{
		int h = 3;
		int l = 5;
		l1.sousMatrice(h,l);

	}
	
	@Test
	public void testMatriceNul()
	{
		Matrice m = Matrice.creerMatriceNul(4, 4);
		double[][] s2 = {{ 0, 0, 0, 0 },{ 0, 0, 0, 0 },{ 0, 0, 0, 0 },{ 0, 0, 0, 0 }};	
		
		Matrice resAttendu = new Matrice(s2);	
		
		assertEquals(m,resAttendu);		
		
	}
	
	@Test
	public void testMatriceIdentite()
	{
		Matrice m = Matrice.creerMatriceIdentite(4);
		double[][] s2 = {{ 1, 0, 0, 0 },{ 0, 1, 0, 0 },{ 0, 0, 1, 0 },{ 0, 0, 0, 1 }};	
		
		Matrice resAttendu = new Matrice(s2);	
		
		assertEquals(m,resAttendu);		
		
	}

	@Test
	public void testGaussIdentite()
	{
		
		l1.Gauss();
		Matrice test = l1.sousMatrice(3,3);
		Matrice resAttendu = Matrice.creerMatriceIdentite(3);
		
		assertEquals(test,resAttendu);		
		
	}
	
	

}
