package model;

import java.util.Date;

public interface IFundRequestImpl {

	public String readFundRequests();
	public FundRequest getFundRequest(int id);
	public String insertItem(int clientID, int productID, String contactName, String contactNo,
			String contactMail, String message, String orgName, Date date);
	public String deleteItem(int fundID);
	public String updateItem(int fundID, int clientID, int productID, String contactName, String contactNo, String contactMail,
			String message, String orgName, Date date);
}
