package middleware;

import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderQueue {
	private List<Order> orderQueue = null;
	private int size;

	public OrderQueue() {
		orderQueue = new ArrayList<Order>();
	}

	public void enqueue(Order o) { 
		orderQueue.add(o);
		++this.size; 
	}

	public void dequeue() { 
		this.orderQueue.remove(0);
		--this.size;
	}
}
