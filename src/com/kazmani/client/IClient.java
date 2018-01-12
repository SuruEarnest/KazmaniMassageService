package com.kazmani.client;

import java.sql.SQLException;

public interface IClient {

	public Client addNewClient(/*String password, String firstName,
			String lastName, String gender, String email, String phoneNumber,
			String addrCountry, String addrDesc, String addrType,
			String addrState, String addrLocalGvt*/ Client client) throws SQLException;

	/**
	 * why would I need this?
	 * 
	 * @param uniqueValue
	 *            this should be either the email or phoneNumber
	 * @return a client's object
	 */

	public Client retrievePassword(String uniqueValue);

	public Client getClientByPhoneNumber(String uniqueValue);

	public Client editDetails(String username, String password,
			String firstName, String lastName, String gender, String email,
			String phoneNumber, String addrCountry, String addrDesc,
			String addrType, String addrState, String addrLocalGvt);

}
