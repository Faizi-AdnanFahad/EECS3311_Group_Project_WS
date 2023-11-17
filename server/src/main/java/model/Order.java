package model;

import java.sql.Date;
import java.sql.Time;
import view.ConcreteSubject;

public class Order {
	private Product orderedProduct;
	private int orderedQuantity;
	private int discountStrategyID;
	private Date orderedRecievedDate;
	private Time orderedRecievedTime;

	public Order(Product orderedProduct, int orderedQuantity) {
		this.orderedProduct = orderedProduct;
		this.orderedQuantity = orderedQuantity;
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
	public void updateView(ConcreteSubject concreteSubject) {
		concreteSubject.orderCompleted(this.orderedProduct, this.orderedQuantity);
	}
}
