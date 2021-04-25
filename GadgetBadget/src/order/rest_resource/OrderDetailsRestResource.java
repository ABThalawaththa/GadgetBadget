package order.rest_resource;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import order.model.OrderDetails;
import order.service.OrderDetailsServiceImpl; 

/**
 * Rest controller for order detail service
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 */
@Path("/order-service/order-detail")
public class OrderDetailsRestResource {
	OrderDetailsServiceImpl orderDetailsObj = new OrderDetailsServiceImpl();
	
	// get all order details and display as a table
	@GET
	@Path("/allOrderDetails") 
	@Produces(MediaType.TEXT_HTML) 
	public String readAllOrderDetails() { 
		return orderDetailsObj.getAllOrderDetails();
	}
	
	// get details of specific order detail by id 
	@GET
	@Path("/readOrderDetails/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readOrderDetailsById(@PathParam("id") String id) {
		
		// Display details of the order detail as a json file
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();

		Gson gson = gb.create();
		return gson.toJson(orderDetailsObj.getOrderDetailsById(id));
	}
		
	// add order details using form
	@POST
	@Path("/addOrderDetails") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrder(@FormParam("orderId") String orderId, @FormParam("paymentType") String paymentType,  
			@FormParam("price") String price) {
		
		String output = orderDetailsObj.createOrderDetails(orderId, paymentType, price); 
		return output; 
	}
	
	// update order details using form file
	@PUT
	@Path("/updateOrderDetails") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateOrder(@FormParam("orderDetailsId") String orderDetailsId, @FormParam("paymentType") String paymentType, 
			@FormParam("price") String price) {
		
		String output = "";
		
		// check whether the id is available in the database
		OrderDetails existsOrderDetailsById = orderDetailsObj.getOrderDetailsById(orderDetailsId);
		
		// if id is not available in the database
		if (existsOrderDetailsById.getOrderDetailId() == 0) {
			output = "Invalid order details id!";
		} else {
			
			//if id is available in the database
			output = orderDetailsObj.updateOrderDetails(orderDetailsId, paymentType, price);
			
		}
		
		return output;
	}
		
	// delete an order detail
	@DELETE
	@Path("/deleteOrderDetails") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteOrder(@FormParam("orderDetailsId") String orderDetailsId) {
		String output = "";
		
		// check whether the id is available in the database
		OrderDetails existsOrderDetailsById = orderDetailsObj.getOrderDetailsById(orderDetailsId);
		
		// if id is not available in the database
		if (existsOrderDetailsById.getOrderId() == 0) {
			output = "Invalid order details id!";
		} else {
			//if id is available in the database
			output = orderDetailsObj.deleteOrderDetails(orderDetailsId); 
		}
		
		return output;
	}

}
