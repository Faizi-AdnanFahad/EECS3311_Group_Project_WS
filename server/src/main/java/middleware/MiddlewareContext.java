package middleware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiddlewareContext {
	Map<String, Middleware> middlewareMap;

	public MiddlewareContext() {
		middlewareMap = new HashMap<String, Middleware>();
	}
	
	public void register(Middleware middleware) {
		middlewareMap.put(middleware.getName(), middleware);
	}
	
	
}
