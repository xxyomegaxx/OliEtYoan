package videoStore;

public abstract class Price {
	
    public Price() {
        super();
    }

    public abstract double getCharge(int daysRented);


    public int getPoints() {
        return 1;
    }
	

}
