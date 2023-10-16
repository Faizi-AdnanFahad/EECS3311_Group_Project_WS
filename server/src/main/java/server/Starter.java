package server;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import utils.AvailableProductList;
import utils.LastOrder;

public class Starter extends JFrame implements ActionListener, PopupMenuListener {

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
	private static LastOrder theLastOrder;

	public static void main(String[] args) {
		JFrame frame = Starter.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
		Server anHttpServer = new Server();

		try {
			anHttpServer.startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Starter instance;

	public static Starter getInstance() {
		if (instance == null)
			instance = new Starter();

		return instance;
	}

	private Starter() {
		// Set window title
		super("Ali's Environment");
		productData = AvailableProductList.getInstance().findAvailableProductsAndQuantities();
		theLastOrder = LastOrder.getInstance().findLastOrder();


		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));

		JPanel east = new JPanel();
		east.setLayout(new GridLayout(2, 0));

		getContentPane().add(west, BorderLayout.WEST);
		getContentPane().add(east, BorderLayout.EAST);

		createCharts(west, east);
		// Set top bar
//		JLabel step1 = new JLabel("Step1 Choose Product");
//		JLabel step2 = new JLabel("Step2 Choose Quantity");
//
//		JLabel chooseProductLabel = new JLabel(": ");
//		Vector<String> productNames = new Vector<String>();
//		productList = new JComboBox<String>(productNames);
//		productNames.add("Product1");
//		productNames.add("Product2");
//		productNames.add("Product3");
//		productNames.add("Product4");
//		productNames.add("Product5");
//		productNames.sort(null);
//
//		JButton addProduct = new JButton("Choose");
//		addProduct.setActionCommand("addProduct");
//		addProduct.addActionListener(this);
//
//		JLabel qty = new JLabel(": ");
//		// JLabel to = new JLabel("To");
//		Vector<String> quantity = new Vector<String>();
//		for (int i = 0; i <= 1000; i = i + 50) {
//			quantity.add("" + i);
//		}
//		quantity.remove(0);
//		quantity.add(0, "1");
//		quantityList = new JComboBox<String>(quantity);
//		JButton addQuantity = new JButton("Choose");
//		addQuantity.setActionCommand("placeOrder");
//		addQuantity.addActionListener(this);
//		productList.addPopupMenuListener(this);
//		productList.addActionListener(this);
//		quantityList.setActionCommand("selectQuantity");
//		quantityList.addActionListener(this);
//
//		JPanel north = new JPanel();
//		north.add(step1);
//		north.add(chooseProductLabel);
//		north.add(productList);
//		north.add(addProduct);
//		north.add(step2);
//		north.add(qty);
//		north.add(quantityList);
//		north.add(addQuantity);
//
//		JPanel east = new JPanel();
//
//		// Set charts region
//		JPanel west = new JPanel();
//		west.setLayout(new GridLayout(2, 0));
//		// createCharts(west);
//
//		JLabel orderDetailsLabel = new JLabel("Order Details: ");
//		orderDetails = new JTextArea(30, 60);
//		JScrollPane orderDetailsScrollPane = new JScrollPane(orderDetails);
//		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(orderDetailsLabel);
//		east.add(orderDetailsScrollPane);
//
//		getContentPane().add(north, BorderLayout.NORTH);
//		getContentPane().add(east, BorderLayout.EAST);
//		getContentPane().add(west, BorderLayout.WEST);
	}

	private void createCharts(JPanel west) {
		createReport(west);

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

	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage1, reportMessage2;

		reportMessage1 = "Last Order\n" + "==========================\n" + "\t";
		reportMessage1 = reportMessage1 + "Product: " + theLastOrder.getProductName() + "\n" + "\tQuantity:"
				+ theLastOrder.getQuantity() + "\n" + "\tTimeStamp:" + theLastOrder.getDate() + "\n";

		reportMessage2 = "Current Product Quantity in Watehouse\n" + "==============================\n";

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			// System.out.println("IN LOOP " + entry.getKey() + " " + entry.getValue());
			reportMessage2 = reportMessage2 + entry.getKey();
			reportMessage2 = reportMessage2 + "\n \t Quantity ==> " + entry.getValue() + "\n";

		}

		report.setText(reportMessage1 + reportMessage2);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

	private void createBar(JPanel west) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), "");

		}

		CategoryPlot plot = new CategoryPlot();
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
	// TODO Auto-generated method stub

	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Open");

	}

	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub

		System.out.println("Closed");
	}

	public void popupMenuCanceled(PopupMenuEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Cancelled");
	}

}
