package product.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private int productId;
	private String productTitle;
	private String productDescription;
	private String productType;
	private String productCategory;
	
	
	public Product(int productId, String productTitle, String productDescription, String productType,
			String productCategory) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productCategory = productCategory;
	}

	public Product() {};

	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductTitle() {
		return productTitle;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productDescription="
				+ productDescription + ", productType=" + productType + ", productCategory=" + productCategory + "]";
	}



}
