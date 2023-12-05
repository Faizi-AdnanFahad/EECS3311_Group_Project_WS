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
		/********************* Observer pattern ************************/
		if (orderTransaction) {
			System.out.println("/*********************Observer pattern************************/");
			order.addViewers();
			order.updateViewers();
			System.out.println("/*********************Observer pattern************************/");
		}
		/*************************************************************/

		/*
		 * If after completing an order, the stock quantity for a product fells below
		 * the target min quantity, set the state to lowStock.
		 */
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

		// Trigger the unblock
//		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();
//		processor.addMessage("");
//		processor.getLatch().countDown();

	}

	public void sendMessage(Order order) {

		System.out.println("------------------------------");

		String message = String.format(Messages.MSG_ORDER_COMPLETED, order.getOrderedProduct().getName(),
				order.getOrderedQuantity(), order.getOrderPrice());
		System.out.println(message);

		// Trigger the unblock
		OrderProcessorFacade processor = OrderProcessorFacade.getInstance();

		processor.addMessage(message);

		processor.getLatch().countDown();

		serverGUI.stateMessage(message);

	}

}
