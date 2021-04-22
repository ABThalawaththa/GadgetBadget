package product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductImpl implements IProduct {

	private static Connection connection = null;
	private static PreparedStatement preparedStmt = null;
	private static ResultSet rs = null;

	// DB connection method
	public Connection productDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/productservicedb", "root",
					"Asiyaamysql1");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	// Method to insert product
	public String insertProduct(String productTitle, String productDescription, String productType,
			String productCategory, int researcherId) {
		String output = "";
		try {
			connection = productDBConnection();
			if (connection == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into products "
					+ "(`productTitle`,`productDescription`,`productType`,`productCategory`,`researcherId`)"
					+ " values (?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
			preparedStmt.setInt(5, researcherId);
			// execute the statement
			preparedStmt.execute();
			connection.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Method to read all the products in the database
	public HashMap<String, Object> getAllProducts() {
		// To return product List
		List<Product> productList = new ArrayList<Product>();

		// Create Error Message
		Error em = new Error();

		// Initialize Data to send
		HashMap<String, Object> data = new HashMap<String, Object>();

		try {
			connection = productDBConnection();
			if (connection == null) {
				System.out.println("Error while connecting to the database");
				em.setErrorMessage("Error while connecting to the database");
				// Return connection error
				data.put("ConnectionError", em);
				return data;
			}

			// create a prepared statement
			String query = "select * from products";
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductTitle(rs.getString("productTitle"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setProductType(rs.getString("productType"));
				product.setProductCategory(rs.getString("productCategory"));
				product.setResercherId(rs.getInt("researcherId"));
				productList.add(product);
			}
			connection.close();
			// return product list
			data.put("ProductList", productList);
			return data;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			// return db read error
			data.put("DB Read Error", e.getMessage());
			return data;
		}
	}

	// Update details of specific product in the database
	public String updateProduct(int productId, String productTitle, String productDescription, String productType,
			String productCategory) {
		connection = productDBConnection();
		String output = "";
		if (connection == null) {
			return "Error while connecting to the database";
		}

		HashMap<String, Object> result = getSpecificProduct(productId);
		if (result.get("ProductReturned") == null) {
			return "Invalid Product ID, Update Failed";
		}

		// create a prepared statement
		String query = " update products set productTitle = ? , productDescription = ? , productType = ? , productCategory = ?  where productId = ? ";
		try {
			preparedStmt = connection.prepareStatement(query);

			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
			preparedStmt.setInt(5, productId);

			preparedStmt.executeUpdate();
			connection.close();
			output = "updated successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Read all the products belonged to specific type
	public HashMap<String, Object> getProductByType(String productType) {
		// To return product List
		List<Product> productList = new ArrayList<Product>();

		// Create Error Message
		Error em = new Error();

		// Initialize Data to send
		HashMap<String, Object> data = new HashMap<String, Object>();
		try {
			connection = productDBConnection();
			if (connection == null) {
				System.out.println("Error while connecting to the database");
				em.setErrorMessage("Error while connecting to the database");
				// Return connection error
				data.put("ConnectionError", em);
				return data;
			}

			// create a prepared statement
			String query = "select * from products where productType = ?";
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, productType);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductTitle(rs.getString("productTitle"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setProductType(rs.getString("productType"));
				product.setProductCategory(rs.getString("productCategory"));
				productList.add(product);
			}
			connection.close();
			// return product list
			data.put("ProductList", productList);
			return data;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			// return db read error
			data.put("DB Read Error", e.getMessage());
			return data;
		}
	}

	// delete a product
	public String deleteProduct(int productId) {
		String output = "";

		try {
			connection = productDBConnection();
			if (connection == null) {
				return "Error while connecting to the database for reading.";
			}

			HashMap<String, Object> result = getSpecificProduct(productId);
			if (result.get("ProductReturned") == null) {
				return "Invalid Product ID, Delete Failed";
			}

			String query = "delete from products where productId = ?";
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, productId);
			preparedStmt.executeUpdate();
			output = "deleted!";

		} catch (Exception e) {
			output = "Error while updating the items.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// get details of specific product
	@Override
	public HashMap<String, Object> getSpecificProduct(int productId) {
		// Create Error Message
		Error em = new Error();

		// Initialize Data to send
		HashMap<String, Object> data = new HashMap<String, Object>();
		try {
			Connection con = productDBConnection();
			if (connection == null) {
				System.out.println("Error while connecting to the database");
				em.setErrorMessage("Error while connecting to the database");
				data.put("ConnectionError", em);
				return data;
			}

			String query = "select * from products where productId =" + productId;
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			if (rs.next()) {

				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductTitle(rs.getString("productTitle"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setProductType(rs.getString("productType"));
				product.setProductCategory(rs.getString("productCategory"));

				data.put("ProductReturned", product);
			}
			connection.close();
			return data;

		} catch (Exception e) {
			em.setErrorMessage(e.getMessage());
			data.put("DBReadError", em);
			return data;
		}
	}
}
