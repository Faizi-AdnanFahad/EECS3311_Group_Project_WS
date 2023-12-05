package model.orderstate;

import controller.OrderController;
import gui.ServerGUI;
import util.Messages;
import model.Order;

public class OrderedQntyLT_TargetMinState implements IOrderState {

	public void processOrder(Order order) {
		
		System.out.println("------------------------------");
		String message = Messages.MSG_SERVER_BELOW_MIN;
		System.out.println(message);
		System.out.println("------------------------------");
		
		order.getOrderedProduct().restocking();
		OrderController cont = new OrderController();
		cont.setOrderState(new OrderedQntySMEqualToAvailableQntyState());
		cont.processOrder(order);
		
		
	
	}

	public void sendMessage(Order order) {
		
		

		
	}

}
