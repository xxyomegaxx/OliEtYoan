
package videoStore;

import java.util.Vector;

/**
 * M. Fowler, et al., Refactoring, Improving the design of existing code,
 * Addison-Wiley, 2000. Exemple Chapitre 1
 * 
 * The Customer class represents the customer of the store.
 * 
 */

public class Customer {
	private String name;
	private Vector<Rental> rentals;
	private Statement statement;

	public Customer(String name) {
		this.name = name;
		rentals = new Vector<Rental>();
	}

	public void addRentals(Rental arg) {
		getRentals().addElement(arg);
	}

	public String getName() {
		return name;
	}

	public String englishStatement() {
		return new EnglishStatement(this).print();
	}

	
	public String frenchStatement() {

		return new FrenchStatement(this).print();
		
	}

	public double calculateTotalAmount() {
		double totalAmount=0;
		for (Rental each : getRentals()) {
			totalAmount += each.calculateAmount();
			
		}
		return totalAmount;
	}
	public int calculateTotalPoints() {
		int frequentRenterPoints = 0;
		for (Rental each : getRentals()) {

			frequentRenterPoints += each.calculatePoints();

		}
		return frequentRenterPoints;
	}

	public Vector<Rental> getRentals() {
		return rentals;
	}

	
}
