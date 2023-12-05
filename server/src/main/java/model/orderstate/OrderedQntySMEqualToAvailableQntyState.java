package model.orderstate;

import controller.OrderController;
import model.Order;
import model.Product;

public class OrderedQntySMEqualToAvailableQntyState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("------------------------------");
		String message = String.format("Order is finalized for Product %s and Quantity %d with total price %.2f.",
				order.getOrderedProduct().getName(), order.getOrderedQuantity(), order.getOrderPrice());
		System.out.println(message);
		System.out.println("------------------------------");

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
//		System.out.println("orderedProduct.getStockQuantity()" + orderedProduct.getStockQuantity());
//		System.out.println("orderedProduct.getTargetMinStockQuantity()" + orderedProduct.getTargetMinStockQuantity());
		if (orderedProduct.getStockQuantity() < orderedProduct.getTargetMinStockQuantity()) {
//			System.out.println("Product state set to Low Stock");
//			OrderController cont = new OrderController();
//			/* change order state */
//			cont.setOrderState(new StockFellBelowMinQntyState());
//			cont.processOrder(order);
			OrderController cont = new OrderController();
			cont.completeProcessOrdering(order);
		}
	}

}
