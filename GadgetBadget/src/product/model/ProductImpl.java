package product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProduct {
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
	
	public String insertProduct(String productTitle,String productDescription,
			String productType,String productCategory) {
		String output = "";
		try {
			Connection con = productDBConnection();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into products " + "(`productTitle`,`productDescription`,`productType`,`productCategory`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, productTitle);
			preparedStmt.setString(2, productDescription);
			preparedStmt.setString(3, productType);
			preparedStmt.setString(4, productCategory);
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
	
	public List<Product> getAllProducts(){
		List<Product> productList = new ArrayList<Product>();
		try {
			Connection con = productDBConnection();
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
				productList.add(product);
			}
			con.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return productList;
	}
	
	public String updateProduct(int productId,String productTitle,String productDescription,
			String productType,String productCategory) {
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
		// binding values

		return output;
	}
	
	public List<Product> getProductByType(String productType) {
		List<Product> productList = new ArrayList<Product>();
		try {
			Connection con = productDBConnection();
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
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return productList;
		
	}
	
	public String deleteItem(int productId) {
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
}