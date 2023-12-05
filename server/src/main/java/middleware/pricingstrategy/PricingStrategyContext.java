package middleware.pricingstrategy;

import model.Order;

public class PricingStrategyContext {
	private IPricingStrategy pricingStrategy;

	public void setPricingStrategy(IPricingStrategy newPricingStrategy) {
		this.pricingStrategy = newPricingStrategy;
	}

	public double determineOrderPrice(Order order) {
		return this.pricingStrategy.calculateOrderPrice(order);
	}

}