package middleware.wares;

import controller.OrderController;
import controller.FactoryController;
import middleware.Middleware;
import model.Order;
import model.orderstate.IOrderState;
import model.orderstate.OrderedQntySMEqualToAvailableQntyState;
import model.orderstatefactory.OrderStateFactoryRepo;
import model.pricingStrategy.IPricingStrategy;
import model.pricingStrategyFactory.PricingStrategyFactoryRepo;
import util.Constants;

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
		try {

			// Let's not overload the CPU
			Thread.sleep(Constants.PROCESSING_LOOP_DELAY);

			if (orderQueue.size() == 0) {
				// System.out.println("Queue is empty, nothing to process");
				return;
			}

			/*
			 * Step 1 - get the first order in the queue to check processing.
			 */
			Order order = orderQueue.take();

			/*
			 * Step 2 - determine order total if the order is eligible for completion.
			 */
			this.orderController.checkPricingEligiblity(order);

			/*
			 * Step 3 - Process order the order
			 */
			this.orderController.completeProcessOrdering(order);

			// Disable the loop
			// testing - Will disable the processing loop
			// this.disable();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	private void setOrderPrice(Order order) {
//		// Find out which pricing strategy is relevant based on the order - used to
//		// create the relevant strategy using the factory
//		int pricintStrategyNum = this.orderController.determineDiscountStrategy(order);
//
//		// setup pricing strategy factory
//		FactoryController fc = new FactoryController();
//		PricingStrategyFactoryRepo repo = fc.setUpPricingFactory();
//		IPricingStrategy pricingStrategy = fc.createPricingStrategy(repo, pricintStrategyNum);
//
//		// calculate and set the correct price for the order
//		this.orderController.setPricingStrategy(pricingStrategy);
//		this.orderController.calculateOrderPrice(order);
//	}

}