package middleware.pricingstrategyfactory;

import middleware.pricingstrategy.IPricingStrategy;

public interface IFactoryPricingStrategy {
	public IPricingStrategy create();
}
