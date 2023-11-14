package view;

public class ReportView implements IView {

	public ReportView(Subject sub) {
		sub.addViewers(this);
	}

	public void update() {
		System.out.println("Report View updated!");
	}

}
