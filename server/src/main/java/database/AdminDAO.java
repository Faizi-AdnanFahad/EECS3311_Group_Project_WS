package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.User;



public class AdminDAO {
    
    
	// Check if user is Authenticated
    public boolean checkAuth(String username, String password) {
    	
    	String path = "jdbc:sqlite:database/admin.db";
 		String query = "SELECT username, password FROM credentials WHERE username=? AND password=?";
 		
 		try {
 	 		Class.forName("org.sqlite.JDBC");

 			Connection conn = DriverManager.getConnection(path);
 			PreparedStatement pstmt = conn.prepareStatement(query);

 			pstmt.setString(1, username);
 			pstmt.setString(2, password);

 			ResultSet resultSet = pstmt.executeQuery();

 			return resultSet.next();
 	      
 		}
 	     catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 	
 		
 		return false;
 		
    }
    
}
	
