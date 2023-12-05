package util;

import middleware.orderstatefactory.ConcreteFactoryOrdQntyGTAvailbaleQnty;
import middleware.orderstatefactory.ConcreteFactoryOrdQntyGTTargetMaxQnty;
import middleware.orderstatefactory.ConcreteFactoryOrdQntySMEqualToAvalialbleQnty;
import middleware.orderstatefactory.ConcreteFactoryStockFellBelowMinQnty;
import middleware.orderstatefactory.OrderStateFactoryRepo;
import middleware.pricingstrategyfactory.FactoryNoDiscount;
import middleware.pricingstrategyfactory.FactoryOrderedQntyOverAThreshold;
import middleware.pricingstrategyfactory.FactoryOrderedTotalOverAThreshold;
import middleware.pricingstrategyfactory.PricingStrategyFactoryRepo;

public class Utilities {
	public static OrderStateFactoryRepo setUpOrderFactory() {
		OrderStateFactoryRepo aRepo = OrderStateFactoryRepo.getInstance();
		aRepo.addFactoryItem(1, new ConcreteFactoryOrdQntyGTTargetMaxQnty());
		aRepo.addFactoryItem(2, new ConcreteFactoryStockFellBelowMinQnty());
		aRepo.addFactoryItem(3, new ConcreteFactoryOrdQntyGTAvailbaleQnty());
		aRepo.addFactoryItem(4, new ConcreteFactoryOrdQntySMEqualToAvalialbleQnty());
		return aRepo;
	}

	public static PricingStrategyFactoryRepo setUpPricingFactory() {
		PricingStrategyFactoryRepo aRepo = PricingStrategyFactoryRepo.getInstance();
		aRepo.addFactoryItem(0, new FactoryNoDiscount());
		aRepo.addFactoryItem(1, new FactoryOrderedQntyOverAThreshold());
		aRepo.addFactoryItem(2, new FactoryOrderedTotalOverAThreshold());
		return aRepo;
	}
}
