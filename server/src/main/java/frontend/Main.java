package frontend;
import middleware.wares.*;

public class Main {

	public static void main(String[] args) {
		// Order processor
		OrderProcessorFacade orderProcessor = OrderProcessorFacade.getInstance();

		// Server operation proxy
		ServerOperation serverOperation = ServerOperation.getInstance();

		// Init systems
		serverOperation.Init();

		// Start processing orders in the OrderQueue
		while (orderProcessor.isActive()) orderProcessor.process();
	}

}
