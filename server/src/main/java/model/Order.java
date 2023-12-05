package model;

import java.sql.Date;
import java.sql.Time;

import database.DAO.ProductDAO;
import view.BarChartView;
import view.ConcretePublisher;
import view.ReportView;

public class Order {
	private final int MIN_AMOUNT_FOR_BASIC_DISCOUNT = 150;
	private final int TOTAL_ORDER_PRICE_DISCOUNT = 1000;
	private Product orderedProduct;
	private int orderedQuantity;
	private int discountStrategyID;
	private double orderPrice;
	private Date orderedRecievedDate;
	private Time orderedRecievedTime;
	private ConcretePublisher concretePublisher;

	public Order() {
	}

	public Order(Product orderedProduct, int orderedQuantity) {
		this.orderedProduct = orderedProduct;
		this.orderedQuantity = orderedQuantity;
		this.concretePublisher = new ConcretePublisher();
	}

	public Product getOrderedProduct() {
		return orderedProduct;
	}

	public void setOrderedProduct(Product orderedProduct) {
		this.orderedProduct = orderedProduct;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getOrderedRecievedDate() {
		return orderedRecievedDate;
	}

	public void setOrderedRecievedDate(Date orderedRecievedDate) {
		this.orderedRecievedDate = orderedRecievedDate;
	}

	public Time getOrderedRecievedTime() {
		return orderedRecievedTime;
	}

	public void setOrderedRecievedTime(Time orderedRecievedTime) {
		this.orderedRecievedTime = orderedRecievedTime;
	}

	public int getDiscountStrategyID() {
		return discountStrategyID;
	}

	public void setDiscountStrategyID(int discountStrategyID) {
		this.discountStrategyID = discountStrategyID;
	}

	public ConcretePublisher getConcretePublisher() {
		return concretePublisher;
	}

	public void setConcretePublisher(ConcretePublisher concretePublisher) {
		this.concretePublisher = concretePublisher;
	}

	/* Informs all viewers with new updates when a change occurs */
	public void updateViewers() {
		this.concretePublisher.orderCompleted(this.orderedProduct, this.orderedQuantity);
	}

	// helper method to update the product db with ordered quantity
	public boolean performOrder() {
		ProductDAO productDAO = new ProductDAO();
		this.orderedProduct.setStockQuantity(this.orderedProduct.getStockQuantity() - this.orderedQuantity);
		return productDAO.updateProduct(this.orderedProduct.getName(), this.orderedQuantity);
	}

	/*
	 * Adds all viewers to the list of observers - Observer Design Pattern
	 */
	public void addViewers() {
		// reset if any viewers exist already
		this.concretePublisher.resetViewers();

		// add viewers as observers
		this.concretePublisher.addViewers(new BarChartView(concretePublisher));
		this.concretePublisher.addViewers(new ReportView(concretePublisher));
	}

	/*
	 * This method is mainly used to find which state of the order is in. The
	 * relevant state is created with the state factory. The numbers returned have
	 * been mapped to the relevant state factory in the Utility class of factory for
	 * state factory.
	 */
	public int compareOrderedQntyAgainstProduct() {
		int targetMaxQnty = this.orderedProduct.getTargetMaxStockQuantity();
		int targetMinQnty = this.orderedProduct.getTargetMinStockQuantity();
		int stockQnty = this.orderedProduct.getStockQuantity();

		if (this.orderedProduct.getStockQuantity() < targetMinQnty) {
			return 2;
		}

		if (this.orderedQuantity > targetMaxQnty) {
			return 1;
		} else if (this.orderedQuantity > stockQnty) {
			return 3;
		} else { // ordered quantity must be less than stock quantity and does not exceed above
					// targetMax quantity and below target minimum quantity.
			return 4;
		}
	}

	/*
	 * This method is mainly used to find which pricing strategy to create with the
	 * factory. The numbers returned have been mapped to the relevant pricing
	 * strategies in the Utility class of factory for pricing strategy
	 */
	public int determineDiscountStrategy() {
		/*
		 * If an order quantity is of more than MIN_AMOUNT_FOR_BASIC_DISCOUNT number of
		 * units then a y% discount is applied AND If the total value of the order is
		 * more than TOTAL_ORDER_PRICE_DISCOUNT dollars, an additional 5% discount is
		 * applied
		 */
		if (this.orderedQuantity > MIN_AMOUNT_FOR_BASIC_DISCOUNT
				&& (this.orderedQuantity * this.orderedProduct.getPrice() > TOTAL_ORDER_PRICE_DISCOUNT)) {
			return 2;
		}

		/*
		 * If ONLY an order quantity is of more than MIN_AMOUNT_FOR_BASIC_DISCOUNT
		 * number of units then a y% discount is applied
		 */
		if (this.orderedQuantity > MIN_AMOUNT_FOR_BASIC_DISCOUNT) {
			return 1;
		}

		return 0; /* If no discount applies */
	}
}