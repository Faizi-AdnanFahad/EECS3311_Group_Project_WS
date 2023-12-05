package middleware.orderstate;

import model.Order;
import util.Messages;
import gui.ServerGUI;

public class OrderedQntyGTTargetMaxQntyState implements IOrderState {

	public void processOrder(Order order) {

		sendMessage(order);
	}

	public void sendMessage(Order order) {

		ServerGUI serverGUI = ServerGUI.getInstance();

		System.out.println("------------------------------");
		System.out.println(Messages.MSG_SERVER_EXCEED_MAX);
		System.out.println("------------------------------");

		serverGUI.stateMessage(Messages.MSG_SERVER_EXCEED_MAX);

	}

}
