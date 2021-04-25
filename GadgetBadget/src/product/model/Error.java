package product.model;

//Handle Error Messages
/**
 * @author IT19240602 
 *
 */
public class Error {
	
	
	private String errorMessage;

	public Error() {
		
	}

	/**
	 * @param errorMessage
	 */
	public Error(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	/**
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
