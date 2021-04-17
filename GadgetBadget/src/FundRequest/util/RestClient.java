package util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class RestClient {

	 private static final String REST_URI 
     = "http://localhost:8081/GadgetBadget/WebApi";
	 
	 private Client client;
	
	public RestClient() {
		super();
		
		client = ClientBuilder.newClient();

	}
	
	public String getProduct(int id) {
		
        return client
          .target(REST_URI)
          .path(String.valueOf(id))
          .request(MediaType.APPLICATION_JSON)
          .header("Content-type", "application/json")
          .get(String.class);
    }

}
