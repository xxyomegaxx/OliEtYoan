/*
 * Created on 2005-03-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package videoStore;

/**
 * M. Fowler, et al., Refactoring, Improving the design of existing code,
 * Addison-Wiley, 2000. Exemple Chapitre 1
 * 
 * The class Movie is just a simple data class
 */

public class Movie {
//	public static final int REGULAR = 0;
//	public static final int NEW_RELEASE = 1;
//	public static final int CHILDRENS = 2;
	
	public static ChildrensPrice childrens;
	public static NewReleasePrice new_release;
	public static RegularPrice regular;

	private java.lang.String title_;
	private Price priceCode_;

	public Movie(String title, Price priceCode) {
		title_ = title;
		priceCode_ = priceCode;
	}
	
	public void createChildrensMovie(String title, ChildrensPrice child) {
		title_ = title;
		childrens = child;
	}
	

	public java.lang.String getTitle() {
		return title_;
	}

	public Price getPriceCode() {
		return priceCode_;
	}

	public void setPriceCode(Price newCode) {
		priceCode_ = newCode;
	}
}
