package httpServerComponent;

public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Server anHttpServer = new Server();
		
		try {
			anHttpServer.startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
