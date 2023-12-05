package model.orderstate;

import gui.ServerGUI;
import model.Order;

import util.Messages;

public class OrderedQntySMEqualToAvailableQntyState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("/*********************Observer pattern************************/");
		order.addViewers();
		order.updateViewers();
		System.out.println("/*********************Observer pattern************************/");
		/*************************************************************/

		
		
		sendMessage(order);
	}

	public void sendMessage(Order order) {
		
		ServerGUI serverGUI = ServerGUI.getInstance();
		
		System.out.println("------------------------------");
		String message = String.format(Messages.MSG_ORDER_COMPLETED,
				order.getOrderedProduct().getName(), order.getOrderedQuantity(), order.getOrderPrice());
		System.out.println(message);
		System.out.println("------------------------------");
		
		// Update all viewers for Observer pattern
		/*********************Observer pattern************************/
		
		serverGUI.stateMessage(message);
		
		
	}

}
