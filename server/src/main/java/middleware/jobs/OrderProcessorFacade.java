package middleware.jobs;

import controller.OrderController;
import controller.FactoryController;
import middleware.Middleware;
import model.Order;
import model.orderstate.IOrderState;
import model.orderstatefactory.OrderStateFactoryRepo;
import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategyFactory.PricingStrategyFactoryRepo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class OrderProcessorFacade extends Middleware {
	private OrderController orderController;
	private BlockingQueue<Order> orderQueue;

	public static OrderProcessorFacade instance = null;

	private OrderProcessorFacade() {
		super("Order Processor", true);
		this.activate();

		orderQueue = new LinkedBlockingDeque<Order>();
		this.orderController = new OrderController();
	}

	public static OrderProcessorFacade getInstance() {
		if (instance == null) {
			instance = new OrderProcessorFacade();
		}

		return instance;
	}

	public void add(Order order) {
		// Add order to the OrderQueue
		orderQueue.add(order);
	}

//	public OrderQueue getQueue() {
//		return orderQueue;
//	}

	public void process() {
		// Grab next in line for processing
		System.out.printf("Queue size is %d, state: %b\n", orderQueue.size(), this.isActive());
		if (orderQueue.size() == 0) {
//			System.out.println("Queue is empty, nothing to process");
			return;
		}

		// Send the data where it needs to go (Controller)
		System.out.println("Order will go for order processing in Controller Package");

		/*
		 * Step 1 - get the first order in the queue
		 */

		try {
			Order order = orderQueue.take();

			if (order == null)
				return;

			System.out.println(order.getOrderPrice());
			System.out.println(order.getOrderedProduct().getName());

			/*
			 * Step 2 - determine the price of an order
			 */
			setOrderPrice(order);

			/*
			 * Step 3 - process the order to completion
			 */
			processOrder(order);

			// For testing
			// this.disable();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setOrderPrice(Order order) {
		// Find out which pricing strategy is relevant based on the order - used to
		// create the relevant strategy using the factory
		int pricintStrategyNum = this.orderController.determineDiscountStrategy(order);

		// setup pricing strategy factory
		FactoryController fc = new FactoryController();
		PricingStrategyFactoryRepo repo = fc.setUpPricingFactory();
		IPricingStrategy pricingStrategy = fc.createPricingStrategy(repo, pricintStrategyNum);

		// calculate and set the correct price for the order
		this.orderController.setPricingStrategy(pricingStrategy);
		this.orderController.calculateOrderPrice(order);
	}

	private void processOrder(Order order) {
		/*
		 * compare the ordered quantity against the available and target max and return
		 * the correct index - used to create the correct state factory
		 */
		int stateNum = this.orderController.compareOrderedQntyAgainstProduct(order);

		// Setup the factory
		FactoryController sfc = new FactoryController();
		OrderStateFactoryRepo repo = sfc.setupStateFactory();

		/*
		 * create the right state using factory method
		 */
		IOrderState orderState = sfc.createState(repo, stateNum);
		// set the correct order state to the state the factory has created.
		this.orderController.setOrderState(orderState);

		// process the order based on the state of the order against the database.
		this.orderController.processOrder(order);
	}

}