package videoStore.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import videoStore.Customer;
import videoStore.Movie;
import videoStore.Rental;

public class CustomerTest {

	private Customer grandmaman;
	private Movie movies[];

	@Before
	public void setup() {
		movies = new Movie[5];

		movies[0] = Movie.createChildren("Caillou a le cancer");
		movies[1] = Movie.createNewRelease("Fast and furious 38: Diesel Cars vs Google Cars");
		movies[2] = Movie.createNewRelease("31 Jump Street: Ninja Academy");
		movies[3] = Movie.createRegular("Les oiseaux se cachent pour mourir");
		movies[4] = Movie.createRegular("Les gars chauds se cachent pour vomir");

		grandmaman = new Customer("Grand-moman");

		grandmaman.addRentals(new Rental(movies[0], 4));
		grandmaman.addRentals(new Rental(movies[1], 1));
		grandmaman.addRentals(new Rental(movies[2], 2));
		grandmaman.addRentals(new Rental(movies[3], 7));
		grandmaman.addRentals(new Rental(movies[4], 1));
		
		/*Exemple de création de location avec une date passée*/
		String str = "2018-12-12";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(str, formatter);
		Rental testDate = new Rental (movies[0],date,2);
		
	}

	

	@Test
	public void testTotalAmount() {

		assertEquals(grandmaman.totalAmount(), 23.5, 0.0001);
	}

	@Test
	public void testTotalPoints() {

		assertEquals(grandmaman.totalPoints(), 7);
	}

	@After
	public void teardown() {
		grandmaman = null;
		movies = null;
	}

}
