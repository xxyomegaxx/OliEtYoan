/*
 * Created on 2005-03-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package videoStore;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * M. Fowler, et al., Refactoring, Improving the design of existing code,
 * Addison-Wiley, 2000. Exemple Chapitre 1
 * 
 * The class Movie is just a simple data class
 * 
 */

public class Movie {

	private java.lang.String title_;
	private Price priceCode_;
	ArrayList<PriceElement> priceList;

	private Movie(String title, Price priceCode) {
		title_ = title;
		priceCode_ = priceCode;// À retirer si on ajoute la liste
		//priceList.add(new PriceElement(priceCode,LocalDate.now()));
	}
	private Movie(String title, Price priceCode, ArrayList<PriceElement> list) {
		title_ = title;
		priceList = list;
		priceList.add(new PriceElement(priceCode,LocalDate.now()));
		//Collections.sort(priceList);
	}

	static public Movie createRegular(String title) {
		return new Movie(title, new RegularPrice());
	}

	static public Movie createChildren(String title) {
		return new Movie(title, new ChildrensPrice());
	}

	static public Movie createNewRelease(String title) {
		return new Movie(title, new NewReleasePrice());
	
	}
	
	static public Movie createUnpopularPrice(String title) {
		return new Movie(title, new UnpopularPrice());
	}
	
	static public Movie createRegular(String title,ArrayList<PriceElement> list) {
		return new Movie(title, new RegularPrice());
	}

	static public Movie createChildren(String title,ArrayList<PriceElement> list) {
		return new Movie(title, new ChildrensPrice());
	}

	static public Movie createNewRelease(String title,ArrayList<PriceElement> list) {
		return new Movie(title, new NewReleasePrice());
	
	}
	
	static public Movie createUnpopularPrice(String title,ArrayList<PriceElement> list) {
		return new Movie(title, new UnpopularPrice());
	}
	
	
	public java.lang.String getTitle() {
		return title_;
	}

	public Price getPriceCode() {
		return priceCode_;
	}

	public double amount(int daysRented)
	{			
		return priceCode_.getCharge(daysRented);
	}
	public int points()
	{
		return priceCode_.getPoints();

	}
}
