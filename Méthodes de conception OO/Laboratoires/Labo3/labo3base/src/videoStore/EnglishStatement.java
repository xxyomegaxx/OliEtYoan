package videoStore;

public class EnglishStatement  extends Statement{

	EnglishStatement(Customer customer) {
		super(customer);
	}
	
	public String print() {

		String result = printHeader();
		
		result += printRentalLines();
		result += printFooter();
		return result;
	}
	
	@Override
	protected String printFooter() {
		String result;
		result = "Amount owed is " + customer.calculateTotalAmount() + "\n";
		result += "You earned " + customer.calculateTotalPoints() + " frequent renter points\n";
		return result;
	}
	
	
	@Override
	protected String printHeader() {
		String result = "Rental Record for " + customer.getName() + "\n";
		return result;
	}

	
	

}
