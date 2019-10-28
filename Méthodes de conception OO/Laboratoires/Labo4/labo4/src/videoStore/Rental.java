/*
 * Created on 2005-03-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package videoStore;

import java.time.LocalDate;

public class Rental {
	private int daysRented;
	private Movie movie;

	private LocalDate date;

	public Rental(Movie movie, LocalDate initialDate, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
		date = initialDate;
	}

	public Rental(Movie movie, int daysRented) {
		this(movie, LocalDate.now(), daysRented);
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public double amount() {
		double thisAmount = 0;

		// determines amount for each line
		switch (movie.getPriceCode()) {
		case Movie.REGULAR:
			thisAmount += 2;
			if (getDaysRented() > 2)
				thisAmount += (getDaysRented() - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			thisAmount += getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			thisAmount += 1.5;
			if (getDaysRented() > 3)
				thisAmount += (getDaysRented() - 3) * 1.5;
			break;
		}
		return thisAmount;
	}

	public int points() {

		if (getMovie().getPriceCode() == Movie.NEW_RELEASE)
			return 2;
		return 1;
	}
}
