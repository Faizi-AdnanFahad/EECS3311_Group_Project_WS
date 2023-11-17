package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO {

	//This method retrieves the price of the respective Product from the user
	public static int retrivePrice(String productName) {

		String path = "jdbc:sqlite:C://dev//repos//EECS3311_Group_Project_WS//database//product.db";
		String query = "SELECT Price FROM product WHERE Name = ?;";
		int price = 0;


		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(path);
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1,productName);
			ResultSet  resultSet = pstmt.executeQuery();

			if(resultSet.next()) {
				price = resultSet.getInt("Price");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		

		return price;
	}

	//This method retrieves the StockQuantity of the respective Product from the user
	public static int retriveStockQuantity(String productName) {

		String path = "jdbc:sqlite:C://dev//repos//EECS3311_Group_Project_WS//database//product.db";
		String query = "SELECT StockQuantity FROM product WHERE Name = ?;";
		int stockQuantity = 0;


		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(path);
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1,productName);
			ResultSet  resultSet = pstmt.executeQuery();

			if(resultSet.next()) {
				stockQuantity = resultSet.getInt("StockQuantity");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		

		return stockQuantity;

	}



}

