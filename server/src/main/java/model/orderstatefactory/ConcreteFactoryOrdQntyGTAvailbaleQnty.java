package model.orderstatefactory;

import model.orderstate.IOrderState;
import model.orderstate.OrderedQntyGTAvailableQntyState;

public class ConcreteFactoryOrdQntyGTAvailbaleQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new OrderedQntyGTAvailableQntyState();
	}

}
