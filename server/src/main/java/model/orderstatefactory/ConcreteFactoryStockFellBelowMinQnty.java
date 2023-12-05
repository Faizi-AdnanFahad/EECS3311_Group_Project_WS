package model.orderstatefactory;

import model.orderstate.IOrderState;
import model.orderstate.StockFellBelowMinQntyState;

public class ConcreteFactoryStockFellBelowMinQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new StockFellBelowMinQntyState();
	}

}
