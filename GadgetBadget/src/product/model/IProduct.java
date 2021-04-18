package product.model;

import java.sql.Connection;
import java.util.List;

public interface IProduct {
	public Connection productDBConnection();
	public String insertProduct(String productTitle,String productDescription,
			String productType,String productCategory, int researcherId);
	public List<Product> getAllProducts();
	public String updateProduct(int productId,String productTitle,String productDescription,
			String productType,String productCategory);
	
	public List<Product> getProductByType(String productType);
	public String deleteProduct(int productId);

}
