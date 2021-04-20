package order.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sathsarani M.W.A.R - IT19151120
 * 
 * Order model class
 *
 */

@XmlRootElement
public class Order {
	
	private int orderId;
    private String orderDesc;
    private Date orderDate;
    private String orderStatus;
    private int productId;
    private int buyerId;
    
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * @return the orderDesc
	 */
	public String getOrderDesc() {
		return orderDesc;
	}
	
	/**
	 * @param orderDesc the orderDesc to set
	 */
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * @return the buyerId
	 */
	public int getBuyerId() {
		return buyerId;
	}
	
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
    
    
    
    

}
