package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.User;



public class AdminDAO {
    
    
   //This method retrieves all the data for the credentials of th user at once . 
    public  List<User> retriveUsernameAndPassword() {
    	
    	String path = "jdbc:sqlite:C://dev//repos//EECS3311_Group_Project_WS//database//admin.db";
 		String query = "SELECT * FROM credentials";
 		List<User> listOfUser = new ArrayList();
 		
 		
 		try {
 	 		Class.forName("org.sqlite.JDBC");
 			Connection conn = DriverManager.getConnection(path);
 			PreparedStatement pstmt = conn.prepareStatement(query);
 			
 			ResultSet resultSet = pstmt.executeQuery();
 	      
 	      while(resultSet.next()) {
 	    	  String u = resultSet.getString("Username");
 	    	  String p = resultSet.getString("Password");

 	    	  User temp = new User();
 	    	  temp.setUsername(u);
 	    	  temp.setPassword(p);
 	    	  
 	    	 listOfUser.add(temp);

 	      }
 		}
 	     catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 	
 		
 		return listOfUser;
 		
    }
    
}
	
