package model;

import model.statePattern.IProductState;

public class Product {
	private String id;
	private String name;
	private int stockQuantity;
	private int price;
	private int targetMaxStockQuantity;
	private int targetMinStockQuantity;
	private int restockSchedule;
	private int discountStrategyID;
	private IProductState productState;
	
	public Product() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTargetMaxStockQuantity() {
		return targetMaxStockQuantity;
	}

	public void setTargetMaxStockQuantity(int targetMaxStockQuantity) {
		this.targetMaxStockQuantity = targetMaxStockQuantity;
	}

	public int getTargetMinStockQuantity() {
		return targetMinStockQuantity;
	}

	public void setTargetMinStockQuantity(int targetMinStockQuantity) {
		this.targetMinStockQuantity = targetMinStockQuantity;
	}

	public int getRestockSchedule() {
		return restockSchedule;
	}

	public void setRestockSchedule(int restockSchedule) {
		this.restockSchedule = restockSchedule;
	}

	public int getDiscountStrategyID() {
		return discountStrategyID;
	}

	public void setDiscountStrategyID(int discountStrategyID) {
		this.discountStrategyID = discountStrategyID;
	}

	public IProductState getProductState() {
		return this.productState;
	}

	public void setProductState(IProductState newState) {
		this.productState = newState;
	}
	
	public void perform() {
		this.productState.perform();
	}

}
