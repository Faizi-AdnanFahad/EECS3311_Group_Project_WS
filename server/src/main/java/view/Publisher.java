package view;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public abstract class Publisher {
	private List<IView> observerViewers;

	public Publisher() {
		this.observerViewers = new ArrayList<IView>();
	}

	public void addViewers(IView view) {
		this.observerViewers.add(view);
	}

	public void removeViewers(IView view) {
		this.observerViewers.remove(view);
	}
	
	public void notifyViewers(Product orderedProduct, int orderedQuantity) {
		for (IView view : this.observerViewers) {
			view.update(orderedProduct, orderedQuantity);
		}
	}
	
	public void resetViewers() {
		this.observerViewers.clear();
	}
}
