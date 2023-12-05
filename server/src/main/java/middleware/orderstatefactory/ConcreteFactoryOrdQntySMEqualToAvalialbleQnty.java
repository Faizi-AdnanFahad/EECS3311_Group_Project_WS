package middleware.orderstatefactory;

import middleware.orderstate.IOrderState;
import middleware.orderstate.OrderedQntySMEqualToAvailableQntyState;

public class ConcreteFactoryOrdQntySMEqualToAvalialbleQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new OrderedQntySMEqualToAvailableQntyState();
	}

}
