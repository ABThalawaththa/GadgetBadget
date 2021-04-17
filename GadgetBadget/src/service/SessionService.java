package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.GsonBuilder;

import model.Session;

@Path("/session")
public class SessionService {

	private Session session = null;
	
	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createSession(@FormParam("userName") int userName, @FormParam("password") int password) {
		
		
		if(session != null) {
			
		//check user authentication
		//-----------------------------------
		
		
		
		//if user exists
		//session = new Session(userName,userID);
		
		return "Logged in successfully";
		}
		else {
			return "user already logged in";
		}
	}
	
	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_PLAIN)
	public String destroySession() {
		
		
		//if session exists
		if(session != null) {
			
			session = null;
			return "Logged out successfully";
		}
		else {
			return "Please Login !!!";
		
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSession() {
		
		
		//if session exists
		if(session != null) {
			
			GsonBuilder gb = new GsonBuilder();
			gb.setPrettyPrinting();
			return gb.create().toJson(session);
			
		}
		else {
			return "No user available !!!";
		
		}
	}
	
	
	
}
