package order.service;

import java.sql.SQLException;

import order.model.Order;

/**
 * Interface for the implementation of CRUD operations
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 */
public interface OrderService {
	
	/**
	 * Add new order
	 * @param order
	 * 
	 */
	public String createOrder(Order order);

	
	/**
	 * Get all details of the orders
	 * 
	 */
	public String getAllOrders();
	
	/**
	 * Update the relevant order
	 * @param order
	 * 
	 */
	public String updateOrder(Order order);

	/**
	 * Delete the relevant order
	 * @param orderId
	 * @throws SQLException
	 */
	public String deleteOrder(String orderId);

	/**
	 * Get details of the order by id
	 * @param orderId
	 * 
	 */
	public Order getOrderById(int orderId); 

}
