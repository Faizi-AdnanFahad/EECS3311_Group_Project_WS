package middleware.orderstate;

import model.Order;

public interface IOrderState {

	void processOrder(Order order);
}



