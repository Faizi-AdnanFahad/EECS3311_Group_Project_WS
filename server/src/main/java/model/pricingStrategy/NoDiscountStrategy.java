package model.pricingStrategy;

public class NoDiscountStrategy implements PricingStrategy {

	public int calculateOrderPrice(int orderedQunatity) {
		System.out.println(this.getClass().getSimpleName());
		return 0;
	}

}
