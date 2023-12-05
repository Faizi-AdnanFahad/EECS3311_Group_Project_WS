package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import middleware.wares.ServerOperation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 


public class ServerGUI extends JFrame implements ActionListener, PopupMenuListener {

	private static final long serialVersionUID = 1L;
	private static JComboBox<String> productList;
	private static JComboBox<String> quantityList;
	private static String theProduct;
	private static String theQuantity;
	private JTextArea orderDetails;
	private static String productReport = null;
	private static String quantityReport = null;
	private static String timeReport = null;
	private static Map<String, Integer> productData;
	

	
	private JPanel west = new JPanel();
	private JPanel east = new JPanel();
	private JTextArea report = new JTextArea();
	private CategoryPlot plot = new CategoryPlot();
	private JTextArea message = new JTextArea();

	/*
	 * stores the content for the report view as two parts. Part 1 (index 0) stores
	 * the Last Order info and Part 2 (Index 1)stores the info about current product
	 * stocks in db.
	 */
	private String[] reportViewContect = new String[2];

	private static ServerGUI instance;

	/* Singleton Design Pattern */
	public static ServerGUI getInstance() {
		if (instance == null)
			instance = new ServerGUI();

		return instance;
	}

	private ServerGUI() {
		// Set window title
		
		super("Warehouse Back-end Stock");
		reportViewContect[0] = "No Last Order \n" + "==============================\n";
		reportViewContect[1] = "";

		// get data from the database
		productData = this.getDataFromDB();

		// Set charts region
		west.setLayout(new GridLayout(2, 0));
		east.setLayout(new GridLayout(2, 0));

		getContentPane().add(west, BorderLayout.WEST);
		getContentPane().add(east, BorderLayout.EAST);

		// create views
		createCharts(west, east);

		createOrderDetailsSection();

		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.WEST);
		
	}

	/*
	 * the section used to represent messages shows in the server GUI during order
	 * processing.
	 */
	private void createOrderDetailsSection() {
		JLabel orderDetailsLabel = new JLabel("Order Details: ");
		orderDetails = new JTextArea(30, 60);
		JScrollPane orderDetailsScrollPane = new JScrollPane(orderDetails);
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(orderDetailsLabel);
		east.add(orderDetailsScrollPane);
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.print(command);

		if ("addProduct".equals(command)) {
			/*
			 * stats.removeAll(); DataVisualizationCreator creator = new
			 * DataVisualizationCreator(); creator.createCharts();
			 */
			if (productList.getSelectedItem() == null)
				return;
			theProduct = productList.getSelectedItem().toString();
			productReport = "Product : " + theProduct + "\n";

		} else if ("addQuantity".equals(command)) {
			// selectedList.add(cryptoList.getSelectedItem().toString());
			theQuantity = quantityList.getSelectedItem().toString();
			quantityReport = "Quantity : " + theQuantity + "\n";
			timeReport = "Client Time Stamp : " + java.time.LocalDateTime.now().toString() + "\n";

			orderDetails.setText(productReport + quantityReport + timeReport + "\n");
		}
	}

	private void createCharts(JPanel west, JPanel east) {
		createBar(west);
		createReport(east);
	}

	/*
	 * Creates the Report View on the
	 */
	private void createReport(JPanel east) {
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 500));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);

		// String messages to be displayed in the report view
		String reportMessage1;
		StringBuilder reportMessage2 = new StringBuilder();
		
		


		
	
	
		// get information for the second part of the content
		reportMessage2.append("Current Product Quantity in Watehouse\n" + "==============================\n");
		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			reportMessage2.append(entry.getKey());
			reportMessage2.append("\n \t Quantity ==> " + entry.getValue() + "\n");
		}
		reportViewContect[1] = reportMessage2.toString();

		report.setText(reportViewContect[0] + reportViewContect[1]);

		JScrollPane outputScrollPane = new JScrollPane(report);
		east.add(outputScrollPane);
	}

	/*
	 * Upon call, can update the report section with the latest database stock
	 * quantity and products.
	 */
	public void updateReportData() {
		// get the latest data from the database
		productData = this.getDataFromDB();

		StringBuilder reportMessage2 = new StringBuilder();
		reportMessage2.append("Current Product Quantity in Warehouse\n" + "==============================\n");
		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			reportMessage2.append(entry.getKey());
			reportMessage2.append("\n \t Quantity ==> " + entry.getValue() + "\n");
		}
		reportViewContect[1] = reportMessage2.toString();

		// update the data
		report.setText(reportViewContect[0] + reportViewContect[1]);
	}

	private void createBar(JPanel west) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), "");
		}

		BarRenderer barrenderer1 = new BarRenderer();
		BarRenderer barrenderer2 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setRenderer(1, barrenderer2);

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		JFreeChart barChart = new JFreeChart("Warehouse Product Monitor System",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

	/*
	 * Upon call, can update the bar chart with the latest database stock quantity
	 * and products.
	 */
	public void updateBar() {
		// get the latest data from the database
		productData = this.getDataFromDB();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), "");
		}
		plot.setDataset(0, dataset);
	}

	/*
	 * Retrieve up to date data to be displayed in the server GUI
	 */
	private Map<String, Integer> getDataFromDB() {
		return ServerOperation.getInstance().getProducts();
	}

	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		System.out.println("Open");

	}

	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		System.out.println("Closed");
	}

	public void popupMenuCanceled(PopupMenuEvent e) {
		System.out.println("Cancelled");
	}

	public void stateMessage(String m) {
	
		orderDetails.setText(m);
		orderDetails.updateUI();
	}
	
	public void populateLastOrder(String prodName, int prodQuantity) {
		LocalDateTime date = java.time.LocalDateTime.now();
		
	   String	reportMessage1 = "Last Order\n" + "==========================\n" + "\t";		
		reportMessage1 +=  "Product: " + prodName + "\n" + "\tQuantity:" + prodQuantity + "\n" + "\tTime: " +  date + "\n";
		reportMessage1 += "==========================\n";
		reportViewContect[0] = reportMessage1;
		
	}

	public void updateMessage(String message) {
		orderDetails.setText(orderDetails.getText() + "\n" + message);
		orderDetails.updateUI();
	}
}
