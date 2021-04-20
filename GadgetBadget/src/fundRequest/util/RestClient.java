package fundRequest.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class RestClient {
	
	//
	//url to access other services
	
	private static final String REST_URI = "http://localhost:8081/GadgetBadget/WebApi";
	private Client client;

	public RestClient() {
		super();

		client = Client.create();

	}

	//
	//get the session from user management service
	//
	public String getSession() {

		WebResource webResource = client.resource(REST_URI + "/UserService/sessiondata/");

        ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
        
		return response.getEntity(String.class);
		
	}
	
	
	

}

