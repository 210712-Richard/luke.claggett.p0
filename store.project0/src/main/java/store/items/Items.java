package store.items;

import java.io.Serializable;

public class Items implements Serializable{
	private Integer id;
	private String productName;
	private double productPrice;
	private Integer quantity; 

public Items(Integer id, String productName, double productPrice, Integer quantity) {
	this.id = id;
	this.productName = productName;
	this.productPrice = productPrice;
	this.quantity = quantity;
}

public Integer getID() {
	return id;
}
public void setID(Integer id) {
	this.id = id;
}
public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public Double getProductPrice() {
	return productPrice;
}

public void setProductPrice(Double productPrice) {
	this.productPrice = productPrice;
}

public Integer getQuantity() {
	return quantity;
}

public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
}