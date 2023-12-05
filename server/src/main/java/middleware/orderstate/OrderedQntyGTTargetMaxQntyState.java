package middleware.orderstate;

import model.Order;
import util.Messages;
import gui.ServerGUI;
import middleware.wares.OrderProcessorFacade;

public class OrderedQntyGTTargetMaxQntyState implements IOrderState {

	public void processOrder(Order order) {

		sendMessage(order);
	}

	public void sendMessage(Order order) {

		ServerGUI serverGUI = ServerGUI.getInstance();

		String message = Messages.MSG_SERVER_EXCEED_MAX;

		// Trigger the unblock
		// sends the message to the client
		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();
		processor.addMessage(message);
		processor.getLatch().countDown();

		// sends the message to the server
		serverGUI.stateMessage(message);

	}

}
