package middleware.orderstate;

import gui.ServerGUI;
import middleware.wares.OrderProcessorFacade;
import model.Order;
import util.Messages;

public class StockFellBelowMinQntyState implements IOrderState {
	private ServerGUI serverGUI = ServerGUI.getInstance();

	public void processOrder(Order order) {
//		System.out.println("------------------------------");
//		String message = "Product quantity fell below the target min quantity - Low Stock";
//		System.out.println(message);

		// invoke the stock operation
//		message = String.format("Restocking Operation for Product %s initiated", order.getOrderedProduct().getName());
//		System.out.println(message);
//		System.out.println("------------------------------");
		order.getOrderedProduct().restocking();

		// Update all viewers for Observer pattern
		/********************* Observer pattern ************************/
		System.out.println("/*********************Observer pattern************************/");
		order.addViewers();
		order.updateViewers();
		System.out.println("/*********************Observer pattern************************/");
		/*************************************************************/

		sendMessage(order);
	}

	public void sendMessage(Order order) {
		updateMessage(order);
	}

	private void updateMessage(Order order) {

		ServerGUI serverGUI = ServerGUI.getInstance();

		System.out.println("------------------------------");
		String message = String.format(Messages.MSG_SERVER_BELOW_MIN);
		System.out.println("------------------------------");

		message += String.format(Messages.MSG_SERVER_RESTOCKING, order.getOrderedProduct().getName());
		message += String.format(Messages.MSG_SERVER_RESTOCKING_COMPLETED, order.getOrderedProduct().getName());

		System.out.println(message);

		// Trigger the unblock
		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();
		processor.addMessage(message);
		processor.getLatch().countDown();

		serverGUI.updateMessage(message);

	}
}
