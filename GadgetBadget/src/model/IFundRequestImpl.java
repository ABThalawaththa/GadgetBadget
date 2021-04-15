package model;

import java.util.Date;

public interface IFundRequestImpl {

	public String readFundRequests();
	public FundRequest getFundRequest(int id);
	public String insertRequest(int clientID, int productID, String contactName, String contactNo, String contactMail,
			String message, String orgName, Date date);
	public String deleteRequest(int fundID);
	public String updateRequest(int fundID , int clientID, int productID, String contactName, String contactNo, String contactMail,
			String message, String orgName, Date date);
}
