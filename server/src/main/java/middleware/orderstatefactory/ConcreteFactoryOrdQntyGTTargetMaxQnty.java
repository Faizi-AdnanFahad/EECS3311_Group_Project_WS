package middleware.orderstatefactory;

import middleware.orderstate.IOrderState;
import middleware.orderstate.OrderedQntyGTTargetMaxQntyState;

public class ConcreteFactoryOrdQntyGTTargetMaxQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new OrderedQntyGTTargetMaxQntyState();
	}

}
