package videoStore;

public abstract class Statement {
	
	Customer customer;
	
	Statement(Customer customer)
	{
		this.customer = customer;
	}
	
	public String print() {

		String result = printHeader();		
		result += printRentalLines();
		result += printFooter();
		return result;
	}

	abstract protected String printFooter();


	abstract protected String printRentalLines();

	abstract protected String printHeader();

}
