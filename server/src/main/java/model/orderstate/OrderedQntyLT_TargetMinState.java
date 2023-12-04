package model.orderstate;

import controller.OrderController;
import model.Order;

public class OrderedQntyLT_TargetMinState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("------------------------------");
		String message = "Product quantity is below the target min quantity";
		System.out.println(message);

		// invoke the stock operation
		message = String.format("Restocking Operation for Product %s initiated", order.getOrderedProduct().getName());
		System.out.println(message);
		System.out.println("------------------------------");
		order.getOrderedProduct().restocking();
		OrderController cont = new OrderController();
		cont.setOrderState(new OrderedQntySMEqualToAvailableQntyState());
		cont.processOrder(order);
		
		

	}

}
