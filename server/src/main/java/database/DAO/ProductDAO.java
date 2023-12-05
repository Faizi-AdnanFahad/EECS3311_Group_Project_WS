package database.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO {

	// This method retrieves the price of the respective Product from the user
	public List<Product> retriveProductDetails() {

		String path = "jdbc:sqlite:database/product.db";
		String query = "SELECT * FROM product;";
		List<Product> listOfProduct = new ArrayList<Product>();

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(path);
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("Id");
				String name = resultSet.getString("Name");
				int price = resultSet.getInt("Price");
				int quantity = resultSet.getInt("StockQuantity");
				int targetMinStockQuantity = resultSet.getInt("targetMinStockQuantity");
				int targetMaxStockQuantity = resultSet.getInt("targetMaxStockQuantity");
				int restockSchedule = resultSet.getInt("restockSchedule");

				Product temp = new Product();
				temp.setId(id);
				temp.setName(name);
				temp.setPrice(price);
				temp.setStockQuantity(quantity);
				temp.setTargetMinStockQuantity(targetMinStockQuantity);
				temp.setTargetMaxStockQuantity(targetMaxStockQuantity);
				temp.setRestockSchedule(restockSchedule);

				listOfProduct.add(temp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listOfProduct;
	}

	/*
	 * Given product name and a quantity, updates the database for the product with
	 * the old quantity - ordered quantity
	 */
	public boolean updateProduct(String productName, int orderedQuantity) {
		String path = "jdbc:sqlite:database/product.db";
		String query = "UPDATE product SET StockQuantity = StockQuantity - ? WHERE Name = ?";

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(path);
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderedQuantity);
			pstmt.setString(2, productName);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateProductQuantity(String ProductName, int newStockQuantity) {
		
		
		String path = "jdbc:sqlite:database/product.db";
		String query = "UPDATE product SET StockQuantity = ? WHERE Name = ?;";
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(path);
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			
			
			pstmt.setInt(1, newStockQuantity);
			pstmt.setString(2, ProductName);
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
                System.out.println("Product stock updated successfully!");
            } else {
                System.out.println("No product found with the provided ID.");
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
