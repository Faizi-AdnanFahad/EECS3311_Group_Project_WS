package middleware.orderstate;

import gui.ServerGUI;
import model.Order;
import util.Messages;

public class StockFellBelowMinQntyState implements IOrderState {
	private ServerGUI serverGUI = ServerGUI.getInstance();

	public void processOrder(Order order) {

		System.out.println("------------------------------");
		String message = Messages.MSG_SERVER_BELOW_MIN;
		System.out.println(message);
		System.out.println("------------------------------");

		// invoke the restock operation
		message = String.format("Restocking Operation for Product %s initiated", order.getOrderedProduct().getName());
		System.out.println(message);
		System.out.println("------------------------------");
		order.getOrderedProduct().restocking();

		// Update all viewers for Observer pattern
		/********************* Observer pattern ************************/
		System.out.println("/*********************Observer pattern************************/");
		order.addViewers();
		order.updateViewers();
		System.out.println("/*********************Observer pattern************************/");
		/*************************************************************/

		serverGUI.populateLastOrder(order.getOrderedProduct().getName(), order.getOrderedQuantity());
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
