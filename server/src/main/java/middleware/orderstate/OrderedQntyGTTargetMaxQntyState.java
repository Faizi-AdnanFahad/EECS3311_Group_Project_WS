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

		System.out.println("------------------------------");
		String message = Messages.MSG_SERVER_EXCEED_MAX;
		System.out.println(message);
		System.out.println("------------------------------");
		
		// Trigger the unblock
		// sends the message to the client
		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();
		processor.addMessage(message);
		processor.getLatch().countDown();

		// sends the message to the server
		serverGUI.stateMessage(message);

	}

}
