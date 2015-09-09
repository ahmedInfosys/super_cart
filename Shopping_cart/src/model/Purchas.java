package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PURCHASES database table.
 * 
 */
@Entity
@Table(name="PURCHASES",schema="TESTDB")
@NamedQuery(name="Purchas.findAll", query="SELECT p FROM Purchas p")
public class Purchas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PURCHASES_SEQ", catalog = "",schema="TESTDB",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="PURCHASES_SEQ")
	@Column(name="USER_ID")
	private long userId;

	@Column(name="PURCHASE_DATE")
	private String purchaseDate;

	@Column(name="UNIT_PRICE")
	private double unitPrice;

	@Column(name="UNIT_QUANTITY")
	private int unitQuantity;

	public Purchas() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

}