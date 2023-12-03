package model.pricingStrategyFactory;

import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategy.OrderedTotalOverAThresholdStrategy;

public class FactoryOrderedTotalOverAThreshold implements IFactoryPricingStrategy {

	public IPricingStrategy create() {
		return new OrderedTotalOverAThresholdStrategy();
	}

}