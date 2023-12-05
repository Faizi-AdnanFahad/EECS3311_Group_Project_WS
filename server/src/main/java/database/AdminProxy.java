package database;

import database.DAO.AdminDAO;

public class AdminProxy {
	AdminDAO adminDAO = null;
	public AdminProxy () {
		adminDAO = new AdminDAO();
	}

	public boolean checkAuth(String username, String password) {
		return adminDAO.checkAuth(username, password);
	}
}
