package order.rest_resource;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 


import order.model.Order;
import order.service.OrderServiceImpl;
import order.util.RestClient;

/**
 * Rest controller for order service
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 */
@Path("/order-service")
public class OrderRestResource {

	OrderServiceImpl orderObj = new OrderServiceImpl();
	Order or;
	
	// get all orders and display as a table
	@GET
	@Path("/allOrders") 
	@Produces(MediaType.TEXT_HTML) 
	public String readAllOrders() { 
		return orderObj.getAllOrders(); 
	}
	
	// get details of specific order by id 
	@GET
	@Path("/readOrder/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readOrderById(@PathParam("id") int id) {
		
		// Display details of the order as a json file
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();

		Gson gson = gb.create();
		return gson.toJson(orderObj.getOrderById(id));
	}
	
	// add an order using form
	@POST
	@Path("/addOrder") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrder(@FormParam("orderDescription") String orderDesc, 
							  @FormParam("orderStatus") String orderStatus, 
							  @FormParam("productId") int productId, 
							  @FormParam("buyerId") int buyerId) {
		
		or = new Order(orderDesc, orderStatus, productId, buyerId);
		
		String output = orderObj.createOrder(or); 
		return output; 
	}
	
	
	// update an order using form file
	@PUT
	@Path("/updateOrder") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateOrder(@FormParam("orderId") int orderId,@FormParam("orderDescription") String orderDesc, 
							  @FormParam("orderStatus") String orderStatus, 
							  @FormParam("productId") int productId, 
							  @FormParam("buyerId") int buyerId) {
		
		String output = "";
		
		// check whether the id is available in the database
		Order existsOrderById = orderObj.getOrderById(orderId);
		
		// if id is not available in the database
		if (existsOrderById.getOrderId() == 0) {
			output = "Invalid order id!";
		} else {
			
			//if id is available in the database
			or = new Order(orderId, orderDesc, orderStatus, productId, buyerId);
			output = orderObj.updateOrder(or); 
			
		}
		
		return output;
	}
	
	// delete an order
	@DELETE
	@Path("/deleteOrder") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteOrder(@FormParam("orderId") String orderId) {
		String output = "";
		
		// check whether the id is available in the database
		Order existsOrderById = orderObj.getOrderById(Integer.parseInt(orderId));
		
		// if id is not available in the database
		if (existsOrderById.getOrderId() == 0) {
			output = "Invalid order id!";
		} else {
			//if id is available in the database
			output = orderObj.deleteOrder(orderId); 
		}
		
		return output;
	}
	
	// display all products that available for selling
	@GET
	@Path("/sellingProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProducts() {

		RestClient client = new RestClient();

		return client.getProducts();
	}
	

}
