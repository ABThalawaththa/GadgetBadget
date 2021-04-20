package user.service;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import user.model.UserService;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/UserService") 
public class UserService_Service 
{ 
	
 UserService service = new UserService();
 
@GET
@Path("login/{username}/{password}") 
@Produces(MediaType.TEXT_HTML) 
public String readItems(@PathParam("username") String username, 
		 @PathParam("password") String password) 
 { 
	String display="invalid";
 Boolean output =  service.validate(username, password);
 if(output == true) {
	 service.session(username);
	 display = "valid user";
	 
 }
 else {
	 display="invalid user";
 }
 return display;
 } 
@GET
@Path("/sessiondata/") 
@Produces(MediaType.TEXT_HTML) 
public String sessiondata() 
 { 
 return service.sessiondata(); 
 }

@GET
@Path("/displayreceivemessage/") 
@Produces(MediaType.TEXT_HTML) 
public String displayreceivemessage() 
 { 
 return service.displayreceivemessage(); 
 }

@GET
@Path("/displaysendmessage/") 
@Produces(MediaType.TEXT_HTML) 
public String displaysendmessage() 
 { 
 return service.displaysendmessage(); 
 }


@GET
@Path("/profile/{username}") 
@Produces(MediaType.TEXT_HTML) 
public String readItems3(@PathParam("username") String username) 
 { 
 return service.profile(username); 
 }


@POST
@Path("/reply/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertItem(@FormParam("from") String from, 
 @FormParam("to") String to, 
 @FormParam("subject") String subject, 
 @FormParam("message") String message) 
{ 
	String display="none";
 boolean output = service.insertCustomerCare(from,to,subject ,message); 
 if(output == true) {
	 display="Inserted successfully"; 
 }
 else {
	 display="Error when inserting data";
 }
 return display;
}


@POST
@Path("/register/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertItem2(
 @FormParam("name") String name, 
 @FormParam("email") String email, 
 @FormParam("phone") String phone,
 @FormParam("username") String username,
 @FormParam("password") String password,
 @FormParam("type") String type) 
{ 
	String display="none";
 boolean output = service.insertcustomer(name,email,phone ,username,password,type); 
 if(output == true) {
	 display="Registered successfully"; 
 }
 else {
	 display = "not registered successfully";
 }
 return display;
}




@PUT
@Path("/editprofile/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateItem(String itemData) 
{ 
	String display="none";
//Convert the input string to a JSON object 
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
//Read the values from the JSON object
 String id = itemObject.get("id").getAsString(); 
 String name = itemObject.get("name").getAsString(); 
 String email = itemObject.get("email").getAsString(); 
 String phone = itemObject.get("phone").getAsString(); 
 String username = itemObject.get("username").getAsString(); 
 String password = itemObject.get("password").getAsString(); 
 String type = itemObject.get("type").getAsString();
 Boolean output = service.updatecustomer(id,name, email, phone,username, password,type);
 if(output == true) {
	 display = "updated successfully";
 }
 else {
	 display = "not updated successfully";
 }
return display; 
}


@DELETE
@Path("/deletemsg/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteItem(String itemData) 
{ 
	String display="no value";
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String id = doc.select("id").text(); 
 boolean output = service.deletemessage(id); 
 if(output == true) {
	 display = "Deleted Sucessfully";
 }
 else {
	 display="not deleted successfully";
 }
return display; 
}



}
