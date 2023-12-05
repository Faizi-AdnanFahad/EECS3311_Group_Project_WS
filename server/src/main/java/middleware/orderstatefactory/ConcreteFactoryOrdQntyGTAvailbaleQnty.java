package middleware.orderstatefactory;

import middleware.orderstate.IOrderState;
import middleware.orderstate.OrderedQntyGTAvailableQntyState;

public class ConcreteFactoryOrdQntyGTAvailbaleQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new OrderedQntyGTAvailableQntyState();
	}

}
