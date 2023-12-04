package model.orderstate;

import model.Order;

public class StockFellBelowMinQntyState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("------------------------------");
		String message = "Product quantity fell below the target min quantity - Low Stock";
		System.out.println(message);

		// invoke the stock operation
		message = String.format("Restocking Operation for Product %s initiated", order.getOrderedProduct().getName());
		System.out.println(message);
		System.out.println("------------------------------");
		order.getOrderedProduct().restocking();
	}
}