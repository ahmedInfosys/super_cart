package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the PAYMENT_CARD database table.
 * 
 */
@Entity
@Table(name="PAYMENT_CARD",schema="TESTDB")
@NamedQuery(name="PaymentCard.findAll", query="SELECT p FROM PaymentCard p")
public class PaymentCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PAYMENT_CARD_SEQ", catalog = "",schema="TESTDB",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="PAYMENT_CARD_SEQ")
	private long id;

	@Column(name="BILLING_CITY")
	private String billingCity;

	@Column(name="BILLING_STATE")
	private String billingState;

	@Column(name="BILLING_STREET_ADDRESS")
	private String billingStreetAddress;

	@Column(name="BILLING_ZIPE_CODE")
	private String billingZipeCode;

	@Column(name="CARD_NUMBER")
	private String cardNumber;

	private int code;

	@Column(name="EXPIRATION_DATE")
	private String expirationDate;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="BALANCE")
    private double balance;
	
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public PaymentCard() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBillingCity() {
		return this.billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return this.billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingStreetAddress() {
		return this.billingStreetAddress;
	}

	public void setBillingStreetAddress(String billingStreetAddress) {
		this.billingStreetAddress = billingStreetAddress;
	}

	public String getBillingZipeCode() {
		return this.billingZipeCode;
	}

	public void setBillingZipeCode(String billingZipeCode) {
		this.billingZipeCode = billingZipeCode;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}