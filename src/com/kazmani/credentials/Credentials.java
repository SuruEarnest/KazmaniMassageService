package com.kazmani.credentials;

import java.io.Serializable;  

import com.google.gson.Gson;
/**
 * This class houses the user credentials needed for authentication
 * @author Earnest Erihbra Suru
 *
 */
public class Credentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;//can be email or phoneNumber
	private String password;

	public Credentials(){}
	public Credentials(String username, String password) {
		// TODO Auto-generated constructor stub
		this.userId = username;
		this.password = password;
	}

	public String getUsername() {
		return userId;
	}

	public void setUsername(String username) {
		this.userId = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void main(String args[]) {
		Gson gs = new Gson();
		Credentials cr = new Credentials();

		cr.setPassword("serihbrah101");
		cr.setUsername("serihbrah@gmail.com");

		System.out.println(gs.toJson(cr));

		String json = gs.toJson(cr).toString();

		Credentials c = gs.fromJson(json, Credentials.class);
		System.out.println("my json:" + c.getPassword());
	}
}
