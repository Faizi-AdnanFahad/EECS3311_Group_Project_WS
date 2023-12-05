package middleware.orderstate;

import controller.OrderController;
import gui.ServerGUI;
import middleware.wares.OrderProcessorFacade;
import util.Messages;

import model.Order;

public class OrderedQntyGTAvailableQntyState implements IOrderState {

	public void processOrder(Order order) {
		/* Perform Restocking */
		order.getOrderedProduct().restocking();

		OrderController cont = new OrderController();
		/* Calculate the price for the order */
		cont.calculateOrderPrice(order);

		cont.completeProcessOrdering(order);
		
		sendMessage(order);
	}

	public void sendMessage(Order order) {		
		ServerGUI serverGUI = ServerGUI.getInstance();
		System.out.println("------------------------------");
		String message = String.format(Messages.MSG_ORDER_PENDING,
				order.getOrderedProduct().getName(), order.getOrderedQuantity());
		System.out.println(message);
		

		
		message +=  String.format(Messages.MSG_SERVER_RESTOCKING, order.getOrderedProduct().getName());
		
		message +=  String.format(Messages.MSG_SERVER_RESTOCKING_COMPLETED, order.getOrderedProduct().getName());

		message +=  String.format(Messages.MSG_ORDER_COMPLETED, order.getOrderedProduct().getName(), order.getOrderedQuantity(), order.getOrderPrice());

		
		// Trigger the unblock
		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();
		String msg = String.format(Messages.MSG_ORDER_COMPLETED, order.getOrderedProduct().getName(), order.getOrderedQuantity(), order.getOrderPrice());
		processor.addMessage(msg);
		processor.getLatch().countDown();
		
		serverGUI.stateMessage(message);
		
	}


}
