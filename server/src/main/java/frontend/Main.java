package frontend;

import middleware.MiddlewareContext;
import middleware.jobs.*;

public class Main {

	public static void main(String[] args) {

		MiddlewareContext mCtx = MiddlewareContext.getInstance();

		OrderProcessorFacade orderProcessor = OrderProcessorFacade.getInstance();
		// Server operation proxy
		ServerOperation serverOperation = ServerOperation.getInstance();

		// Order processor

		// Register our middlewares
		mCtx.register(orderProcessor);
		mCtx.register(serverOperation);

		// Init systems
		serverOperation.Init(orderProcessor);

		// Start processing orders in the OrderQueue
		while (orderProcessor.isActive())
			orderProcessor.process();
	}

}
