package middleware;

import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderQueue {
	private List<Order> orderQueue = null;
	private int size;
	

	OrderQueue() {
		orderQueue = new ArrayList<Order>();
		size = 0;
	}

	public void enqueue(Order o) { 
		orderQueue.add(o);
		++this.size; 
	}

	public Order dequeue() { 
		if(size > 0) {
			Order order = this.orderQueue.remove(0);
			--this.size;
			return order;
		}
		
		return null;
	}
	
	public int getSize() { return size; }
}
