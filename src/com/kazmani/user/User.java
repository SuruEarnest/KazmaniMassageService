package com.kazmani.user;

import java.io.Serializable;      

import com.kazmani.credentials.Credentials;
import com.kazmani.role.IRole;

public class User implements IRole, Serializable {

	private static final long serialVersionUID = 1L;
	private Credentials userCredentials;
	private String role;

	/**
	 * @return the userCredentials
	 */
	public Credentials getUserCredentials() {
		return userCredentials;
	}

	/**
	 * @param userCredentials
	 *            the userCredentials to set
	 */
	public void setUserCredentials(Credentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	@Override
	public void setRole(String role) {
		// TODO Auto-generated method stub
		this.role = role;

	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}

}
