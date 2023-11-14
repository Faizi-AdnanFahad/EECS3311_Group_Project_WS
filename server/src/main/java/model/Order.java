package model;

import java.sql.Date;
import java.sql.Time;

public class Order {
	private Product orderedProduct;
	private int orderedQuantity;
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
}
