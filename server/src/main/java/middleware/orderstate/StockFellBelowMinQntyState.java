package middleware.orderstate;

import gui.ServerGUI;
import middleware.wares.OrderProcessorFacade;
import model.Order;
import util.Messages;

public class StockFellBelowMinQntyState implements IOrderState {
	private ServerGUI serverGUI = ServerGUI.getInstance();

	public void processOrder(Order order) {
		order.getOrderedProduct().restocking();

		order.addViewers();
		order.updateViewers();

		sendMessage(order);
	}

	public void sendMessage(Order order) {
		updateMessage(order);
	}

	private void updateMessage(Order order) {

		ServerGUI serverGUI = ServerGUI.getInstance();
		String message = String.format(Messages.MSG_SERVER_BELOW_MIN);

		message += String.format(Messages.MSG_SERVER_RESTOCKING, order.getOrderedProduct().getName());
		message += String.format(Messages.MSG_SERVER_RESTOCKING_COMPLETED, order.getOrderedProduct().getName());


		// Trigger the unblock
		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();
		processor.getLatch().countDown();

		// Print to server GUI
		serverGUI.updateMessage(message);

	}
}
