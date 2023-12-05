package controller;

import model.Order;
import model.orderstate.IOrderState;
import model.orderstate.OrderedQntySMEqualToAvailableQntyState;
import model.orderstatefactory.OrderStateFactoryRepo;
import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategyFactory.PricingStrategyFactoryRepo;

public class OrderController {
	private IOrderState orderState;
	private IPricingStrategy pricingStrategy;

	public IOrderState getOrderState() {
		return this.orderState;
	}

	public void setOrderState(IOrderState newState) {
		this.orderState = newState;
	}

	public IPricingStrategy getPricingStrategy() {
		return pricingStrategy;
	}

	public void setPricingStrategy(IPricingStrategy pricingStrategy) {
		this.pricingStrategy = pricingStrategy;
	}

	private void processOrder(Order order) {
		this.orderState.processOrder(order);
	}

	public int compareOrderedQntyAgainstProduct(Order order) {
		return order.compareOrderedQntyAgainstProduct();
	}

	/*
	 * Based on the current pricing strategy, calculates the price of an order. This
	 * may include normal pricing or discounted pricing.
	 */
	public void calculateOrderPrice(Order order) {
		// Find out which pricing strategy is relevant based on the order - used to
		// create the relevant strategy using the factory
		int pricintStrategyNum = this.determineDiscountStrategy(order);

		// setup pricing strategy factory
		FactoryController fc = new FactoryController();
		PricingStrategyFactoryRepo repo = fc.setUpPricingFactory();
		IPricingStrategy pricingStrategy = fc.createPricingStrategy(repo, pricintStrategyNum);

		// calculate and set the correct price for the order
		this.setPricingStrategy(pricingStrategy);

		double orderPrice = this.pricingStrategy.calculateOrderPrice(order);
		this.updateOrderPrice(order, orderPrice);
	}

	private IOrderState determineOrderState(Order order) {
		/*
		 * compare the ordered quantity against the available and target max and return
		 * the correct index - used to create the correct state factory
		 */
		int stateNum = this.compareOrderedQntyAgainstProduct(order);

		// Setup the factory
		FactoryController sfc = new FactoryController();
		OrderStateFactoryRepo repo = sfc.setupStateFactory();

		/*
		 * create the right state using factory method
		 */
		IOrderState orderState = sfc.createState(repo, stateNum);
		return orderState;
	}

	public void checkPricingEligiblity(Order order) {
		/*
		 * Step 2 - Determine the order state
		 */
		IOrderState orderState = determineOrderState(order);

		/*
		 * Step 3 - determine the price of an order only if the client order is eligible
		 * for order processing.
		 */
		if (orderState instanceof OrderedQntySMEqualToAvailableQntyState) {
			this.calculateOrderPrice(order);
		}
	}

	/*
	 * Given an order and a price, updates price of that order with the given price.
	 */
	private void updateOrderPrice(Order order, double orderPrice) {
		order.setOrderPrice(orderPrice);
	}

	public int determineDiscountStrategy(Order order) {
		return order.determineDiscountStrategy();
	}

	public void completeProcessOrdering(Order order) {
		
		IOrderState orderState = determineOrderState(order);
		// set the correct order state to the state the factory has created.
		this.setOrderState(orderState);

		// process the order based on the state of the order against the database.
		this.processOrder(order);
	}

}
