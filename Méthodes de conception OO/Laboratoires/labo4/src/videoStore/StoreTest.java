package videoStore;

public class StoreTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Movie movies[] = new Movie[5];
		
		
		movies[0] = Movie.createChildren("Caillou a le cancer");
		movies[1] = Movie.createNewRelease("Fast and furious 38: Diesel Cars vs Google Cars");
		movies[2] = Movie.createNewRelease("31 Jump Street: Ninja Academy");
		movies[3] = Movie.createRegular("Les oiseaux se cachent pour mourir");
		movies[4] = Movie.createRegular("Les gars chauds se cachent pour vomir");
		
		
		Customer grandmaman = new Customer("Grand-moman");
		
		grandmaman.addRentals(new Rental(movies[0],4));
		grandmaman.addRentals(new Rental(movies[1],1));
		grandmaman.addRentals(new Rental(movies[2],2));
		grandmaman.addRentals(new Rental(movies[3],7));
		grandmaman.addRentals(new Rental(movies[4],1));
		
		System.out.println(grandmaman.englishStatement());
		System.out.println(grandmaman.frenchStatement());
		
		
		

	}

}
