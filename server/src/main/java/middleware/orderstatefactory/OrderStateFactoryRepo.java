package middleware.orderstatefactory;

import java.util.HashMap;
import java.util.Map;

public class OrderStateFactoryRepo {
	private static OrderStateFactoryRepo instance = null;
	private Map<Integer, IFactoryOrderState> mapping;

	private OrderStateFactoryRepo() {
		this.mapping = new HashMap<Integer, IFactoryOrderState>();
	}

	public static OrderStateFactoryRepo getInstance() {
		if (instance == null) {
			OrderStateFactoryRepo.instance = new OrderStateFactoryRepo();
		}
		return OrderStateFactoryRepo.instance;
	}

	public IFactoryOrderState getFactoryItem(int num) {
		return this.mapping.get(num);
	}

	public void addFactoryItem(int id, IFactoryOrderState f) {
		this.mapping.put(id, f);
	}
}
