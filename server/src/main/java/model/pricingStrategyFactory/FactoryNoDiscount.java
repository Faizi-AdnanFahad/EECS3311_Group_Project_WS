package model.pricingStrategyFactory;

import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategy.NoDiscountStrategy;

public class FactoryNoDiscount implements IFactoryPricingStrategy {

	public IPricingStrategy create() {
		return new NoDiscountStrategy();
	}

}
