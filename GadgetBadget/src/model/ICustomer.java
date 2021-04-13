package model;

import java.util.List;

import model.Customer;

public interface ICustomer {
	public boolean deletemessage(String id);
	public  List<Customer> getCustomerDetails(String Id);
	public boolean updatecustomer(String id, String name, String email, String phone, String username, String password, String type);
	public boolean insertcustomer(String name, String email, String phone, String username, String password,String type);
	public boolean insertCustomerCare(String from,String to,String subject ,String message);
	public List<Customer> getDetails(String userName);
	public boolean validatee(String username,String password);
}
