package gui;
import admin.AuthenticationProxy;

import javax.swing.*;

import utils.Constants;
import web.Server;

import java.awt.*;
import java.awt.event.*;

import admin.Auth;

public class LoginGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
	private JLabel userLabel, passwordLabel, messageLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    private static LoginGUI instance;
    
    
    public static LoginGUI getInstance() {
    	
  		if (instance == null)
  			instance = new LoginGUI();

  		return instance;
      }
    

    public LoginGUI() {
    	
        setTitle(Constants.loginTitle);
        setSize(Constants.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(Constants.BORDER_LAYOUT);
        
        JPanel inputPanel = Constants.PANEL;
        inputPanel.setLayout(Constants.GRID_LAYOUT);
        inputPanel.setBackground(Constants.primary_Colour);
        
        userLabel = Constants.USERNAME;
        userLabel.setForeground(Color.white);
        
        passwordLabel = Constants.PASSWORD;
        passwordLabel.setForeground(Color.white);
        
        messageLabel = Constants.MESSAGE_LABEL;
        
        usernameField = Constants.USER_TEXTFIELD;
        usernameField.setBackground(Constants.Secondary_Colour);
        
        passwordField = Constants.PASSWORD_TEXTFIELD;
        passwordField.setBackground(Constants.Secondary_Colour);
        
        loginButton = Constants.LOGIN_BUTTON;  

        //add to panel
        inputPanel.add(userLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        
        //this is just for spacing
        inputPanel.add(new JLabel("")); 
        
        inputPanel.add(loginButton);

        add(inputPanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        loginButton.addActionListener(this);
       
        
    }
    
  

	public void actionPerformed(ActionEvent e) {
		
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        AuthenticationProxy a = new AuthenticationProxy();

        boolean isAuthenticated = a.authenticateUser(username, password);

        if (isAuthenticated) {
        	messageLabel.setText("Login Successful");
        	
        	JFrame frame = ServerGUI.getInstance();
    		frame.setSize(900, 600);
    		frame.pack();
    		frame.setVisible(true);
    		Server http = new Server();
    		try {
				http.start();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    		 this.dispose();

        } else {
        	
        	messageLabel.setText("login failed, Please try again.");
        }

		
	}

	
    
    

}