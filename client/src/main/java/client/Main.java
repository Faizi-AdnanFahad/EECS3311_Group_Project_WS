/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package client;

import javax.swing.JFrame;

import gui.ClientGUI;
import http.Client;

public class Main {
	public static void main(String[] args) {
		JFrame frame = ClientGUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);

		Client client = new Client();
		try {
			System.out.print(client.getProducts());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}