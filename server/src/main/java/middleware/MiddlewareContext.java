package middleware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiddlewareContext {
	public static MiddlewareContext instance = null;
	Map<String, Middleware> middlewareMap;

	private MiddlewareContext() {
		middlewareMap = new HashMap<String, Middleware>();
	}
	
	public static MiddlewareContext getInstance() {
		if(instance == null) {
			instance = new MiddlewareContext();
		}
		
		return instance;
	}

	public void register(Middleware middleware) {
		middlewareMap.put(middleware.getName(), middleware);
	}
	
	public void unregister(Middleware middleware) {
		Middleware m = middlewareMap.get(middleware.getName());
		if(m != null) m.disable();
	}
	
}
