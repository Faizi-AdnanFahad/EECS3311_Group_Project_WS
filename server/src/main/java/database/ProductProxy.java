package database;

import java.util.List;

import database.DAO.ProductDAO;
import model.Product;

public class ProductProxy {
	ProductDAO productDAO = new ProductDAO();

	public List<Product> retriveProductDetails() {
		return productDAO.retriveProductDetails();	
	}

	public boolean updateProduct(String productName, int orderedQuantity) {
		return productDAO.updateProduct(productName, orderedQuantity);	
	}

	public void updateProductQuantity(String productName, int newStockQuantity) {
		productDAO.updateProductQuantity(productName, newStockQuantity);	
	}
}
