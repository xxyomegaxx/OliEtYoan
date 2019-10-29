package videoStore;

import java.time.LocalDate;

public class PriceElement {
	Price priceCode;
	LocalDate date;
	public Price getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(Price priceCode) {
		this.priceCode = priceCode;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	PriceElement(Price p , LocalDate d)
	{
		priceCode = p;
		date = d;
	}

}
