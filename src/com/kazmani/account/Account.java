package com.kazmani.account;

public class Account {

	String accountNumber;
	String firstName;
	String lastName;
	String surName;
	String errorMessage;
	String message;

	/**
	 * @param accountNumber
	 * @param firstName
	 * @param lastName
	 * @param surName
	 * @param bankName
	 * @param phoneNumber
	 */

	public Account(String accountNumber, String firstName, String lastName,
			String surName, String bankName, String phoneNumber) {
		super();
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.surName = surName;
		this.bankName = bankName;
		this.phoneNumber = phoneNumber;
	}

	String bankName;
	String phoneNumber;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

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

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Account() {
	};
}
