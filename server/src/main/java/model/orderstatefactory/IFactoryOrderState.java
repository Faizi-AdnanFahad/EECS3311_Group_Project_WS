package model.orderstatefactory;

import model.orderstate.IOrderState;

public interface IFactoryOrderState {
	
	public IOrderState create();
}
