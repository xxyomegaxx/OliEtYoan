package videoStore;

public class FrenchStatement extends Statement{

	FrenchStatement(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String printFooter() {
		String result;
		result = "Le montant du est " + customer.calculateTotalAmount() + "\n";
		result += "Vous avez obtenu " + customer.calculateTotalPoints() + " points de fidelité\n";
		return result;

	}


	@Override
	protected String printHeader() {
		return "Ensemble des locations de " + customer.getName() + "\n";
	}


}
