package model.orderstate;

import model.Order;

public class OrderedQntySMEqualToAvailableQntyState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("------------------------------");
		String message = String.format("“Order is finalized for Product %s and Quantity %d with total price Z",
				order.getOrderedProduct().getName(), order.getOrderedQuantity());
		System.out.println(message);
		System.out.println("------------------------------");
	}

}
