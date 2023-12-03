package controller;

import model.Order;
import model.orderstate.IOrderState;
import model.pricingStrategy.IPricingStrategy;

public class OrderController {
	private IOrderState orderState;
	private IPricingStrategy pricingStrategy;
	
	public IOrderState getOrderState() {
		return this.orderState;
	}

	public void setOrderState(IOrderState newState) {
		this.orderState = newState;
	}

	public void processOrder(Order order) {
		this.orderState.processOrder(order);
	}

	public int compareOrderedQntyAgainstProduct(Order order) {
		return order.compareOrderedQntyAgainstProduct();
	}
	
	public void calculateOrderPrice(Order order) {
		this.pricingStrategy.calculateOrderPrice(order.getOrderedQuantity());
	}
	
	public int determineDiscountStrategy(Order order) {
		return order.determineDiscountStrategy();
	}

}
