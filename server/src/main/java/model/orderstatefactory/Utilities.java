package model.orderstatefactory;

public class Utilities {
	public static OrderStateFactoryRepo setUpFactory() {
		OrderStateFactoryRepo aRepo = OrderStateFactoryRepo.getInstance();
		aRepo.addFactoryItem(1, new ConcreteFactoryOrdQntyGTTargetMaxQnty());
		aRepo.addFactoryItem(2, new ConcreteFactoryOrdQntyLT_TargetMin());
		aRepo.addFactoryItem(3, new ConcreteFactoryOrdQntyGTAvailbaleQnty());
		aRepo.addFactoryItem(4, new ConcreteFactoryOrdQntySMEqualToAvalialbleQnty());
		return aRepo;
	}
}
