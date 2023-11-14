package view;

public class BarChartView implements IView {

	public BarChartView(Subject sub) {
		sub.addViewers(this);
	}

	public void update() {
		System.out.println("Bar Chart View updated!");
	}

}
