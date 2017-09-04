package org.srinivas.siteworks.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "Developer_Details")
public class Developer {
	

	@Id
	@Column(name = "DeveloperId")
	@GenericGenerator(name = "actgen", strategy = "increment")
	@GeneratedValue(generator = "actgen")
	private Integer developerId;
	

	@Column(name = "First_Name")
	private String firstName;
	

	@Column(name = "Last_Name")
	private String lastName;
	

	@Column(name = "Email_Address")
	private String emailAddress;
	

	@Column(name = "SkillSet")
	private String skillSet;
	
	
	/**
	 * Gets the developer id. 
	 * @return the developer id
	 */
	public Integer getDeveloperId() {
		return developerId;
	}
	
	/**
	 * Sets the developer id. 
	 * @param developerId
	 */
	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}
	
	/**
	 * Gets the first name. 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name. 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name. 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the email address. 
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Sets the email address. 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Gets the skill set. 
	 * @return the skill set
	 */
	public String getSkillSet() {
		return skillSet;
	}
	
	/**
	 * Sets the skill set. 
	 * @param skillSet
	 */
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
}
