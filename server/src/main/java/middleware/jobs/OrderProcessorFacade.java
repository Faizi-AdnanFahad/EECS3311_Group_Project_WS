package middleware.jobs;

import controller.OrderController;
import controller.FactoryController;
import middleware.Middleware;
import middleware.OrderQueue;
import model.Order;
import model.orderstate.IOrderState;
import model.orderstatefactory.OrderStateFactoryRepo;
import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategyFactory.PricingStrategyFactoryRepo;

public class OrderProcessorFacade extends Middleware {
	private OrderQueue orderQueue = null;
	private OrderController orderController;

	public static OrderProcessorFacade instance = null;

	private OrderProcessorFacade() {
		super("Order Processor", true);
		this.activate();

		orderQueue = new OrderQueue();
		this.orderController = new OrderController();
	}

	public static OrderProcessorFacade getInstance() {
		if (instance == null) {
			instance = new OrderProcessorFacade();
			return instance;
		}

		return instance;
	}

	public void add(Order order) {
		// Add order to the OrderQueue
		orderQueue.enqueue(order);
	}

	public OrderQueue getQueue() {
		return orderQueue;
	}

	public void process() {
		// Grab next in line for processing
		System.out.printf("Queue size is %d, state: %b\n", orderQueue.getSize(), this.isActive());
		if (orderQueue.getSize() == 0) {
			System.out.println("Queue is empty, nothing to process");
			return;
		}

		// Send the data where it needs to go (Controller)
		System.out.println("Order will go for order processing in Controller Package");

		// Step 1 - get the first order in the queue
		Order order = orderQueue.dequeue();

		/*
		 * Step 2 - compare the ordered quantity against the available and target max
		 * and create a correct int returning
		 */
		int stateNum = this.orderController.compareOrderedQntyAgainstProduct(order);

		// Determine the price of the order based on the relevant strategy
		int pricintStrategyNum = this.orderController.determineDiscountStrategy(order);
		FactoryController sfc = new FactoryController();
		PricingStrategyFactoryRepo repo = sfc.setUpPricingFactory();
		IPricingStrategy pricingStrategy = sfc.createPricingStrategy(repo, pricintStrategyNum);
		pricingStrategy.calculateOrderPrice(order.getOrderedQuantity());
		System.out.println("PRICINTY STRATEGY TO BE APPLIED IS " + pricintStrategyNum);

		// Step 3 - process the order the order
		processOrder(order, stateNum);

		// For testing
		this.disable();

	}

	private void processOrder(Order order, int stateNum) {
		FactoryController sfc = new FactoryController();

		// Step 4 - Setup the factory
		OrderStateFactoryRepo repo = sfc.setupStateFactory();

		/*
		 * Step 5 create the right state using factory method based on the correct state
		 * situation that has been passed as an argument
		 */
		IOrderState orderState = sfc.createState(repo, stateNum);
		// set the correct order state to the state the factory has created.
		this.orderController.setOrderState(orderState);

		// Step 6 - process the order based on the state of the order against the
		// database.
		this.orderController.processOrder(order);
	}

}