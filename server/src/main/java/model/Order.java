package model;

import java.sql.Date;
import java.sql.Time;

import view.BarChartView;
import view.ConcretePublisher;
import view.IView;
import view.ReportView;

public class Order {
	private Product orderedProduct;
	private int orderedQuantity;
	private int discountStrategyID;
	private Date orderedRecievedDate;
	private Time orderedRecievedTime;
	private ConcretePublisher concretePublisher;

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

	/* Updates all viewers which result in the Observer pattern */
	public void updateViewers() {
		this.concretePublisher.orderCompleted(this.orderedProduct, this.orderedQuantity);
	}

	public void addViewers() {
		ConcretePublisher concretePublisher = new ConcretePublisher();
		// add viewers as observers
		IView barChartView = new BarChartView(this.concretePublisher);
		IView reportView = new ReportView(this.concretePublisher);
	}

	public int compareOrderedQntyAgainstProduct() {
		int targetMaxQnty = this.orderedProduct.getTargetMaxStockQuantity();
		int targetMinQnty = this.orderedProduct.getTargetMinStockQuantity();
		int stockQnty = this.orderedProduct.getStockQuantity();

		if (this.orderedQuantity > targetMaxQnty) {
			return 1;
		} else if (this.orderedQuantity < targetMinQnty) {
			return 2;
		} else if (this.orderedQuantity > stockQnty) {
			return 3;
		} else { // ordered quantity must be less than stock quantity and does not exceed above
					// targetMax quantity and below target minimum quantity.
			return 4;
		}
	}
}
