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

	// DB connection method
	public Connection productDBConnection() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productservicedb", "root", "Asiyaamysql1");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// Method to insert product
	public String insertProduct(String productTitle, String productDescription, String productType,
			String productCategory, int researcherId) {
		String output = "";
		try {
			Connection con = productDBConnection();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into products "
					+ "(`productTitle`,`productDescription`,`productType`,`productCategory`,`researcherId`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
			preparedStmt.setInt(5, researcherId);
			// execute the statement
			preparedStmt.execute();
			con.close();
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
			Connection con = productDBConnection();
			if (con == null) {
				System.out.println("Error while connecting to the database");
				em.setErrorMessage("Error while connecting to the database");
				// Return connection error
				data.put("ConnectionError", em);
				return data;
			}

			// create a prepared statement
			String query = "select * from products";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
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
			con.close();
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
		Connection con = productDBConnection();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement
		String query = " update products set productTitle = ? , productDescription = ? , productType = ? , productCategory = ?  where productId = ? ";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
			preparedStmt.setInt(5, productId);

			preparedStmt.executeUpdate();
			con.close();
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
			Connection con = productDBConnection();
			if (con == null) {
				System.out.println("Error while connecting to the database");
				em.setErrorMessage("Error while connecting to the database");
				// Return connection error
				data.put("ConnectionError", em);
				return data;
			}

			// create a prepared statement
			String query = "select * from products where productType = ?";
			PreparedStatement preparedStmt;
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, productType);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductTitle(rs.getString("productTitle"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setProductType(rs.getString("productType"));
				product.setProductCategory(rs.getString("productCategory"));
				productList.add(product);
			}
			con.close();
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
			Connection con = productDBConnection();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			String query = "delete from products where productId = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, productId);
			stmt.executeUpdate();
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
			if (con == null) {
				System.out.println("Error while connecting to the database");
				em.setErrorMessage("Error while connecting to the database");
				data.put("ConnectionError", em);
				return data;
			}

			String query = "select * from products where productId =" + productId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
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
			con.close();
			return data;

		} catch (Exception e) {
			em.setErrorMessage(e.getMessage());
			data.put("DBReadError", em);
			return data;
		}
	}
}
