package model.pricingStrategyFactory;

import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategy.OrderedQntyOverAThresholdStrategy;

public class FactoryOrderedQntyOverAThreshold implements IFactoryPricingStrategy {

	public IPricingStrategy create() {
		return new OrderedQntyOverAThresholdStrategy();
	}

}
