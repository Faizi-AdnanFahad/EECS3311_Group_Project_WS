package frontend.server;

import java.util.Map;

public interface IServerOperation {
	public Map<String, Integer> getProducts();
	public void Init();
}
