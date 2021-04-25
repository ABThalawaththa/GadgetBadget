package product.service;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

/**
 * @author IT19240602
 *
 */

//This class handles the interservice communication 
//product service and other service(fund request service / order service)

public class ProductInterService {
	private static final String REST_URI = "http://localhost:8080/GadgetBadget/WebApi";
	
	private Client client;

	public ProductInterService() {
		super();

		client = Client.create();
	}

	// get all the funding requests for a particular product from fundRequest
	// service
	public String getAllResquestForProduct(int productID) {

		// particular url of fund request service
		WebResource webResource = client.resource(REST_URI + "/fundRequest/fund/" + productID);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		String returnString = response.getEntity(String.class);
		return returnString;

	}
	
	// get all the orders belonged to a particular product from Orders service
	public String getAllOrdersForProduct(int productID) {

		//particular url of order service
		WebResource webResource = client.resource(REST_URI + "/order-service/readOrdersByProject/" + productID);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		String returnString = response.getEntity(String.class);
		return returnString;

	}
	
	// orderID and order status send to Order Service to update the order status 
	public String sendOrderStatusDetails(int orderID, String orderStatus) {
		// Input data setting
		Form input = new Form();
		input.add("orderId", String.valueOf(orderID));
		input.add("orderStatus",orderStatus );

		// Particular url of order service to update order status
		WebResource webResource = client.resource(REST_URI + "/order-service/updateOrderStatus");

		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).accept("application/json").put(ClientResponse.class,input);
		String returnString = response.getEntity(String.class);
		return returnString;

	}


}
