package utils;

import frontend.web.*;

import com.sun.net.httpserver.HttpHandler;

import frontend.web.OrderHandler;
import frontend.web.ProductHandler;

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
