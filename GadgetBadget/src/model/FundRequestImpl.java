package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class FundRequestImpl implements IFundRequestImpl{

	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_db", "root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public String readFundRequests() {
		// TODO Auto-generated method stub
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund ID</th><th>Client ID</th>" + "<th>ProductID</th>"
					+ "<th>Contact Name</th>" + "<th>Contact No</th>" + "<th>Contact Mail</th>" +
					 "<th>Message</th>" + "<th>Organization Name</th>" + "<th>Date</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from fundrequests";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundID"));
				String clientID = Integer.toString(rs.getInt("clientID"));
				String productID = Integer.toString(rs.getInt("productID"));
				String contactName = rs.getString("contactName");
				String contactNo = rs.getString("contactNo");
				String contactMail = rs.getString("contactMail");
				String message = rs.getString("message");
				String orgName = rs.getString("orgName");
				Date date = rs.getDate("date");
				// Add into the html table
				output += "<tr><td>" + fundID + "</td>";
				output += "<td>" + clientID + "</td>";
				output += "<td>" + productID + "</td>";
				output += "<td>" + contactName + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + contactMail + "</td>";
				output += "<td>" + message + "</td>";
				output += "<td>" + orgName + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='fundID' type='hidden' value='" + fundID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	@Override
	public FundRequest getFundRequest(int id) {
		// TODO Auto-generated method stub
		
		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database ");
				return null;
			}
			
			String query = "select * from fundrequests where fundID =" + id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			if(rs.next()) {
				
				FundRequest fr = new FundRequest();
				
				fr.setFundID(rs.getInt("fundID"));
				fr.setClientID(rs.getInt("clientID"));
				fr.setProductID(rs.getInt("productID"));
				fr.setContactName(rs.getString("contactName"));
				fr.setContactNo(rs.getString("contactNo"));
				fr.setContactMail(rs.getString("contactMail"));
				fr.setMessage(rs.getString("message"));
				fr.setOrgName(rs.getString("orgName"));
				fr.setDate(rs.getDate("date"));
				
				return fr;
			}
			con.close();
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public String insertItem(int clientID, int productID, String contactName, String contactNo, String contactMail,
			String message, String orgName, Date date) {
		
		// TODO Auto-generated method stub
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into fundrequests (`clientID`,`productID`,`contactName`,`contactNo`,`contactMail`,"
					+ "'message','orgName','date')"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, clientID);
			preparedStmt.setInt(2, productID);
			preparedStmt.setString(3, contactName);
			preparedStmt.setString(4, contactNo);
			preparedStmt.setString(5, contactMail);
			preparedStmt.setString(6, message);
			preparedStmt.setString(7, orgName);
			preparedStmt.setDate(8, (java.sql.Date) date);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	@Override
	public String updateItem(int fundID , int clientID, int productID, String contactName, String contactNo, String contactMail,
			String message, String orgName, Date date) {
		// TODO Auto-generated method stub
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "UPDATE fundrequests SET clientID=?,productID=?,contactName=?,contactNo=?"
					+ ",contactMail=?,message=?,orgName=?,date=?  WHERE fundID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, clientID);
			preparedStmt.setInt(2, productID);
			preparedStmt.setString(3, contactName);
			preparedStmt.setString(4, contactNo);
			preparedStmt.setString(5, contactMail);
			preparedStmt.setString(6, message);
			preparedStmt.setString(7, orgName);
			preparedStmt.setDate(8, (java.sql.Date) date);
			preparedStmt.setInt(9, fundID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}


	@Override
	public String deleteItem(int fundID) {
		// TODO Auto-generated method stub
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "delete from fundrequests where fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, fundID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
