package product.service;

import java.util.ArrayList;
import java.util.List;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import product.model.IProduct;
import product.model.ProductImpl;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Products")
public class ProductService {
	
	IProduct iproduct = new ProductImpl();
	ProductInterService productinterservice = new ProductInterService();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProducts() {
		
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		
		Gson gson = gb.create();
		return gson.toJson(iproduct.getAllProducts());
	}
	
	@GET
	@Path("Specific/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSpecificProduct(@PathParam ("productId") int productId) {
		
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		
		Gson gson = gb.create();
		return gson.toJson(iproduct.getSpecificProduct(productId));
	}
	
	@GET
	@Path("/{productType}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductByType(@PathParam ("productType") String productType) {
		
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		
		Gson gson = gb.create();
		return gson.toJson(iproduct.getProductByType(productType));
	}
	
	@GET
	@Path("/{productId}/fundingRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductByType(@PathParam ("productId") int productID) {
		return productinterservice.getAllResquestForProduct(productID);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertProduct(@FormParam("productTitle") String productTitle, @FormParam("productDescription") String productDescription,
			@FormParam("productType") String productType, @FormParam("productCategory") String productCategory, 
			@FormParam("researcherID") int researcherID) {
		String output = iproduct.insertProduct(productTitle, productDescription, productType, productCategory,researcherID);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String updateProduct(@FormParam("productId") int productId,@FormParam("productTitle") String productTitle, @FormParam("productDescription") String productDescription,
			@FormParam("productType") String productType, @FormParam("productCategory") String productCategory) {
		String output = iproduct.updateProduct(productId,productTitle, productDescription, productType, productCategory);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String deleteProduct(@FormParam("productId") int productId) {
		String output = iproduct.deleteProduct(productId);
		return output;
	}

}
