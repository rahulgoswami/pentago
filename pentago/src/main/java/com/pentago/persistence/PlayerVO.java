package com.pentago.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PlayerVO implements Serializable {
	private static final long	serialVersionUID	= 1L;

	/*@Id
	@GeneratedValue
	private Long				id;*/

	@NotNull
	@Size(min = 2, max = 30)
	private String				firstName;

	@NotNull
	@Size(min = 2, max = 50)
	private String				lastName;

	@Id
	@NotNull
	@Size(min = 5, max = 20)
	private String				userName;
	
	@NotNull
	private String				password;

	@NotNull
	@Size(min = 0, max = 21844)
	private String				salt;
	
	private String				email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created				= new Date();

	@PrePersist
	private void onCreate() {
		created = new Date();
	}
	
*/	
	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}