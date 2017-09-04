package org.srinivas.siteworks.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "Registered_Accounts")
public class Account {
	

	@Id
	@Column(name = "Account_Id")
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	private Integer accountId;	

	@Column(name = "User_Name")
	private String userName;
	

	@Column(name = "User_Password")
	private String password;
	

	@Column(name = "Created_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	

	@OneToOne
	@JoinColumn(name="Developer_Id")
	private Developer developer;	
	
	/**
	 * Gets the account id. 
	 * @return the account id
	 */
	public Integer getAccountId() {
		return accountId;
	}
	
	/**
	 * Sets the account id.* 
	 * @param accountId
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}	
	
	/**
	 * Gets the user name.
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name. 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the password.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password. 
	 * @param password            
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the created date. 
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * Sets the created date. 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the developer. 
	 * @return the developer
	 */
	public Developer getDeveloper() {
		return developer;
	}
	
	/**
	 * Sets the developer. 
	 * @param developer
	 */
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	
}
