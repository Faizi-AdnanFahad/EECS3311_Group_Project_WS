package middleware.pricingstrategyfactory;

public class Utilities {
	public static PricingStrategyFactoryRepo setUpFactory() {
		PricingStrategyFactoryRepo aRepo = PricingStrategyFactoryRepo.getInstance();
		aRepo.addFactoryItem(0, new FactoryNoDiscount());
		aRepo.addFactoryItem(1, new FactoryOrderedQntyOverAThreshold());
		aRepo.addFactoryItem(2, new FactoryOrderedTotalOverAThreshold());
		return aRepo;
	}
}
