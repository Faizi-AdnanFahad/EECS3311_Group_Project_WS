package model.pricingStrategy;

public class PricingStrategyContext {
	private PricingStrategy pricingStrategy;

	public void setPricingStrategy(PricingStrategy newPricingStrategy) {
		this.pricingStrategy = newPricingStrategy;
	}

	public int determineOrderPrice(int orderdQnty) {
		return this.pricingStrategy.calculateOrderPrice(orderdQnty);
	}

}