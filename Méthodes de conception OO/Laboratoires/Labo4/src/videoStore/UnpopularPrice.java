package videoStore;

public class UnpopularPrice extends Price{
	
	public UnpopularPrice() {
		super();
	}
	
	public double getCharge(int daysRented) {
		return 2;
	}
	
	public int getPoints() {
	    return 3;
	}
	
}
