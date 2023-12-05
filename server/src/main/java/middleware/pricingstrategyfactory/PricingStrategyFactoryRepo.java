package middleware.pricingstrategyfactory;

import java.util.HashMap;
import java.util.Map;

public class PricingStrategyFactoryRepo {
	private static PricingStrategyFactoryRepo instance = null;
	private Map<Integer, IFactoryPricingStrategy> mapping;

	private PricingStrategyFactoryRepo() {
		this.mapping = new HashMap<Integer, IFactoryPricingStrategy>();
	}

	public static PricingStrategyFactoryRepo getInstance() {
		if (instance == null) {
			PricingStrategyFactoryRepo.instance = new PricingStrategyFactoryRepo();
		}
		return PricingStrategyFactoryRepo.instance;
	}

	public IFactoryPricingStrategy getFactoryItem(int num) {
		return this.mapping.get(num);
	}

	public void addFactoryItem(int id, IFactoryPricingStrategy f) {
		this.mapping.put(id, f);
	}
}
