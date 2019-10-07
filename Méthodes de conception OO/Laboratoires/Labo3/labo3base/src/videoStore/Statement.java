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


	protected String printRentalLines()
	{
		String result = "";
		for (Rental each : customer.getRentals()) {
					
					// show figures for this rental
					result += "\t" + each.getMovie().getTitle() + "\t" + each.calculateAmount() + "\n";
				}
		return result;		
	}
	

	abstract protected String printHeader();

}
