package middleware;

import model.Order;

public class OrderProcessor extends Middleware {
	OrderQueue orderQueue = null;
	static OrderProcessor instance = null;

	private OrderProcessor() {
		super("Order Processor", true);
		orderQueue = new OrderQueue();
	}
	
	public static OrderProcessor getInstance() {
		if(instance != null) return instance;
		return new OrderProcessor();
	}

	
	public void add(Order order) {
		// Add order to the OrderQueue
		this.orderQueue.enqueue(order);
	}
	
	public void process() {
		// Grab next in line for processing
		Order order = this.orderQueue.dequeue();
		
		// Send the data where it needs to go (Controller) 
	}

}
