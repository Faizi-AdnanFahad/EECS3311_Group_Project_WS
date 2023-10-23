package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginGui extends JFrame implements ActionListener {
    private JLabel userLabel, passwordLabel, messageLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    
    //some colors to make the GUI nice
    
    private static final Color primary_Colour = new Color(38,37,70);
    private static final Color Secondary_Colour = new Color(255,171,63);
    private static final Color Button_Colour = new Color(0,0,0);
    

    public loginGui() {
    	
        setTitle("Server login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
       

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.setBackground(primary_Colour);

        userLabel = new JLabel("Username:");
        
        
        userLabel.setForeground(Color.white);
        
        passwordLabel = new JLabel("Password:");
        
        passwordLabel.setForeground(Color.white);
        
        messageLabel = new JLabel("");
        usernameField = new JTextField(15);
        
        usernameField.setBackground(Secondary_Colour);
     
        
        
        passwordField = new JPasswordField(15);
        
        passwordField.setBackground(Secondary_Colour);
        
        
         
        loginButton = new JButton("Login");  

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
    
    
    //the action listener to demonstrate the GUI for now

    public void actionPerformed(ActionEvent e) {
    	
        String username = usernameField.getText();
        
        char[] password = passwordField.getPassword();
        
// for now we have no connection to the login database so I just passed 'username' and 'password' to test the fields.
        
        if (username.equals("username") && String.valueOf(password).equals("password")) {
            messageLabel.setText("Login successful!");
        } else {
            messageLabel.setText("Login failed. Please try again.");
        }
    }
    
  

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            loginGui loginGUI = new loginGui();
            loginGUI.setVisible(true);
        });
    }
}