package admin;

import java.util.List;

import database.AdminDAO;
import model.User;

public class Auth {
	   AdminDAO admin = new AdminDAO();
	    
	    
	    public boolean authenticateUser(String username, String password) {
	        return admin.checkAuth(username, password);
	    }
	
}