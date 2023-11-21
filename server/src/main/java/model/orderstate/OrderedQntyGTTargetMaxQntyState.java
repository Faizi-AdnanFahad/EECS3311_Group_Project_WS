package model.orderstate;

import model.Order;

public class OrderedQntyGTTargetMaxQntyState implements IOrderState {

	public void processOrder(Order order) {
		System.out.println("------------------------------");
		System.out.println("“Order exceeds the max quantity set for this product and cannot be processed");
		System.out.println("------------------------------");
	}

}
