//package controller;
//
//import admin.AuthenticationServer;
//import model.User;
//
//public class UserController {
//	private AuthenticationServer authServer = new AuthenticationServer();
//
//	public void createUser() {
//		User user = new User("alex5000", "Toronto33");
//		authServer.setUser(user);
//
//		authServer.CreateUser();
//	}
//
//	public boolean verifyCredintials(String username, String password) {
//		boolean result = authServer.getPassword(username);
//
//		// perform authentication
//		return result;
//	}
//
////	public User getUser(String username) {
////		// may be wrong - but good for just starting what we need to do
//////		User user = new User();
//////		user = user.getUser(username);
//////		return user;
////	}
//
//	public boolean changePassword(String username) {
//		// call the model
//		boolean result = authServer.changePassoword(username);
//
//		return result;
//	}
//
//	public boolean deleteUser(String username) {
//		// call the model
//		boolean result = authServer.deleteUser(username);
//
//		return result;
//	}
//
//}