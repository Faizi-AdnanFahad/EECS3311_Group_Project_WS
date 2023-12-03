package model.pricingStrategy;

public class PricingStrategyContext {
	private IPricingStrategy pricingStrategy;

	public void setPricingStrategy(IPricingStrategy newPricingStrategy) {
		this.pricingStrategy = newPricingStrategy;
	}

	public int determineOrderPrice(int orderdQnty) {
		return this.pricingStrategy.calculateOrderPrice(orderdQnty);
	}

}