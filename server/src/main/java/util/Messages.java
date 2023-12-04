package util;

public final class Messages {
	private Messages() {
	}

	private static String MSG_ORDER_RECIEVED = "Order is received for Product %s and Quantity %d";

	private static String MSG_ORDER_PENDING = "Order for Product %s Quantity %d is pending"
											+ " – order exceeds available quantity";

	private static String MSG_ORDER_COMPLETED = "Order is finalized for Product %s and"
											+ "Quantity %d with total price %f";

	private static String MSG_SERVER_RESTOCKING = "Restocking Operation for Product %s "
												+ "initiated";

	private static String MSG_SERVER_RESTOCKING_COMPLETED = "Restocking Operation for Product %s completed";
}
