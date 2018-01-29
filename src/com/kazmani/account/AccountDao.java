package com.kazmani.account;

import java.sql.Connection;    
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.kazmani.config.DbConnector;

/**
 * 
 * @author Earnest Erihbra Suru The Essence of this class is to keep a record of
 *         therapists account details so that Kazmani can make payment to them
 *         the moment the've delivered services at the client site
 * 
 */
public class AccountDao implements IAccount {

	private DbConnector connector = new DbConnector();
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	@Override
	public Account createAccount(Account account) throws SQLException {
		Account acct = new Account();

		conn = connector.makeConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT into BANK_ACCOUNTS"
					+ "(PHONE_NUMBER,ACCOUNT_NUMBER,SURNAME,FIRST_NAME,LAST_NAME,BANK_NAME) "
					+ "values(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, account.getPhoneNumber());
			pst.setString(2, account.getAccountNumber());
			pst.setString(3, account.getSurName());
			pst.setString(4, account.getFirstName());
			pst.setString(5, account.getLastName());
			pst.setString(6, account.getBankName());
			// about to update
			pst.executeUpdate();
			System.out.println("just executed update");
			// about to commit
			conn.commit();
			System.out.println("just commited to the database");

			acct.setMessage("Your account details has been saved!");
			return acct;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			acct.setErrorMessage("Failed:" + e.getMessage());
			conn.rollback();
			return acct;
		} finally {
			// releasing resources
			connector.release(conn);
			connector.release(pst);
		}

	}

	@Override
	public Account editAccountByPhoneNumber(Account account) throws SQLException {
		// TODO Auto-generated method stub
		Account acct = new Account();
		conn = connector.makeConnection();
		try {
			String sql = "UPDATE BANK_ACCOUNTS "
					+ "SET ACCOUNT_NUMBER=?,FIRST_NAME=?,LAST_NAME=? SURNAME=?,BANK_NAME=? "
					+ "where PHONE_NUMBER=?";
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, account.getAccountNumber());
			pst.setString(2, account.getFirstName());
			pst.setString(3, account.getLastName());
			pst.setString(4, account.getSurName());
			pst.setString(5, account.getBankName());
			pst.setString(6, account.getPhoneNumber());
			pst.executeUpdate();
			conn.commit();
			acct.setMessage("Update of account details successful!");
			return acct;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			acct.setErrorMessage("Falied:" + e.getMessage());
			return acct;
		} finally {
			// releasing resources
			connector.release(pst);
			connector.release(rs);
			connector.release(conn);

		}

	}

	@Override
	public Account getAccountByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		Account acct = new Account();
		conn = connector.makeConnection();
		String sql = "SELECT ACCOUNT_NUMBER,SURNAME,FIRST_NAME,LAST_NAME,BANK_NAME from "
				+ "BANK_ACCOUNTS where PHONE_NUMBER=?";
		try {

			pst = conn.prepareStatement(sql);
			pst.setString(1, phoneNumber);
			rs = pst.executeQuery();

			if (rs.next() == true) {
				do {
					// ResultSet is not empty, Iterate over it
					acct.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
					acct.setBankName(rs.getString("BANK_NAME"));
					acct.setFirstName(rs.getString("FIRST_NAME"));
					acct.setLastName(rs.getString("LAST_NAME"));
					acct.setSurName(rs.getString("SURNAME"));
					acct.setMessage("SUCCESS");
				} while (rs.next());
			} else {
				// ResultSet is empty
				acct.setMessage("No account matches the PhoneNumber Id provided!");
			}
			return acct;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			acct.setErrorMessage("Failed:" + e.getMessage());
			return acct;
		} finally {
			connector.release(pst);
			connector.release(rs);
			connector.release(conn);
		}

	}

	public static void main(String args[]) throws SQLException {

		Gson gson = new Gson();

		System.out.println("Account creation Begins");
		
	
		Account acct = new Account();
		acct.setFirstName("Samuel");
		acct.setLastName("Arogundade");
		acct.setSurName("Imole");
		acct.setAccountNumber("0198987652");
		acct.setBankName("Union Bank");
		acct.setPhoneNumber("09023897611");

		System.out.println("finished setting up account...");
		
		System.out.println("Account Json format:\n" + gson.toJson(acct));

		System.out.println("Json Response Format:\n"
				+ gson.toJson(new AccountDao().createAccount(acct)));

		/*System.out.println("Account Details::\n"
				+ gson.toJson(new AccountDao()
						.getAccountDetailsByPhoneNumber("09021711737")));*/
	}
}
