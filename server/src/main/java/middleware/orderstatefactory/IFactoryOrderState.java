package middleware.orderstatefactory;

import middleware.orderstate.IOrderState;

public interface IFactoryOrderState {
	
	public IOrderState create();
}
