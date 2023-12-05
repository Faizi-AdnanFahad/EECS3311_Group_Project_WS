package util;

public final class Messages {
	private Messages() {
	}

	public static String MSG_ORDER_RECIEVED = "Order is received for Product %s and Quantity %d \n";

	public static String MSG_ORDER_PENDING = "Order for Product %s Quantity %d is pending"
											+ " – order exceeds available quantity \n";

	public static String MSG_ORDER_COMPLETED = "Order is finalized for Product %s and"
											+ " Quantity %d with total price %.2f \n";

	public static String MSG_SERVER_RESTOCKING = "Restocking Operation for Product %s "
												+ "initiated \n";

	public static String MSG_SERVER_RESTOCKING_COMPLETED = "Restocking Operation for Product %s completed \n";

	public static String MSG_SERVER_EXCEED_MAX = "Order exceeds the max quantity set for this product and cannot be processed\n";
	
	public static String MSG_SERVER_BELOW_MIN = "Product quantity fell below the target min quantity - Low Stock";


}
