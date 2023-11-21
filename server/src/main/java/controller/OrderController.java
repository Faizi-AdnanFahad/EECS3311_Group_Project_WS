package controller;

import model.Order;
import model.Product;
import model.orderstate.IOrderState;
import view.BarChartView;
import view.ConcretePublisher;
import view.IView;
import view.ReportView;

public class OrderController {
	private IOrderState orderState;

	public IOrderState getOrderState() {
		return this.orderState;
	}

	public void setOrderState(IOrderState newState) {
		this.orderState = newState;
	}

	public void processOrder(Order order) {
		this.orderState.processOrder(order);
	}
	
	public int compareOrderedQntyAgainstProduct(Product product, int orderedQnty) {
		int targetMaxQnty = product.getTargetMaxStockQuantity();
		int targetMinQnty = product.getTargetMinStockQuantity();
		int stockQnty = product.getStockQuantity();
		
		if (orderedQnty > targetMaxQnty) {
			return 1;
		}
		else if (orderedQnty < targetMinQnty) {
			return 2;
		}
		else if (orderedQnty > stockQnty) {
			return 3;
		}
		else { // ordered quantity must be less than stock quantity and does not exceed above targetMax quantity and below target minimum quantity.
			return 4;
		}
	}

	/*
	 * This method, updates all viewers using the observer pattern once an order is
	 * complete.
	 */
	public void orderCompleted() {
		Product p = new Product();
		int quantity = 2;
		Order order = new Order(p, quantity);

		ConcretePublisher concreteSubject = new ConcretePublisher();
		// add viewers as observers
		IView barChartView = new BarChartView(concreteSubject);
		IView reportView = new ReportView(concreteSubject);

		order.updateView(concreteSubject);

	}

}
