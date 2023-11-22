package middleware.jobs;

import controller.OrderController;
import middleware.Middleware;
import middleware.OrderQueue;
import model.Order;
import model.Product;
import model.orderstate.IOrderState;
import model.orderstatefactory.OrderStateFactoryRepo;
import model.orderstatefactory.Utilities;

public class OrderProcessorFacade extends Middleware {
	OrderQueue orderQueue = null;
	public static OrderProcessorFacade instance = null;
	private OrderController orderController;

	private OrderProcessorFacade() {
		super("Order Processor", true);
		orderQueue = new OrderQueue();
		this.activate();
		this.orderController = new OrderController();
	}

	public static OrderProcessorFacade getInstance() {
		System.out.println(instance);

		if (instance != null)
			return instance;
		return new OrderProcessorFacade();
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
		 *  Step 2 - compare the ordered quantity against the available and target max
		 *  and create a correct int returning
		 */
		int stateNum = this.orderController.compareOrderedQntyAgainstProduct(order);

		// Step 3 - process the order the order
		processOrder(order, stateNum);

		// For testing
		this.disable();

	}

	private void processOrder(Order order, int stateNum) {
		// Setup the factory
		OrderStateFactoryRepo stateFactoryRepo = Utilities.setUpFactory();

		/*
		 * create the right state using factory method based on the correct state
		 * situation that has been passed as an argument
		 */
		IOrderState orderState = stateFactoryRepo.getFactoryItem(stateNum).create();
		// set the correct order state to the state the factory has created.
		this.orderController.setOrderState(orderState);

		// process the order based on the state of the order against the database.
		this.orderController.processOrder(order);
	}

}