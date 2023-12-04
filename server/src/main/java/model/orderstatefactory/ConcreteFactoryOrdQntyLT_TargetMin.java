package model.orderstatefactory;

import model.orderstate.IOrderState;
import model.orderstate.StockFellBelowMinQntyState;

public class ConcreteFactoryOrdQntyLT_TargetMin implements IFactoryOrderState {

	public IOrderState create() {
		return new StockFellBelowMinQntyState();
	}

}
