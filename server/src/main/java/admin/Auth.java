package admin;

import database.AdminProxy;

public class Auth {
	AdminProxy admin = new AdminProxy();

	public boolean authenticateUser(String username, String password) {
		return admin.checkAuth(username, password);
	}

}