package videoStore.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import videoStore.Customer;
import videoStore.Movie;
import videoStore.Rental;

public class RentalTest {
	public static final double EPSILON = 0.1;

	Rental r1;
	Rental r2;
	Rental r3;

	@Before
	public void setup() {
		Movie movies[] = new Movie[5];

		movies[0] = new Movie("Caillou a le cancer", Movie.CHILDRENS);
		movies[1] = new Movie("Fast and furious 38: Diesel Cars vs Google Cars", Movie.NEW_RELEASE);
		movies[2] = new Movie("Les oiseaux se cachent pour mourir", Movie.REGULAR);
		
		r1= new Rental(movies[0], 4);
		r2= new Rental(movies[1], 1);
		r3= new Rental(movies[2], 2);
	}

	@Test
	public void calculateAmountTest()
	{
		double result = 3;
		assert(r1.calculateAmount()-result<EPSILON);	
		
		double result2 = 3;
		assert(r2.calculateAmount()-result2<EPSILON);

		double result3 = 9.5;
		assert(r3.calculateAmount()-result3<EPSILON);	
	}	
	
	@Test
	public void calculatePointsTest()
	{
		int result = 1;
		assertEquals(r1.calculatePoints(),result);		
		int result2 = 2;
		assertEquals(r2.calculatePoints(),result2);
	}



}