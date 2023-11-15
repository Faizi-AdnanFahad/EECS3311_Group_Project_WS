package view;

import model.Product;

public interface IView {
	
	public void update(Product orderedProduct, int orderedQuantity);
}
