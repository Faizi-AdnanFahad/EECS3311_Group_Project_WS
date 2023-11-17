package utils;

import web.*;

import com.sun.net.httpserver.HttpHandler;

public final class Constants {
	private Constants() { } 

	public static String[] ROUTES = { 
		"/order", 
		"/products" 
	};

	public static HttpHandler[] HANDLERS = { 
		new OrderHandler(), 
		new ProductHandler() 
	};

}
