package model.pricingStrategy;

public class OrderedQntyOverAThresholdStrategy implements IPricingStrategy {

	public int calculateOrderPrice(int orderedQunatity) {
		System.out.println(this.getClass().getSimpleName());
		return 0;
	}

}
