package middleware.pricingstrategyfactory;

import middleware.pricingstrategy.IPricingStrategy;
import middleware.pricingstrategy.NoDiscountStrategy;

public class FactoryNoDiscount implements IFactoryPricingStrategy {

	public IPricingStrategy create() {
		return new NoDiscountStrategy();
	}

}
