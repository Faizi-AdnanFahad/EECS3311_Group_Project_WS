package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ProductProxy;

public class Product {
	private String id;
	private String name;
	private int stockQuantity;
	private int price;
	private int targetMaxStockQuantity;
	private int targetMinStockQuantity;
	private int restockSchedule;
	private ProductProxy productProxy = new ProductProxy();

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
		return productProxy.retriveProductDetails();
	}

	public Map<String, Integer> findAvailableProductsAndQuantities() {
		List<Product> productList = getProductList();

		Map<String, Integer> productMap = new HashMap<String, Integer>();
		for (Product product : productList) {
			productMap.put(product.getName(), product.getStockQuantity());
		}

		return productMap;
	}

	public void restocking() {

		List<Product> prodList = getProductList();

		int targetMaxStockQuan = 0;
		int currentStock = 0;
		int restockSched = 0;
		int toBeOrdered = 0;
		String productName = null;

		for (Product p : prodList) {
			if (p.getName().equals(this.getName())) {
				targetMaxStockQuan = p.getTargetMaxStockQuantity();
				currentStock = p.getStockQuantity();
				restockSched = p.getRestockSchedule();
				productName = p.getName();
			}
		}

		toBeOrdered = targetMaxStockQuan - currentStock;
		System.out.println(toBeOrdered);
		System.out.println(targetMaxStockQuan); // 100
		System.out.println(currentStock);// 20
		System.out.println(restockSched); // 30

		// 2
		int noOfRestockOperation = toBeOrdered / restockSched; // 80

		for (int i = 0; i < noOfRestockOperation; i++) {
			// productDAO.updateProductQuantity(productName, restockSched);
			currentStock = currentStock + restockSched;

			/* Additionally needed for "Low Stock State" */
			this.stockQuantity += currentStock;

			productProxy.updateProductQuantity(productName, currentStock);
			System.out.println(currentStock);
			System.out.println("We are Working");
		}

		if (targetMaxStockQuan > currentStock) {
			int remainingQuantity = (targetMaxStockQuan - currentStock);
//					productDAO.updateProductQuantity(productName, remainingQuantity);

			productProxy.updateProductQuantity(productName, remainingQuantity + currentStock);
			System.out.println(remainingQuantity + currentStock);
			System.out.println(noOfRestockOperation);

		}

	}

}
