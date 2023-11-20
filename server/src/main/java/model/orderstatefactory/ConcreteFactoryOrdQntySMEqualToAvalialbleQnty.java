package model.orderstatefactory;

import model.orderstate.IOrderState;
import model.orderstate.OrderedQntySMEqualToAvailableQntyState;

public class ConcreteFactoryOrdQntySMEqualToAvalialbleQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new OrderedQntySMEqualToAvailableQntyState();
	}

}
