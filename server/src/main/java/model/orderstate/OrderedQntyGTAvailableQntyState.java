package model.orderstate;

import controller.OrderController;
import model.Order;

public class OrderedQntyGTAvailableQntyState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("------------------------------");
		String message = String.format("Order for Product %s Quantity %d is pending – order exceeds available quantity",
				order.getOrderedProduct().getName(), order.getOrderedQuantity());
		System.out.println(message);

		/* Perform Restocking */
		message = String.format("Restocking Operation for Product %s initiated", order.getOrderedProduct().getName());
		System.out.println("------------------------------");
		order.getOrderedProduct().restocking();
		OrderController cont = new OrderController();
		cont.setOrderState(new OrderedQntySMEqualToAvailableQntyState());
		cont.processOrder(order);
		
		
	}
}
