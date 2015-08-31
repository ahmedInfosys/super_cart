package model;

import java.io.Serializable;

import javax.persistence.*;


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

	private String firstname;

	private String lastname;

	public OnlineCustomer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}