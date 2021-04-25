package order.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import order.model.OrderDetails;
import order.util.DbConnection;

/**
 * Implementing CRUD operations
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 *
 */
public class OrderDetailsServiceImpl implements OrderDetailsService{

	private static Connection con;

	@Override
	public String createOrderDetails(String orderId, String paymentType, String price) {
		String output = "";
		String sqlQuery = null;
		PreparedStatement preparedStmt;
		
		try {
				
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				return "Failed to connect to the database for inserting!!!";
			}
			 
			// sql query to insert a new record to order table
			sqlQuery = " insert into it19151120db.order_detail (`order_detai_id`,`order_id`,`payment_type`,`order_price`,`total_amount`) " + " values (?, ?, ?, ?, ?)";
						
			// create a prepared statement
			preparedStmt = con.prepareStatement(sqlQuery);
			 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setInt(2, Integer.parseInt(orderId));
			
			// validate payment type
			if ( !(paymentType.equalsIgnoreCase("PAYPAL")|| paymentType.equalsIgnoreCase("CARD")) ) {
				return "Payment Type should be 'PAYPAL' or 'CARD'!";
			} else {
				preparedStmt.setString(3, paymentType);
			}
			
			// validate pattern of price -> [+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?
			String regex = "[+-]?[0-9]+(\\\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
			Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(String.valueOf(price));
	        if (matcher.find()) {
	        	preparedStmt.setDouble(4, Double.parseDouble(price));
	        } else {
	        	return "Price should be in 9999.99 format!";
	        }
			
			Double totAmount = calculateTotAmount(paymentType, price);
			preparedStmt.setDouble(5, totAmount);
			 
			// execute the statement
			preparedStmt.execute();
			 
			// database connection is going to be closed at the end of transaction
			con.close();
			 
			output = "Entered record has been successfully inserted and Your total amount is " + totAmount;
			
		} catch (Exception e) {
			 
			 output = "Failed to insert records due to an error!!";
			 System.err.println(e.getMessage());
		}
		
		return output;
	}

	// method to calculate total amount
	private Double calculateTotAmount(String paymentType, String price) {
		Double totalAmount;
		Double orderPrice = Double.parseDouble(price);
		// if payment type is PayPal 5% of discount can be received 
		if(paymentType.equalsIgnoreCase("PAYPAL")) {
			Double discount = 0.05;
			totalAmount = orderPrice - (orderPrice * discount);
		} else {
			totalAmount = orderPrice;
		}
		
		return totalAmount;
	
	}

	@Override
	public String getAllOrderDetails() {
		String output = "";
		
		try {
						
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for reading!!!"); 
			}
			 
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr><th> Order Detail ID </th>"
			 		+ "<th> Order ID </th>" 
			 		+ "<th> Payment Type </th>" 
			 		+ "<th> Order Price </th>" 
			 		+ "<th> Total Amount </th></tr>";
	
			 // sql query to retrieve the details of all orders details
			 String sqlQuery = "select * from it19151120db.order_detail";
			 
			 Statement stmt = con.createStatement();
			 
			 ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			 // iterate through the rows in the result set
			 while (rs.next()) {
				 
				 String orderDetailId = Integer.toString(rs.getInt("order_detai_id")); 
				 String orderId = Integer.toString(rs.getInt("order_id")); 
				 String paymentType = rs.getString("payment_type"); 
				 String price = Double.toString(rs.getDouble("order_price")); 
				 String totAmount = Double.toString(rs.getDouble("total_amount"));
				 
				 // Add into the html table
				 output += "<tr><td>" + orderDetailId + "</td>"; 
				 output += "<td>" + orderId + "</td>"; 
				 output += "<td>" + paymentType + "</td>"; 
				 output += "<td>" + price + "</td>"; 
				 output += "<td>" + totAmount + "</td></tr>";
			 }
			 
			 // database connection is going to be closed at the end of transaction
			 con.close();
			 
			 // Complete the html table
			 output += "</table>"; 
			
		} catch (Exception e) {
			output = "Failed to retrieve records due to an error!!"; 
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	@Override
	public String updateOrderDetails(String orderDetailId, String paymentType, String price) {
		String output = ""; 
		
		try {
						
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for updating!!!"); 
			}
			 
			// sql query to update the order details
			String sqlQuery = "UPDATE it19151120db.order_detail SET payment_type = ?, order_price = ?, total_amount = ? WHERE order_detai_id = ?"; 
			 
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
			 
			// validate payment type
			if ( !(paymentType.equalsIgnoreCase("PAYPAL")|| paymentType.equalsIgnoreCase("CARD")) ) {
				return "Payment Type should be 'PAYPAL' or 'CARD'!";
			} else {
				preparedStmt.setString(1, paymentType);
			}
			
			// validate pattern of price -> [+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?
			String regex = "[+-]?[0-9]+(\\\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
			Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(String.valueOf(price));
	        if (matcher.find()) {
	        	preparedStmt.setDouble(2, Double.parseDouble(price));
	        } else {
	        	return "Price should be in 9999.99 format!";
	        }
			
			Double totAmount = calculateTotAmount(paymentType, price);
			preparedStmt.setDouble(3, totAmount);
			preparedStmt.setInt(4, Integer.parseInt(orderDetailId)); 
			
			// execute the statement
			preparedStmt.execute();
			
			// database connection is going to be closed at the end of transaction
			con.close(); 
			
			output = "Entered record has been successfully updated and Your total amount is " + totAmount; 
			
		 } catch (Exception e) { 
			 output = "Failed to update records due to an error!!"; 
			 System.err.println(e.getMessage()); 
		 } 
		
		 return output;
	}

	@Override
	public String deleteOrderDetails(String orderDetailId) {
		String output = ""; 
		
		try {
			
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for deleting!!!"); 
			}
		 
			// sql query to delete a selected record 
			String sqlQuery = "delete from it19151120db.order_detail where order_detai_id = ?";
			
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(orderDetailId));
			
			// execute the statement
			preparedStmt.execute();
			
			// database connection is going to be closed at the end of transaction
			con.close(); 
			
			output = "Selected record has been successfully deleted!"; 
			
		} catch (Exception e) {
			output = "Failed to delete records due to an error!!"; 
			System.err.println(e.getMessage()); 
		} 
		
		return output;
	}

	@Override
	public OrderDetails getOrderDetailsById(String orderDetailId) {
		OrderDetails orderDetails = new OrderDetails();
		
		try {
			
			// get the database connection
			con = DbConnection.getConnection();
			 
			if (con == null) {
				 
				System.out.println("Failed to connect to the database for reading an order detail!!!"); 
			}
			 
			
			// sql query to retrieve the details of specific order detail by id
			String sqlQuery = "select * from it19151120db.order_detail where order_detai_id =" + orderDetailId;
			 
			Statement stmt = con.createStatement();
			 
			ResultSet rs = stmt.executeQuery(sqlQuery);
			 
			if (rs.next()) {
								 
				orderDetails.setOrderDetailId(rs.getInt("order_detai_id"));
				orderDetails.setOrderId(rs.getInt("order_id"));
				orderDetails.setPaymentType(rs.getString("payment_type"));
				orderDetails.setPrice(rs.getDouble("order_price"));
				orderDetails.setTotAmount(rs.getDouble("total_amount"));
				 
			}
			 
			// database connection is going to be closed at the end of transaction
			con.close();
			 
			
		} catch (Exception e) {
			System.out.println("Failed to retrieve record due to an error!!");  
			System.err.println(e.getMessage());
		}
		
		return orderDetails;
	}

	

	
}
