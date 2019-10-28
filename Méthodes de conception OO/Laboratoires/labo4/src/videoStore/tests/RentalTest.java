package videoStore.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import videoStore.Movie;
import videoStore.Rental;

public class RentalTest {
	
	private final double EPSILON = 0.01;
	private Movie childMovie;
	private Movie regularMovie;
	private Movie newMovie;
	
	@Before
	public void setup() {
		
		childMovie = new Movie("Caillou a le cancer", Movie.childrens);
		
//		childMovie = new Movie("Caillou a le cancer", Movie.CHILDRENS);
//		newMovie = new Movie("31 Jump Street: Ninja Academy", Movie.NEW_RELEASE);
//		regularMovie = new Movie("Les oiseaux se cachent pour mourir", Movie.REGULAR);		
	}
	
	
	@Test
	public void testAmount(){
		
		

		
		Rental child1day = new Rental(childMovie,1);
//		Rental child3days = new Rental(childMovie,3);
//		Rental child5days = new Rental(childMovie,5);
//		Rental new1day = new Rental(newMovie,1);
//		Rental new5days = new Rental(newMovie,5);
//		Rental reg1day = new Rental(regularMovie,1);
//		Rental reg2days = new Rental(regularMovie,2);
//		Rental reg5days = new Rental(regularMovie,5);
//		
//		
		assertEquals(childMovie.getPriceCode().Amount(child1day),1.5,EPSILON);
//		assertEquals(childMovie.amount(child1day),childMovie.amount(child3days),EPSILON);
//		assertEquals(childMovie.amount(child5days),4.5,EPSILON);
//		assertEquals(regularMovie.amount(reg1day),2.0,0.0001);
//		assertEquals(regularMovie.amount(reg1day),regularMovie.amount(reg2days),EPSILON);		
//		assertEquals(regularMovie.amount(reg5days),6.5,EPSILON);
//		assertEquals(newMovie.amount(new1day),3.0,EPSILON);
//		assertEquals(newMovie.amount(new5days),15.0,EPSILON);		
		
	}
	
	@Test
	public void testPoints(){
		
//		Rental childrens = new Rental(childMovie,1);		
//		Rental newrelease = new Rental(newMovie,1);	
//		Rental regular = new Rental(regularMovie,5);
		
//		assertEquals(newMovie.points(),2);	
//		assertEquals(childMovie.points(),1);	
//		assertEquals(regularMovie.points(),1);	Í
	}
	
	

	
	@After
	public void teardown() {
		childMovie = null;
		regularMovie=null;
		newMovie=null;
	}
}
