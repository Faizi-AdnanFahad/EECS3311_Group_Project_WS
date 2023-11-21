package model.orderstatefactory;

import model.orderstate.IOrderState;
import model.orderstate.OrderedQntyLT_TargetMinState;

public class ConcreteFactoryOrdQntyLT_TargetMin implements IFactoryOrderState {

	public IOrderState create() {
		return new OrderedQntyLT_TargetMinState();
	}

}
