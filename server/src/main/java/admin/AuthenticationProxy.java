package admin;

public class AuthenticationProxy implements Authentication {

	public boolean authenticateUser(String username, String password) {

		Auth auth = new Auth();
		return auth.authenticateUser(username, password);
	}

}