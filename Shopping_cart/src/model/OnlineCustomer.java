package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the ONLINE_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="ONLINE_CUSTOMER", schema="TESTDB")
@NamedQuery(name="OnlineCustomer.findAll", query="SELECT o FROM OnlineCustomer o")
public class OnlineCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ONLINE_CUSTOMER_SEQ", catalog = "",schema="TESTDB",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="ONLINE_CUSTOMER_SEQ")
	private long id;

	private String email;

	private String firstname;

	@Column(name="JOIN_DATE")
	private String joinDate;

	private String lastname;

	private long zipcode;

	public OnlineCustomer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}

}