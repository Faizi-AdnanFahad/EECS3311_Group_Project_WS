package frontend.server;

import middleware.jobs.OrderProcessorFacade;

public interface IServerOperation {
	public void getProducts();
	public void Init(OrderProcessorFacade opf);
	public void setSystemState();
}
