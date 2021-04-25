package order.model;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * Order details model class
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 *
 */

@XmlRootElement
public class OrderDetails {
	
	private int orderDetailId;
    private int orderId;
    private String paymentType;
    private Double price;
    private Double totAmount;
    
    
	/**
	 * Default constructor
	 */
	public OrderDetails() {
		super();
	}
	
	/**
	 * Overloaded constructor
	 * @param orderDetailId
	 * @param orderId
	 * @param paymentType
	 * @param price
	 * @param totAmount
	 */
	public OrderDetails(int orderDetailId, int orderId, String paymentType, Double price, Double totAmount) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.paymentType = paymentType;
		this.price = price;
		this.totAmount = totAmount;
	}

	/**
	 * @return the orderDetailId
	 */
	public int getOrderDetailId() {
		return orderDetailId;
	}

	/**
	 * @param orderDetailId the orderDetailId to set
	 */
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

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
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the totAmount
	 */
	public Double getTotAmount() {
		return totAmount;
	}

	/**
	 * @param totAmount the totAmount to set
	 */
	public void setTotAmount(Double totAmount) {
		this.totAmount = totAmount;
	}  
    

}
