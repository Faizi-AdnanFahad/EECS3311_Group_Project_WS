package middleware.pricingstrategyfactory;

import middleware.pricingstrategy.IPricingStrategy;
import middleware.pricingstrategy.OrderedQntyOverAThresholdStrategy;

public class FactoryOrderedQntyOverAThreshold implements IFactoryPricingStrategy {

	public IPricingStrategy create() {
		return new OrderedQntyOverAThresholdStrategy();
	}

}
