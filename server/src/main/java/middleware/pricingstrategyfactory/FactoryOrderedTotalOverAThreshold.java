package middleware.pricingstrategyfactory;

import middleware.pricingstrategy.IPricingStrategy;
import middleware.pricingstrategy.OrderedTotalOverAThresholdStrategy;

public class FactoryOrderedTotalOverAThreshold implements IFactoryPricingStrategy {

	public IPricingStrategy create() {
		return new OrderedTotalOverAThresholdStrategy();
	}

}