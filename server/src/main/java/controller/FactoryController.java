package controller;

import middleware.orderstate.IOrderState;
import middleware.orderstatefactory.OrderStateFactoryRepo;
import middleware.pricingstrategy.IPricingStrategy;
import middleware.pricingstrategyfactory.PricingStrategyFactoryRepo;
import util.Utilities;

public class FactoryController {

	/*
	 * Setting up state Factory
	 */
	public OrderStateFactoryRepo setupStateFactory() {
		return Utilities.setUpOrderFactory();
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
		return Utilities.setUpPricingFactory();
	}

	/*
	 * Creating a pricing strategy using the pricing strategy factory
	 */
	public IPricingStrategy createPricingStrategy(PricingStrategyFactoryRepo repo, int index) {
		return repo.getFactoryItem(index).create();
	}
}
