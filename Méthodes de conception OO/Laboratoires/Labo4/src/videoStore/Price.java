package videoStore;

public abstract class Price {
	
	protected Rental rental;
	
	public Price() {
		
	}
	
	public abstract double Amount(Rental rental);
	
	public int Points() {
		return 1;
	};
}
