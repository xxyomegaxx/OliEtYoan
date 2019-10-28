package videoStore;

public class ChildrensPrice extends Price {

	@Override
	public double Amount(Rental rental) {
		double thisAmount = 0;
		thisAmount += 1.5;
		if (rental.getDaysRented() > 3)
			thisAmount += (rental.getDaysRented() - 3) * 1.5;
		return thisAmount;
	}

	@Override
	public int Points() {
		// TODO Auto-generated method stub
		return 0;
	}

}
