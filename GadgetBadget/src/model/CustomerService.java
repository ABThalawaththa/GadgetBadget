package model;
//IT19058160
//name : W.M.C.S Bandara
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




import util.Helper;


public class CustomerService implements ICustomer{//public class name CustomerService which implements ICustomer
	//declaring
	//public static final Logger log = Logger.getLogger(AppProperties.class.getName());
	private static boolean isSuccess;

	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement pmt=null;
	
	private static Connection con=null;
	

	
	public static Connection getConnecton() {
		//implement methods
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assigment", "root", ""); 
			//For testing
			System.out.print("Successfully connected"); 
			
			
		}
		catch(Exception e)
		{
			System.out.println("Database connection is not success!");
			//log.log(Level.SEVERE, e.getMessage());
		}
		return con;
	}
	
	
    public boolean validatee(String username,String password) {
    	try {
    		con=getConnecton();//database connection
    		stmt=con.createStatement();
    		String sql = "select * from customer where username='"+username+"' and password='"+password+"'";
    		rs=stmt.executeQuery(sql);
    		if(rs.next())//if condition
    		{
    			isSuccess=true;
    		}
    		else {
    			isSuccess=false;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//log.log(Level.SEVERE, e.getMessage());
    	}
    	return isSuccess;//return values
    }
    public List<Customer> getDetails(String userName) {
	//impmentation of method
	ArrayList<Customer> cus = new ArrayList<>();
	
	try { 
		//database connection
	    con = getConnecton();
	    stmt = con.createStatement();
	    String sql = "select * from customer where username='"+userName+"' ";	    
	    rs = stmt.executeQuery(sql);
	    
	    if(rs.next()) {//if condition
		int id = rs.getInt(1);
		String name = rs.getString(2);
		String email = rs.getString(3);
		String phone = rs.getString(4);
		String userU = rs.getString(5);
		String passU = rs.getString(6);
		String type = rs.getString(7);
		Customer c = new Customer(id,name,email,phone,userU,passU, type);//declaring object
		cus.add(c);
		
	    }
	    
	}
	catch(Exception e) {
	    e.printStackTrace();
	    //log.log(Level.SEVERE, e.getMessage());
	}
	
	return cus;
	
    }
    public boolean insertCustomerCare(String from,String to,String subject ,String message){
    	//method implementation
    	
    	
    	String id = Helper.generateStudentIDs(getStudentId());
    	
		boolean isSuccess=false;
		
		
		//check if there are any errors
		try {
			//database connection
			con = getConnecton();
    		stmt = con.createStatement();
			String sql = "insert into sendmessage values('"+id+"','"+from+"' , '"+to+"','"+subject+"','"+message+"')";
			int rs=stmt.executeUpdate(sql);
			
			if(rs > 0) {//if condition
				isSuccess = true;
				
			}
			else {
				isSuccess = false;
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//log.log(Level.SEVERE, e.getMessage());
		}
		
		return isSuccess;
		
	}
    
    private ArrayList<String> getStudentId() {

    	ArrayList<String> arrayLst1= new ArrayList<String>();//declearing
    	 
    	try {
    		 con = getConnecton();//Database connection
    	
    		 pmt=con.prepareStatement("select id from sendmessage");
    		 ResultSet rs = pmt.executeQuery();
    		 
    		 while(rs.next())//while loop
    		 {
    			 arrayLst1.add(rs.getString("id"));
    		 }
    	}catch(SQLException e) {
    		//log.log(Level.SEVERE, e.getMessage());
    	}
    	finally 
    	{
    		try 
    		{
    			if(pmt != null)
    			 {
    				 pmt.close();
    			 }
    			 if(con != null)
    			 {
    				 con.close();
    			 }
    		}
    		catch(SQLException e)
    		{
    			//log.log(Level.SEVERE, e.getMessage());
    		}
    	}
    	return arrayLst1;//return value
    }
    	 
    
  
    
    public boolean insertcustomer(String name, String email, String phone, String username, String password,String type) {
    	//method implementation
    	boolean isSuccess = false;
    	
    	try {
    		//database connection
    		con = getConnecton();
    		stmt = con.createStatement();
    	    String sql = "insert into customer values (0,'"+name+"','"+email+"','"+phone+"','"+username+"','"+password+"','"+type+"')";
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {//if condition
    			isSuccess = true;
    		} 
    		else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		//log.log(Level.SEVERE, e.getMessage());
    	}
 	
    	return isSuccess;
    }
    
    public boolean updatecustomer(String id, String name, String email, String phone, String username, String password,String type) {
    	//method implemenation
    	try {
    		//database connection
    		con = getConnecton();
    		stmt = con.createStatement();
    		String sql = "update customer set name='"+name+"',email='"+email+"',phone='"+phone+"',username='"+username+"',password='"+password+"',type='"+type+"'"
    				+ "where id='"+id+"'";
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {
    			isSuccess = true;
    		}
    		else {
    			isSuccess = false;
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		//log.log(Level.SEVERE, e.getMessage());
    	}
    	
    	return isSuccess;
    }
 
    public  List<Customer> getCustomerDetails(String Id) {
    	//method implemetation
    	int convertedID = Integer.parseInt(Id);//convert string to integer
    	
    	ArrayList<Customer> cus = new ArrayList<>();
    	
    	try {//try catch block
    		//connect with database
    		con = getConnecton();
    		stmt = con.createStatement();
    		String sql = "select * from customer where id='"+convertedID+"'";
    		rs = stmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			int id = rs.getInt(1);
    			String name = rs.getString(2);
    			String email = rs.getString(3);
    			String phone = rs.getString(4);
    			String username = rs.getString(5);
    			String password = rs.getString(6);
    			String type = rs.getString(7);
    			Customer c = new Customer(id,name,email,phone,username,password, type);
    			cus.add(c);
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		//log.log(Level.SEVERE, e.getMessage());
    	}	
    	return cus;	
    }
	public boolean deletemessage(String id) {
		//method implementation
		int idd = Integer.parseInt(id);//convert string to int
		boolean isSuccess = false;
    	
    	try {
    		con = getConnecton();
    		stmt = con.createStatement();
    		String sql = "delete from receivemessage where id='"+idd+"'";
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {
    			isSuccess = true;
    		} 
    		else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		//log.log(Level.SEVERE, e.getMessage());
    	}
 	
    	return isSuccess;
	}
	
	
	
	public String readItems() 
	 { 
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
 		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>#</th><th>Name</th><th>Subject</th>" +
	 "<th>Designation</th>" + 
	 "<th>Email</th>" +
	 "<th>Message</th></tr>"; 
	 
	 String query = "select * from receivemessage"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String subject = rs.getString("subject"); 
	 String designation = rs.getString("designation"); 
	 String email = rs.getString("email"); 
	 String message = rs.getString("message"); 
	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + subject + "</td>"; 
	 output += "<td>" + designation + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + message + "</td></tr>"; 
	 // buttons
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	
	
	
	public String readItems2() 
	 { 
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>id</th><th>from</th><th>to</th>" +
	 "<th>subject</th>" + 
	 "<th>message</th></tr>"; 
	 
	 String query = "select * from sendmessage"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String from = rs.getString("fromm"); 
	 String to = rs.getString("too"); 
	 String subject = rs.getString("subject"); 
	 String message = rs.getString("message"); 

	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + from + "</td>"; 
	 output += "<td>" + to + "</td>"; 
	 output += "<td>" + subject + "</td>"; 
	 output += "<td>" + message + "</td></tr>"; 
	 // buttons
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	public String profile(String username) 
	 { 
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 
	 
	 String query = "select * from customer  where username='"+username+"'"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email");  
	 String phone = rs.getString("phone"); 
	 String un = rs.getString("username"); 
	 String password = rs.getString("password");
	 String type = rs.getString("type");
	 // Add into the html table
	 output += "<p>" +"Id : "+id + "</p>"; 
	 output += "<p>" + "Name : "+name + "</p>"; 
	 output += "<p>" +"Email : "+ email + "</p>"; 
	 output += "<p>" + "Phone : "+phone + "</p>"; 
	 output += "<p>" +"User Name : "+ un + "</p>"; 
	 output += "<p>" +"Password : "+ password + "</p>"; 
	 output += "<p>" +"Type : "+ type + "</p>"; 
	 // buttons
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	
	
	
}
