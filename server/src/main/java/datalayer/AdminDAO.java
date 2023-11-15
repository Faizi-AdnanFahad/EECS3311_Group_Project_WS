package datalayer;

import model.User;

public class AdminDAO {

	// CRUD
	public User queryUser(String username) {
		// SQL Query and obtaining info
		User user = null;

		return user;
	}

	public void CreateUser(String username, String password) {
		// SQL Query for Creating user

	}

	public boolean changePassword(String username) {
		// SQL Query for changing password
		return true;
	}

	public boolean delteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean queryPassword(String username) {
		return false;
	}
}
