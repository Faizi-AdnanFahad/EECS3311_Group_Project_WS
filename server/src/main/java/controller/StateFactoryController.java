package controller;

import model.orderstate.IOrderState;
import model.orderstatefactory.OrderStateFactoryRepo;
import model.orderstatefactory.Utilities;

public class StateFactoryController {

	public OrderStateFactoryRepo setupFactory() {
		return Utilities.setUpFactory();
	}

	public IOrderState createFactory(OrderStateFactoryRepo repo, int index) {
		return repo.getFactoryItem(index).create();
	}
}
