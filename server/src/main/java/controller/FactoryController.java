package controller;

import middleware.orderstate.IOrderState;
import middleware.orderstatefactory.OrderStateFactoryRepo;
import middleware.orderstatefactory.Utilities;
import middleware.pricingstrategy.IPricingStrategy;
import middleware.pricingstrategyfactory.PricingStrategyFactoryRepo;

public class FactoryController {

	/*
	 * Setting up state Factory
	 */
	public OrderStateFactoryRepo setupStateFactory() {
		return Utilities.setUpFactory();
	}

	/*
	 * Creating a state using the factory
	 */
	public IOrderState createState(OrderStateFactoryRepo repo, int index) {
		return repo.getFactoryItem(index).create();
	}

	/*
	 * Setting up Pricing Factory
	 */
	public PricingStrategyFactoryRepo setUpPricingFactory() {
		return middleware.pricingstrategyfactory.Utilities.setUpFactory();
	}

	/*
	 * Creating a pricing strategy using the pricing strategy factory
	 */
	public IPricingStrategy createPricingStrategy(PricingStrategyFactoryRepo repo, int index) {
		return repo.getFactoryItem(index).create();
	}
}
