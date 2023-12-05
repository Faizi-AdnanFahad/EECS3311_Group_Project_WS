package middleware.wares;

import controller.OrderController;
import controller.FactoryController;
import middleware.Middleware;
import middleware.orderstate.IOrderState;
import middleware.orderstate.OrderedQntySMEqualToAvailableQntyState;
import middleware.orderstatefactory.OrderStateFactoryRepo;
import middleware.pricingstrategy.IPricingStrategy;
import middleware.pricingstrategyfactory.PricingStrategyFactoryRepo;
import model.Order;
import util.Constants;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class OrderProcessorFacade extends Middleware {
	private OrderController orderController;
	private BlockingQueue<Order> orderQueue;
	private BlockingQueue<String> messageQueue;
	private CountDownLatch countDownLatch;

	public static OrderProcessorFacade instance = null;
	

	private OrderProcessorFacade() {
		super("Order Processor", true);
		this.activate();

		orderQueue = new LinkedBlockingDeque<Order>();
		messageQueue = new ArrayBlockingQueue<String>(64);

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

	public void setLatch(CountDownLatch cdl) {
		// Add order to the OrderQueue
		countDownLatch = cdl;
	}

	public CountDownLatch getLatch() {
		return countDownLatch;
	}

	public void addMessage(String msg) {
		// Add order to the OrderQueue
		messageQueue.add(msg);
	}

	public BlockingQueue<String> getMessageQueue() {
		return messageQueue;
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
			 * Step 2 - determine total price if the order is eligible for completion.
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
}