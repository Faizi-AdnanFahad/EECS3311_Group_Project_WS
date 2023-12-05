package controller;

import java.util.List;
import java.util.Map;

import model.Product;

public class ProductController {
	
	public List<Product> getAllProduct() {
		Product product = new Product();
		return product.getProductList();
	}

	public Map<String, Integer> getAvaliableProducts() {
		return (new Product()).findAvailableProductsAndQuantities();
	}
}
