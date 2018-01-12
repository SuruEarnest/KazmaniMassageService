package com.kazmani.access;

import java.sql.SQLException;  

import com.kazmani.credentials.Credentials;

public interface ILogin {

	public Login authenticate(Credentials loginDetails) throws SQLException;
	
}
