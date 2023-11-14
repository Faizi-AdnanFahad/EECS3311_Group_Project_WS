package view;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	private List<IView> observerViewers;

	public Subject() {
		this.observerViewers = new ArrayList<IView>();
	}

	public void addViewers(IView view) {
		this.observerViewers.add(view);
	}

	public void removeViewers(IView view) {
		this.observerViewers.remove(view);
	}
	
	public void notifyViewers() {
		for (IView view : this.observerViewers) {
			view.update();
		}
	}
}
