import java.util.HashMap;
import java.util.Map;

import product.model.IProduct;
import product.model.Product;
import product.model.ProductImpl;

public class ProductTesting {

	public static void main(String[] args) {
		IProduct iproduct = new ProductImpl();
//		HashMap<String,Object> hash = iproduct.getSpecificProduct(88);
//		if(hash.get("ProductReturned") == null) {
//			System.out.print("Fuck you");
//		}
//		Product product = (Product) hash.get("ProductReturned");
//		System.out.print(product.getProductId());
//		System.out.print(product.getProductTitle());
		Map<String,Object> hashmap = iproduct.getProductsOfResearcher(3);
		if(hashmap.containsKey("ProductList")) {
			System.out.print("okey");
		}

	}

}
