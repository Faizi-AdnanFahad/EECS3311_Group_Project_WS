package middleware.orderstate;

import controller.OrderController;
import gui.ServerGUI;
import middleware.wares.OrderProcessorFacade;
import model.Order;
import model.Product;
import util.Constants;
import util.Messages;

public class OrderedQntySMEqualToAvailableQntyState implements IOrderState {
	private ServerGUI serverGUI = ServerGUI.getInstance();

	public void processOrder(Order order) {

		/* Complete the order */
		boolean orderTransaction = order.performOrder();

		// Update all viewers for Observer pattern
		if (orderTransaction) {
			order.addViewers();
			order.updateViewers();
		}
		
		checkIfBelowStockTargetMinQty(order);
	}

	/*
	 * If after completing an order, the stock quantity for a product fells below
	 * the target min quantity, set the state to lowStock.
	 */
	public void checkIfBelowStockTargetMinQty(Order order) {

		Product orderedProduct = order.getOrderedProduct();
		if (orderedProduct.getStockQuantity() < orderedProduct.getTargetMinStockQuantity()) {
			sendMessage(order);

			// Trigger the unblock
			OrderProcessorFacade processor = OrderProcessorFacade.getInstance();
			processor.addMessage(String.format(Messages.MSG_ORDER_COMPLETED, order.getOrderedProduct().getName(),
					order.getOrderedQuantity(), order.getOrderPrice()));
			processor.getLatch().countDown();

			OrderController cont = new OrderController();
			cont.completeProcessOrdering(order);

		} else { 
			sendMessage(order); 
		}
	}

	public void sendMessage(Order order) {

		String message = String.format(Messages.MSG_ORDER_COMPLETED, order.getOrderedProduct().getName(),
				order.getOrderedQuantity(), order.getOrderPrice());

		// Trigger the unblock
		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();

		processor.addMessage(message);
		processor.getLatch().countDown();
		serverGUI.stateMessage(message);

	}

}
