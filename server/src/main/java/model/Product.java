package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.ProductController;
import database.ProductDAO;

public class Product {
	private String id;
	private String name;
	private int stockQuantity;
	private int price;
	private int targetMaxStockQuantity;
	private int targetMinStockQuantity;
	private int restockSchedule;


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

	/*
	 * Returns a list of all products from the database.
	 */
	public List<Product> getProductList() {
		ProductDAO productDAO = new ProductDAO();
		return productDAO.retriveProductDetails();
	}

	public Map<String, Integer> findAvailableProductsAndQuantities() {
		List<Product> productList = getProductList();

		Map<String, Integer> productMap = new HashMap<String, Integer>();
		for (Product product : productList) {
			productMap.put(product.getName(), product.getStockQuantity());
		}

		return productMap;
	}
}
