package com.kazmani.account;

import java.sql.SQLException;

public interface IAccount {

	public Account createAccount(Account newAccount) throws SQLException;

	public Account editAccountByPhoneNumber(/*String phoneNumber,
			String accountNumber, String firstName, String lastName,
			String surName, String bankName*/ Account account) throws SQLException;

	public Account getAccountByPhoneNumber(String phoneNumber);
} 
