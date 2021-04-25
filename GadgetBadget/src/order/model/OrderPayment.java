package order.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Order payment model class
 * 
 * @author Sathsarani M.W.A.R - IT19151120
 *
 */
@XmlRootElement
public class OrderPayment {
	
	private int orderPaymentId;
    private int orderId;
    private String paymentType;
    private Double totAmount;
    private int buyerId;
    private String paymentStatus;
    private Date paidDate;
    private String cardNo;
    private String paypalAccNo;
    
	/**
	 * Default constructor
	 */
	public OrderPayment() {
		super();
	}

	/**
	 * Overloaded constructor
	 * @param orderPaymentId
	 * @param orderId
	 * @param paymentType
	 * @param totAmount
	 * @param buyerId
	 * @param paymentStatus
	 * @param paidDate
	 * @param cardNo
	 * @param paypalAccNo
	 */
	public OrderPayment(int orderPaymentId, int orderId, String paymentType, Double totAmount, int buyerId,
			String paymentStatus, Date paidDate, String cardNo, String paypalAccNo) {
		super();
		this.orderPaymentId = orderPaymentId;
		this.orderId = orderId;
		this.paymentType = paymentType;
		this.totAmount = totAmount;
		this.buyerId = buyerId;
		this.paymentStatus = paymentStatus;
		this.paidDate = paidDate;
		this.cardNo = cardNo;
		this.paypalAccNo = paypalAccNo;
	}

	/**
	 * @return the orderPaymentId
	 */
	public int getOrderPaymentId() {
		return orderPaymentId;
	}

	/**
	 * @param orderPaymentId the orderPaymentId to set
	 */
	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
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

	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the paidDate
	 */
	public Date getPaidDate() {
		return paidDate;
	}

	/**
	 * @param paidDate the paidDate to set
	 */
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the paypalAccNo
	 */
	public String getPaypalAccNo() {
		return paypalAccNo;
	}

	/**
	 * @param paypalAccNo the paypalAccNo to set
	 */
	public void setPaypalAccNo(String paypalAccNo) {
		this.paypalAccNo = paypalAccNo;
	}
    
	
    

}
