package model.orderstate;

import model.Order;

public class OrderedQntySMEqualToAvailableQntyState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("------------------------------");
		String message = String.format("Order is finalized for Product %s and Quantity %d with total price Z",
				order.getOrderedProduct().getName(), order.getOrderedQuantity());
		System.out.println(message);
		System.out.println("------------------------------");
		
		// Update all viewers for Observer pattern
		/*********************Observer pattern************************/
		System.out.println("/*********************Observer pattern************************/");
		order.addViewers();
		order.updateViewers();
		System.out.println("/*********************Observer pattern************************/");
		/*************************************************************/

	}

}
