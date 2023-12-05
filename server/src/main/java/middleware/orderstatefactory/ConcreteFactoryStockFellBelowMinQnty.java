package middleware.orderstatefactory;

import middleware.orderstate.IOrderState;
import middleware.orderstate.StockFellBelowMinQntyState;

public class ConcreteFactoryStockFellBelowMinQnty implements IFactoryOrderState {

	public IOrderState create() {
		return new StockFellBelowMinQntyState();
	}

}
