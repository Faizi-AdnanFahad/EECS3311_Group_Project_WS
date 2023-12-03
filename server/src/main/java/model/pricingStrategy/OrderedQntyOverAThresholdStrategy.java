package model.pricingStrategy;

public class OrderedQntyOverAThresholdStrategy implements PricingStrategy {

	public int calculateOrderPrice(int orderedQunatity) {
		System.out.println(this.getClass().getSimpleName());
		return 0;
	}

}
