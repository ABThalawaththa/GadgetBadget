package product.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//This class handles the interservice communication 
//product service and other service
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

		WebResource webResource = client.resource(REST_URI + "/fundRequest/fund/" + productID);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		String returnString = response.getEntity(String.class);
		return returnString;

	}

}
