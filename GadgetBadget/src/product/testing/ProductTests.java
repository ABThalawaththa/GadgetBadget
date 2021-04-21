package product.testing;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;




public class ProductTests {
	
	@Test
	public void test_GET() {
		given().get("http://localhost:8080/GadgetBadget/WebApi/Products").then().
		statusCode(200).
		body("ProductList.productId[0]",equalTo(1)).
		log().all();
		
	}

}
