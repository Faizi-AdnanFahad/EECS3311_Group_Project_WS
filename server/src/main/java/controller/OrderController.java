package controller;

import model.Order;
import model.orderstate.IOrderState;
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

	public void processOrder(Order order) {
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

	/*
	 * Given an order and a price, updates price of that order with the given price.
	 */
	private void updateOrderPrice(Order order, double orderPrice) {
		order.setOrderPrice(orderPrice);
	}

	public int determineDiscountStrategy(Order order) {
		return order.determineDiscountStrategy();
	}

}
