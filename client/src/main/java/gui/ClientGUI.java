package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import http.Client;

public class ClientGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static JComboBox<String> productList;
	private static JComboBox<String> quantityList;
	private JTextArea orderDetails;
	private static String productReport = null;
	private static String quantityReport = null;
	private static String timeReport = null;

	private String selectedProduct = null;
	private String selectedQty = null;

	private static ClientGUI instance;
	private Client client = null;

	public static ClientGUI getInstance() {
		if (instance == null)
			instance = new ClientGUI();

		return instance;
	}

	private ClientGUI() {

// Set window title
		super("Product Ordering Client");

		// init our HTTP Client
		client = new Client();
		Vector<String> p = new Vector<String>();
		productList = new JComboBox<String>(p);

		// Set top bar
		JLabel step1 = new JLabel("Step1 Choose Product");
		JLabel step2 = new JLabel("Step2 Choose Quantity");

		JLabel chooseProductLabel = new JLabel(": ");

		try {
			String pName = client.getProducts();
			update(pName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel qty = new JLabel(": ");
		// JLabel to = new JLabel("To");
		Vector<String> quantity = new Vector<String>();
		for (int i = 0; i <= 1000; i = i + 50) {
			quantity.add("" + i);
		}
		quantity.remove(0);
		quantity.add(0, "1");
		quantityList = new JComboBox<String>(quantity);
		JButton addQuantity = new JButton("Choose");
		addQuantity.setActionCommand("placeOrder");
		addQuantity.addActionListener(this);

//		productList.addPopupMenuListener(this);
		productList.addActionListener(this);
		productList.setActionCommand("selectProduct");
		quantityList.setActionCommand("selectQuantity");
		quantityList.addActionListener(this);

		JPanel north = new JPanel();
		north.add(step1);
		north.add(chooseProductLabel);
		north.add(productList);
//		north.add(addProduct);
		north.add(step2);
		north.add(qty);
		north.add(quantityList);
		north.add(addQuantity);

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		// createCharts(west);

		JLabel orderDetailsLabel = new JLabel("Order Details: ");
		orderDetails = new JTextArea(30, 60);
		JScrollPane orderDetailsScrollPane = new JScrollPane(orderDetails);
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(orderDetailsLabel);
		east.add(orderDetailsScrollPane);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.WEST);

//		createCharts(west);
	}

	private void createCharts(JPanel west) {
		createReport(west);
	}

	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;

		reportMessage = "Product " + selectedProduct + "\n" + "Quantity " + selectedQty + "\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.print(command);
		
		if ("placeOrder".equals(command)) {
			if (productList.getSelectedItem() == null)
				return;

			selectedProduct = productList.getSelectedItem().toString();
			selectedQty = quantityList.getSelectedItem().toString();

			try {
				client.placeOrder(selectedProduct, Integer.parseInt(selectedQty));
			} catch (Exception ee) {
				ee.printStackTrace();
			}

		} 
	}
	public void update(String name) {
		productList.removeAllItems();
		String[] nameArray = name.split("\n");
		for (String s : nameArray) {
			int hyphenIndex = s.indexOf('-');
			String pn = s.substring(hyphenIndex + 1);
			productList.addItem(pn.trim());

		}
	}
}
