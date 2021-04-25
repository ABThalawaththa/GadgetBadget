package order.rest_resource;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import order.model.Order;
import order.service.OrderPaymentServiceImpl; 




/**
* Rest controller for order payment service
* 
* @author Sathsarani M.W.A.R - IT19151120
* 
*/
@Path("/order-service/order-payment")
public class OrderPaymentRestResource {
	
	OrderPaymentServiceImpl orderPaymentObj = new OrderPaymentServiceImpl();
	
	// add an order payment using form
	@POST
	@Path("/addOrderPayment") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrderPayment(@FormParam("orderId") String orderId, 
							  @FormParam("paymentType") String paymentType, 
							  @FormParam("totalAmount") String totAmount, 
							  @FormParam("buyerId") String buyerId, 
							  @FormParam("cardNo") String cardNo, 
							  @FormParam("paypalAccNo") String paypalAccNo) {
		
		
		String output = orderPaymentObj.createOrderPayment(orderId, paymentType, totAmount, buyerId, cardNo, paypalAccNo); 
		return output; 
	}

}
