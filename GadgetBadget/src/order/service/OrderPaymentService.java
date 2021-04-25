package order.service;

/**
 * Interface for the implementation of CRUD operations
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 */
public interface OrderPaymentService {
	
	/**
	 * Create order payment
	 * @param orderId
	 * @param paymentType
	 * @param totAmount
	 * @param buyerId
	 * @param cardNo
	 * @param paypalAccNo
	 * @return
	 */
	public String createOrderPayment(String orderId, String paymentType, String totAmount, String buyerId, String cardNo, String paypalAccNo);

}
