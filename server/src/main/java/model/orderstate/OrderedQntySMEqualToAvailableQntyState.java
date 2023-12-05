package model.orderstate;
import gui.ServerGUI;
import java.time.LocalDateTime;
import model.Order;
import util.Messages;

public class OrderedQntySMEqualToAvailableQntyState implements IOrderState {
   private ServerGUI serverGUI = ServerGUI.getInstance();

	public void processOrder(Order order) {

	    
		System.out.println("------------------------------");
		String message = String.format("Order is finalized for Product %s and Quantity %d with total price %.2f.",
				order.getOrderedProduct().getName(), order.getOrderedQuantity(), order.getOrderPrice());
		System.out.println(message);
		System.out.println("------------------------------");
	    
		

		serverGUI.populateLastOrder(order.getOrderedProduct().getName(), order.getOrderedQuantity());
	
	
		System.out.println("/*********************Observer pattern************************/");
		order.addViewers();
		order.updateViewers();
		System.out.println("/*********************Observer pattern************************/");
		/*************************************************************/	
		
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
