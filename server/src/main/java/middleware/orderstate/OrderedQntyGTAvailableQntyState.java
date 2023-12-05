package middleware.orderstate;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.OrderController;
import gui.ServerGUI;
import util.Messages;

import model.Order;

public class OrderedQntyGTAvailableQntyState implements IOrderState {

	public void processOrder(Order order) {

		System.out.println("------------------------------");
		order.getOrderedProduct().restocking();

		OrderController cont = new OrderController();
		/* Calculate the price for the order */
		cont.calculateOrderPrice(order);

		cont.completeProcessOrdering(order);
		cont.setOrderState(new OrderedQntySMEqualToAvailableQntyState());
		cont.processOrder(order);

		sendMessage(order);

	}

	public void sendMessage(Order order) {
		// TODO Auto-generated method stub
		
		ServerGUI serverGUI = ServerGUI.getInstance();
		System.out.println("------------------------------");
		String message = String.format(Messages.MSG_ORDER_PENDING,
				order.getOrderedProduct().getName(), order.getOrderedQuantity());
		System.out.println(message);
		
		/* Perform Restocking */
		message +=  String.format(Messages.MSG_ORDER_COMPLETED, order.getOrderedProduct().getName(), order.getOrderedQuantity(), order.getOrderPrice());
		
		message +=  String.format(Messages.MSG_SERVER_RESTOCKING, order.getOrderedProduct().getName());
		
		message +=  String.format(Messages.MSG_SERVER_RESTOCKING_COMPLETED, order.getOrderedProduct().getName());

		serverGUI.stateMessage(message);
		
	}


}
