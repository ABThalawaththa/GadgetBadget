package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.FundRequest;
import model.FundRequestImpl;


@Path("/fundRequest")
public class FundRequestService {

	FundRequestImpl fr = new FundRequestImpl();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRequests() {
		return fr.readFundRequests();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRequest(@FormParam("clientID") int clientID, @FormParam("productID") int productID,
			@FormParam("contactName") String contactName, @FormParam("contactNo") String contactNo, 
			@FormParam("contactMail") String contactMail, @FormParam("message") String message, 
			@FormParam("orgName") String orgName, @FormParam("date") Date date) 
	{
		String output = fr.insertRequest(clientID,productID,contactName,
				contactNo, contactMail ,message, orgName, date);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRequest(String data) throws ParseException {
		
		// Convert the input string to a JSON object
		JsonObject jObject = new JsonParser().parse(data).getAsJsonObject();
		
		// Read the values from the JSON object
		int fundID = jObject.get("fundID").getAsInt();
		int clientID = jObject.get("clientID").getAsInt();
		int productID = jObject.get("productID").getAsInt();
		String contactName = jObject.get("contactName").getAsString();
		String contactNo = jObject.get("contactNo").getAsString();
		String contactMail = jObject.get("contactMail").getAsString();
		String message = jObject.get("message").getAsString();
		String orgName = jObject.get("orgName").getAsString();
		String jDate = jObject.get("date").getAsString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(jDate);
		
		String output = fr.updateRequest(fundID , clientID, productID, contactName, contactNo, contactMail,
				message, orgName, date);
		return output;
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteRequest(String data) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(data, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 int fundID = Integer.parseInt(doc.select("fundID").text()); 
	 String output = fr.deleteRequest(fundID); 
	return output; 
	}
	
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML})
	public FundRequest getRequest(@PathParam("id") int id) {
		return fr.getFundRequest(id);
	}
}
