package order.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import order.util.DbConnection;

/**
 * Implementing CRUD operations
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 *
 */
public class OrderPaymentServiceImpl implements OrderPaymentService{
	private static Connection con;

	// method to add order payment
	@Override
	public String createOrderPayment(String orderId, String paymentType, String totAmount, String buyerId,
			String cardNo, String paypalAccNo) {
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
			sqlQuery = " insert into it19151120db.order_payments (`order_paymentsId`,`order_id`,`payment_type`,`total_amount`,`buyer_id`,`payment_status`,`paid_date`,`card_no`,`paypal_acc_no`) " + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
						
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
			
			// validate pattern of total amount -> [+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?
			String regex = "[+-]?[0-9]+(\\\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
			Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(String.valueOf(totAmount));
	        if (matcher.find()) {
	        	preparedStmt.setDouble(4, Double.parseDouble(totAmount));
	        } else {
	        	return "Total amount should be in 9999.99 format!";
	        }
	        
			preparedStmt.setInt(5, Integer.parseInt(buyerId));
			preparedStmt.setString(6, "PAID");
			preparedStmt.setDate(7, Date.valueOf(LocalDate.now())); /* get the current system date as paid date */
			preparedStmt.setString(8, cardNo);
			preparedStmt.setString(9, paypalAccNo);
			
			// execute the statement
			preparedStmt.execute();
			 
			// database connection is going to be closed at the end of transaction
			con.close();
			 
			output = "Payment has been successfully inserted!";
			
		} catch (Exception e) {
			 
			 output = "Failed to insert records due to an error!!";
			 System.err.println(e.getMessage());
		}
		
		return output;
	}

}
