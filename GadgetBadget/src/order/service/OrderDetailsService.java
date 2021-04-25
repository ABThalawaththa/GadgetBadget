package order.service;

import order.model.OrderDetails;

/**
 * Interface for the implementation of CRUD operations
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 */
public interface OrderDetailsService {
	
	public String createOrderDetails(String orderId, String paymentType, String price);
	
	public String getAllOrderDetails();
	
	public String updateOrderDetails(String orderDetailId, String paymentType, String price);
	
	public String deleteOrderDetails(String orderDetailId);
	
	public OrderDetails getOrderDetailsById(String orderDetailId);

}
