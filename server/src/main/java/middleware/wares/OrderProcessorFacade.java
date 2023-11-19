package middleware.wares;

import middleware.Middleware;
import middleware.OrderQueue;
import model.Order;

public class OrderProcessorFacade extends Middleware {
	OrderQueue orderQueue = null;
	public static OrderProcessorFacade instance = null;

	private OrderProcessorFacade() {
		super("Order Processor", true);
		orderQueue = new OrderQueue();
		this.activate();
	}
	
	public static OrderProcessorFacade getInstance() {
		System.out.println(instance);

		if(instance != null) return instance;
		return new OrderProcessorFacade();
	}

	
	public void add(Order order) {
		// Add order to the OrderQueue
		orderQueue.enqueue(order);
	}
	
	public void process() {
		// Grab next in line for processing
		System.out.printf("Queue size is %d, state: %b\n", orderQueue.getSize(), this.isActive());
		if(orderQueue.getSize() == 0) {
			System.out.println("Queue is empty, nothing to process");
			return;
		}

		Order order = orderQueue.dequeue();

		// For testing
//		this.disable();

		// Send the data where it needs to go (Controller) 
		System.out.println("Order will go for order processing in Controller Package");
	}
	
	public OrderQueue getQueue () {
		return orderQueue;
	}

}
