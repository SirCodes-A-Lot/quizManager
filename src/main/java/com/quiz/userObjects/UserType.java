package com.quiz.userObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String userType;
	
	private String hashedPassword;
	
	public UserType() {
	}

	public UserType(String userType, String hashedPassword) {
		this.userType = userType;
		this.hashedPassword = hashedPassword;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

}
