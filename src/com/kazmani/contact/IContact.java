package com.kazmani.contact;

import java.sql.SQLException;

public interface IContact {

	public Contact editContactById(Contact contact) throws SQLException;

	public Contact addNewContact(Contact contact) throws SQLException;
	
	public Contact deleteContactById(String phoneOrEmail) throws SQLException;
	
	public Contact getContactById(String phoneOrEmail);
}
