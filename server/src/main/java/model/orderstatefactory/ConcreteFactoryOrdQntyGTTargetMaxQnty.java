package model.orderstatefactory;

import model.orderstate.IOrderState;
import model.orderstate.OrderedQntyGTTargetMaxQntyState;

public class ConcreteFactoryOrdQntyGTTargetMaxQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new OrderedQntyGTTargetMaxQntyState();
	}

}
