package model.orderstate;

import model.Order;

public interface IOrderState {

	void processOrder(Order order);
	
	
	public void sendMessage(Order order);
}



