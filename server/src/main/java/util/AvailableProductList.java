package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.ProductController;
import model.Product;

public class AvailableProductList {
	private static AvailableProductList instance = null;

	private HashMap<String, Integer> availableProductList = new HashMap<String, Integer>();

	public HashMap<String, Integer> getAvailableProductList() {
		return availableProductList;
	}

	public static AvailableProductList getInstance() {
		if (instance == null)
			instance = new AvailableProductList();

		return instance;
	}

	private AvailableProductList() {
		// findAvailableProductsAndQuantities();
	}

	public HashMap<String, Integer> findAvailableProductsAndQuantities() {

		availableProductList.clear();

		// Here we query the Product DB and we get the product names or the product IDs
		ProductController productController = new ProductController();
		List<Product> products = productController.getAllProduct();

		for (Product product : products) {
			availableProductList.put(product.getName(), product.getStockQuantity());
		}
		return availableProductList;
	}

}
