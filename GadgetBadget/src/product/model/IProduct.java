package product.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public interface IProduct {
	public Connection productDBConnection();
	public String insertProduct(String productTitle,String productDescription,
			String productType,String productCategory, int researcherId);
	public HashMap<String,Object> getAllProducts();
	public String updateProduct(int productId,String productTitle,String productDescription,
			String productType,String productCategory);
	
	public HashMap<String,Object> getProductByType(String productType);
	public String deleteProduct(int productId);
	public HashMap<String,Object> getSpecificProduct(int productId);

}
