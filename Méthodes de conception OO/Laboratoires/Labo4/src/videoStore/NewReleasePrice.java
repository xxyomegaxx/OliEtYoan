package videoStore;

public class NewReleasePrice extends Price {
	
    public NewReleasePrice() {
        super();
    }

    public double getCharge(int daysRented) {
        return daysRented * 3;
    }


    public int getPoints() {
        return 2;
    }
}


