package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the SHOPPING_CART database table.
 * 
 */
@Entity
@Table(name="SHOPPING_CART",schema="TESTDB")
@NamedQuery(name="ShoppingCart.findAll", query="SELECT s FROM ShoppingCart s")
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SHOPPING_CART_SEQ", catalog = "",schema="TESTDB",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="SHOPPING_CART_SEQ")
	private long id;

	@Column(name="PRODUCT_ID")
	private long productId;

	@Column(name="PURCHASE_DATE")
	private String purchaseDate;

	@Column(name="UNIT_PRICE")
	private double unitPrice;

	@Column(name="UNIT_QUANTITY")
	private int unitQuantity;

	@Column(name="USER_ID")
	private long userId;

	public ShoppingCart() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitQuantity() {
		return this.unitQuantity;
	}

	public void setUnitQuantity(int unitQuantity) {
		this.unitQuantity = unitQuantity;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}