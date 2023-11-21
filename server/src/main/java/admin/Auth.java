package admin;

import java.util.List;

import database.AdminDAO;
import model.User;

public class Auth {
	


	   AdminDAO admin = new AdminDAO();
	    
	    
	    public boolean authenticateUser(String username, String password) {
	    	
	        List<User> userList = admin.retriveUsernameAndPassword();

	        for (User user : userList) {
	            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
	                return true; 
	            }
	        }
	        return false; 
	    }
	
}