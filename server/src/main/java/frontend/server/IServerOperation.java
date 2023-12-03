package frontend.server;

import java.util.Map;

import middleware.jobs.OrderProcessorFacade;

public interface IServerOperation {
	public Map<String, Integer> getProducts();
	public void Init(OrderProcessorFacade opf);
	public void setSystemState();
}
