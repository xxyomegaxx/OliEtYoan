package videoStore;

public class NewReleasePrice extends Price {

	@Override
	public double Amount(Rental rental) {
		double thisAmount = 0;
		thisAmount += rental.getDaysRented() * 3;
		return thisAmount;
	}

	@Override
	public int Points() {
		return 2;
	}

}
