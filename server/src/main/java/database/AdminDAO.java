package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.User;



public class AdminDAO {
	
	//This method retrieves the password of the respective username by the user in the GUI.
    public static String retrivePassword(String username) {
  	  
  	  String path = "jdbc:sqlite:C://dev//repos//EECS3311_Group_Project_WS//database//admin.db";
 		String query = "SELECT Password FROM credentials WHERE Username = ?;";
 		String password = null;
 		
 		
 		try {
 		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(path);
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1,username);
      ResultSet  resultSet = pstmt.executeQuery();
      
      if(resultSet.next()) {
      	password = resultSet.getString("Password");
      }
 		}
      catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
     
 		return password;

}
    
    
   //This method is to check if the User exists in the system and is stored in the database or no. 
    public  List<User> checkIfUserExists(String username) {
    	
    	String path = "jdbc:sqlite:C://dev//repos//EECS3311_Group_Project_WS//database//admin.db";
 		String query = "SELECT * FROM credentials";
 		int count = 0;
 		List<User> l = new ArrayList();
 		
 		
 		try {
 	 		Class.forName("org.sqlite.JDBC");
 			Connection conn = DriverManager.getConnection(path);
 			PreparedStatement pstmt = conn.prepareStatement(query);
 			
 			ResultSet resultSet = pstmt.executeQuery();
 	      
 	      //Get the count of rows with the input username 
 	      while(resultSet.next()) {
 	    	  String u = resultSet.getString("Username");
 	    	  String p = resultSet.getString("Password");

 	    	  User temp = new User();
 	    	  temp.setUsername(u);
 	    	  temp.setPassword(p);
 	    	  
 	    	  l.add(temp);

 	      }
 		}
 	     catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 	
 		
 		return l;
 		
    }
    
}
	
