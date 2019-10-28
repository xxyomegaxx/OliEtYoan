package videoStore;

public class RegularPrice extends Price{

	@Override
	public double Amount(Rental rental) {
		double thisAmount = 0;
		
		thisAmount += 2;
		if (rental.getDaysRented() > 2)
			thisAmount += (rental.getDaysRented() - 2) * 1.5;
		
		return thisAmount;
	}

	@Override
	public int Points() {
		// TODO Auto-generated method stub
		return 0;
	}

}
