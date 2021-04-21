package product.testing;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import product.model.Product;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTests {
	
	//Test Case to test GET Method
	@Test
	public void test_GET() {
		given().get("http://localhost:8080/GadgetBadget/WebApi/Products").then().statusCode(200)
				.body("ProductList.productId[0]", equalTo(1)).log().all();
	}

	//Test Case to test POST Method
	@Test
	public void test_POST() {
		Product product = new Product();
		product.setProductTitle("Test Product");
		product.setProductDescription("Test Product Description");
		product.setProductType("Selling");
		product.setProductCategory("IT");
		product.setResercherId(2);

		//request body data
		String body = String.format(
				"productTitle=%s&productDescription=%s&" + "productType=%s&productCategory=%s&researcherID=%s",
				product.getProductTitle(), product.getProductDescription(), product.getProductType(), product.getProductCategory(),Integer.toString(product.getResercherId()));

		given().with().header("Content-Type", "application/x-www-form-urlencoded").accept(ContentType.HTML).body(body)
				.when().post("http://localhost:8080/GadgetBadget/WebApi/Products").then().statusCode(200);

	}
	
	//Test case to test DELETE method
	@Test
	public void test_DELETE() {
		
		String body = String.format("productId=%s","7");
		given().with().header("Content-Type","application/x-www-form-urlencoded").accept(ContentType.HTML).body(body)
		.when().delete("http://localhost:8080/GadgetBadget/WebApi/Products").then().statusCode(200);
	}
	
	//Test case to test PUT method
	@Test
	public void test_PUT() {
		Product product = new Product();
		product.setProductTitle("Test Product update");
		product.setProductDescription("Test Product Description update");
		product.setProductType("Selling");
		product.setProductCategory("IT");
		product.setResercherId(2);

		String body = String.format(
				"productId=%s&productTitle=%s&productDescription=%s&" + "productType=%s&productCategory=%s&researcherID=%s",
				"8","Test Product update","Test Product Description update","Selling","IT","2");

		given().with().header("Content-Type", "application/x-www-form-urlencoded").accept(ContentType.HTML).body(body)
				.when().put("http://localhost:8080/GadgetBadget/WebApi/Products").then().statusCode(200);

	}

}
