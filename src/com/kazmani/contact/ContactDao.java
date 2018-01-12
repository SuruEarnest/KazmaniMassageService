package com.kazmani.contact;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kazmani.address.Address;
import com.kazmani.config.DbConnector;

public class ContactDao implements IContact {

	private Connection conn = null;
	private DbConnector connector = new DbConnector();
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	@Override
	public Contact addNewContact(Contact contact) throws SQLException {
		// TODO Auto-generated method stub
		String sqlQuery = "insert into CONTACT_TB(EMAIL,PHONE_NUMBER,ADDR_COUNTRY,"
				+ "ADDR_TYPE,ADDR_STATE,ADDR_LOCAL_GOVT,ADDR_DESCRIPTION)"
				+ " VALUES(?,?,?,?,?,?,?)";

		Contact addContact = new Contact();

		conn = connector.makeConnection();

		try {

			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sqlQuery);

			pst.setString(1, contact.getEmail());
			pst.setString(2, contact.getPhoneNumber());
			pst.setString(3, contact.getAddress().getCountry());
			pst.setString(4, contact.getAddress().getAddressType());
			pst.setString(5, contact.getAddress().getState());
			pst.setString(6, contact.getAddress().getLocalGovt());
			pst.setString(7, contact.getAddress().getDescription());
			pst.executeUpdate();
			addContact.setMessage("New Contact successfully added");
			conn.commit();

			return addContact;
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			addContact.setErrorMessage("Error occured:" + e.getMessage());
			return addContact;
		} finally {
			connector.release(pst);
			connector.release(conn);
		}

	}

	@Override
	public Contact editContactById(Contact contact) throws SQLException {
		// TODO Auto-generated method stub
		String sqlQuery = "UPDATE CONTACT_TB SET EMAIL=?,PHONE_NUMBER=?,"
				+ "ADDR_COUNTRY=?,ADDR_TYPE=?,ADDR_STATE=?,ADDR_LOCAL_GOVT=?,ADDR_DESCRIPTION =?"
				+ " where PHONE_NUMBER=? or EMAIL=?";

		String phoneOrEmail = contact.getId();
		Contact addContact = new Contact();
		conn = connector.makeConnection();

		try {

			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sqlQuery);

			pst.setString(1, contact.getEmail());
			pst.setString(2, contact.getPhoneNumber());
			pst.setString(3, contact.getAddress().getCountry());
			pst.setString(4, contact.getAddress().getAddressType());
			pst.setString(5, contact.getAddress().getState());
			pst.setString(6, contact.getAddress().getLocalGovt());
			pst.setString(7, contact.getAddress().getDescription());
			pst.setString(8, phoneOrEmail);
			pst.setString(9, phoneOrEmail);
			pst.executeUpdate();
			addContact.setMessage("Contact successfully edited");
			conn.commit();

			return addContact;

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			addContact.setErrorMessage("Error occured:" + e.getMessage());
			return addContact;

		} finally {
			connector.release(pst);
			connector.release(conn);
		}

	}

	@Override
	public Contact deleteContactById(String phoneOrEmail) throws SQLException {
		// TODO Auto-generated method stub
		String sqlQuery = "DELETE from CONTACT_TB where PHONE_NUMBER=? or EMAIL=?";

		Contact contact = new Contact();
		conn = connector.makeConnection();

		try {

			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sqlQuery);

			pst.setString(1, phoneOrEmail);
			pst.setString(2, phoneOrEmail);

			// execute delete operation
			pst.executeUpdate();

			contact.setMessage("Contact successfully deleted");
			conn.commit();

			return contact;

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			contact.setErrorMessage("Error occured:" + e.getMessage());
			return contact;

		} finally {
			connector.release(pst);
			connector.release(conn);
		}
	}

	@Override
	public Contact getContactById(String phoneOrEmail) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT * FROM CONTACT_TB where PHONE_NUMBER=? or EMAIL=?";

		Contact addContact = new Contact();

		conn = connector.makeConnection();

		try {

			pst = conn.prepareStatement(sqlQuery);
			pst.setString(1, phoneOrEmail);
			pst.setString(2, phoneOrEmail);
			rs = pst.executeQuery();
			if (rs.next() == true) {

				do {
					addContact.setEmail(rs.getString("EMAIL"));
					addContact.setPhoneNumber(rs.getString("PHONE_NUMBER"));
					Address addr = new Address();
					addr.setAddressType(rs.getString("ADDR_TYPE"));
					addr.setCountry(rs.getString("ADDR_COUNTRY"));
					addr.setState(rs.getString("ADDR_STATE"));
					addr.setLocalGovt(rs.getString("ADDR_LOCAL_GOVT"));
					addr.setDescription(rs.getString("ADDR_DESCRIPTION"));
					addContact.setAddress(addr);
				} while (rs.next());
			} else {
				addContact.setMessage("No contact matches the Id provided");
			}
			return addContact;
		} catch (SQLException e) {
			e.printStackTrace();
			addContact.setErrorMessage("Error occured:" + e.getMessage());
			return addContact;
		} finally {
			connector.release(pst);
			connector.release(rs);
			connector.release(conn);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
