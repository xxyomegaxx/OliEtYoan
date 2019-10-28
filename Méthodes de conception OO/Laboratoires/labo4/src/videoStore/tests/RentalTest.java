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
		
		

		childMovie = new Movie("Caillou a le cancer", Movie.CHILDRENS);
		newMovie = new Movie("31 Jump Street: Ninja Academy", Movie.NEW_RELEASE);
		regularMovie = new Movie("Les oiseaux se cachent pour mourir", Movie.REGULAR);		
	}
	
	
	@Test
	public void testAmount(){
		Rental child1day = new Rental(childMovie,1);
		Rental child3days = new Rental(childMovie,3);
		Rental child5days = new Rental(childMovie,5);
		Rental new1day = new Rental(newMovie,1);
		Rental new5days = new Rental(newMovie,5);
		Rental reg1day = new Rental(regularMovie,1);
		Rental reg2days = new Rental(regularMovie,2);
		Rental reg5days = new Rental(regularMovie,5);
		
		
		assertEquals(child1day.amount(),1.5,EPSILON);
		assertEquals(child1day.amount(),child3days.amount(),EPSILON);
		assertEquals(child5days.amount(),4.5,EPSILON);
		assertEquals(reg1day.amount(),2.0,0.0001);
		assertEquals(reg1day.amount(),reg2days.amount(),EPSILON);		
		assertEquals(reg5days.amount(),6.5,EPSILON);
		assertEquals(new1day.amount(),3.0,EPSILON);
		assertEquals(new5days.amount(),15.0,EPSILON);		
		
	}
	
	@Test
	public void testPoints(){
		Rental childrens = new Rental(childMovie,1);		
		Rental newrelease = new Rental(newMovie,1);	
		Rental regular = new Rental(regularMovie,5);
		
		assertEquals(newrelease.points(),2);	
		assertEquals(childrens.points(),1);	
		assertEquals(regular.points(),1);	
	}
	
	

	
	@After
	public void teardown() {
		childMovie = null;
		regularMovie=null;
		newMovie=null;
	}
}
