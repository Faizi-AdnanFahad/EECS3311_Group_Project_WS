package controller;

import model.orderstate.IOrderState;
import model.orderstatefactory.OrderStateFactoryRepo;
import model.orderstatefactory.Utilities;
import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategyFactory.PricingStrategyFactoryRepo;

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
		return model.pricingStrategyFactory.Utilities.setUpFactory();
	}

	/*
	 * Creating a pricing strategy using the pricing strategy factory
	 */
	public IPricingStrategy createPricingStrategy(PricingStrategyFactoryRepo repo, int index) {
		return repo.getFactoryItem(index).create();
	}
}
