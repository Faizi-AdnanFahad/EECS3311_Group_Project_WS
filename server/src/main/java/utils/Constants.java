package utils;

import web.*;
import java.awt.*;

import javax.swing.*;

import com.sun.net.httpserver.HttpHandler;

public final class Constants {
	private Constants() { } 

	public static String[] ROUTES = { 
		"/order", 
		"/products" 
	};

	public static HttpHandler[] HANDLERS = { 
		new OrderHandler(), 
		new ProductHandler() 
	};
	
	
	public static final Dimension FRAME_SIZE = new Dimension(500,200);
	public static final Dimension TEXTFIELD_SIZE = new Dimension((int)(FRAME_SIZE.width * 0.80), 50);
	public static final String loginTitle = "Server Login";
	public static final GridLayout GRID_LAYOUT = new GridLayout(3,2);
	public static final BorderLayout BORDER_LAYOUT = new BorderLayout();
	
	//Jpanel 
	public static final JPanel PANEL = new JPanel();
	
	//Username Label
	public static final JLabel USERNAME = new JLabel ("  USERNAME: ");
	
	//Password Label
	public static final JLabel PASSWORD = new JLabel ("  PASSWORD: ");
	
	//username Field
	public static final JTextField USER_TEXTFIELD = new JTextField (5);
	
	//Password Field
	public static final JPasswordField PASSWORD_TEXTFIELD = new JPasswordField (5);
	
	//Button Field
	public static final JButton LOGIN_BUTTON= new JButton ("LOGIN");
	
	//Message Label
	public static final JLabel MESSAGE_LABEL = new JLabel ("");
	
	//some colors for the GUI
	public static final Color primary_Colour = new Color(38,37,70);
	public static final Color Secondary_Colour = new Color(255,171,63);
	public static final Color Button_Colour = new Color(0,0,0);
		
		
		
	
	
	
	

}
