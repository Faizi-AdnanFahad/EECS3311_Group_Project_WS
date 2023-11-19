package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;

public class ProductDAO {

	//This method retrieves the price of the respective Product from the user
	public List<Product> retriveProductDetails() {

		String path = "jdbc:sqlite:database\\product.db";
		String query = "SELECT * FROM product;";
		List<Product> listOfProduct = new ArrayList();
		


		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(path);
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
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
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		

		return listOfProduct;
	}

	//This method retrieves the StockQuantity of the respective Product from the user
//	public static int retriveStockQuantity(String productName) {
//
//		String path = "jdbc:sqlite:database\\product.db";
//		String query = "SELECT StockQuantity FROM product WHERE Name = ?;";
//		int stockQuantity = 0;
//
//
//		try {
//			Class.forName("org.sqlite.JDBC");
//			Connection conn = DriverManager.getConnection(path);
//			PreparedStatement pstmt = conn.prepareStatement(query);
//
//			pstmt.setString(1,productName);
//			ResultSet  resultSet = pstmt.executeQuery();
//
//			if(resultSet.next()) {
//				stockQuantity = resultSet.getInt("StockQuantity");
//			}
//		}
//		catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 		
//
//		return 0;
//
//	}



}

