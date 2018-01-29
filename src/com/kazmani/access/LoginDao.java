package com.kazmani.access;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.Gson;
import com.kazmani.address.Address;
import com.kazmani.client.Client;
import com.kazmani.config.Cypherizer;
import com.kazmani.config.DbConnector;
import com.kazmani.contact.Contact;
import com.kazmani.credentials.Credentials;
import com.kazmani.therapist.Therapist;

/**
 * This class helps to authenticate users
 * 
 * @author Earnest-Suru Erihbra
 * 
 */
public class LoginDao implements ILogin {

    private DbConnector connector = new DbConnector();
    private Connection con;

    private PreparedStatement clientPrepStatement = null;
    private PreparedStatement userPrepStatement = null;
    private PreparedStatement trPreparedStatement = null;
    private ResultSet rs = null, rs2 = null, rs3 = null;


     /**
      * @param loginDetails takes in a Credentials object to authenticate users
      * @return returns a Login object containing response message from the system
      */
    public Login authenticate(Credentials loginDetails) throws SQLException {

	Login loginData = new Login();
	String role = null;
	String phoneNumber = null;

	String username = loginDetails.getUsername();
	String password = loginDetails.getPassword();

	String encryptedPassword = Cypherizer.enCrypt(password);
	try {

	    con = connector.makeConnection();

	    con.setAutoCommit(false);

	    String sqlQuery = "SELECT ROLE,PHONE_NUMBER from USERS where "
		    + "email=? AND pass_word=? OR "
		    + "phone_number=? AND pass_word=?";
	    userPrepStatement = con.prepareStatement(sqlQuery);
	    userPrepStatement.setString(1, username);
	    userPrepStatement.setString(2, encryptedPassword);
	    userPrepStatement.setString(3, username);
	    userPrepStatement.setString(4, encryptedPassword);

	    rs = userPrepStatement.executeQuery();

	    while (rs.next()) {
		// fetches the role of the user with the provided credentials
		role = rs.getString("ROLE");
		phoneNumber = rs.getString("PHONE_NUMBER");
	    }

	    System.out.println("Role:" + role);
	    System.out.println("Phone Number:" + phoneNumber);
	    if (role != null) {
		// then there is a value in it which is either client or
		// therapist or admin
		if (role.equalsIgnoreCase("client")) {
		    // run a query to get the user with the user credentials
		    // specified above
		    String clientSql = "SELECT CLIENT_TB.PHONE_NUMBER,CLIENT_TB.LAST_NAME,"
			    + "CLIENT_TB.FIRST_NAME,CLIENT_TB.GENDER,CONTACT_TB.EMAIL,"
			    + "CONTACT_TB.PHONE_NUMBER,CONTACT_TB.ADDR_DESCRIPTION,"
			    + "CONTACT_TB.ADDR_LOCAL_GOVT,"
			    + "CONTACT_TB.ADDR_STATE,CONTACT_TB.ADDR_TYPE,CONTACT_TB.ADDR_COUNTRY "
			    + "from "
			    + "CLIENT_TB,CONTACT_TB,USERS "
			    + "WHERE "
			    + "CLIENT_TB.PHONE_NUMBER = CONTACT_TB.PHONE_NUMBER "
			    + "AND "
			    + "CONTACT_TB.PHONE_NUMBER = USERS.PHONE_NUMBER "
			    + "AND " + "USERS.PHONE_NUMBER=?";

		    clientPrepStatement = con.prepareStatement(clientSql);
		    clientPrepStatement.setString(1, phoneNumber);

		    rs2 = clientPrepStatement.executeQuery();
		    while (rs2.next()) {
			Client loginClient = new Client();

			Address clientAddress = new Address();
			clientAddress
				.setAddressType(rs2.getString("ADDR_TYPE"));
			clientAddress.setDescription(rs2
				.getString("ADDR_DESCRIPTION"));
			clientAddress.setLocalGovt(rs2
				.getString("ADDR_LOCAL_GOVT"));
			clientAddress.setState(rs2.getString("ADDR_STATE"));
			clientAddress.setCountry(rs2.getString("ADDR_COUNTRY"));

			Contact clientContact = new Contact();
			clientContact.setAddress(clientAddress);
			clientContact.setEmail(rs2.getString("EMAIL"));
			clientContact.setPhoneNumber(rs2
				.getString("PHONE_NUMBER"));

			// adding all the client attributes

			loginClient.setFirstName(rs2.getString("FIRST_NAME"));
			loginClient.setLastName(rs2.getString("LAST_NAME"));
			loginClient.setGender(rs2.getString("GENDER"));

			loginClient.setContact(clientContact);
			loginClient.setRole(role);
			loginData.setUser(loginClient);
			loginData.setMessage("LOGIN SUCCESSFUL");

		    }

		} else if (role.equalsIgnoreCase("therapist")) {
		    // therapist
		    String trSql = "SELECT THERAPIST_TB.PHONE_NUMBER,THERAPIST_TB.LAST_NAME,"
			    + "THERAPIST_TB.FIRST_NAME,THERAPIST_TB.GENDER,THERAPIST_TB.COUNTRY,"
			    + "THERAPIST_TB.STATE,THERAPIST_TB.LOCAL_GOVT,THERAPIST_TB.STATUS,"
			    + "THERAPIST_TB.PROFILE_TEXT,THERAPIST_TB.AGE,"
			    + "CONTACT_TB.EMAIL,"
			    + "CONTACT_TB.PHONE_NUMBER,CONTACT_TB.ADDR_DESCRIPTION,"
			    + "CONTACT_TB.ADDR_LOCAL_GOVT,"
			    + "CONTACT_TB.ADDR_STATE,CONTACT_TB.ADDR_TYPE,CONTACT_TB.ADDR_COUNTRY "
			    + "from "
			    + "THERAPIST_TB,CONTACT_TB,USERS "
			    + "WHERE "
			    + "THERAPIST_TB.PHONE_NUMBER = CONTACT_TB.PHONE_NUMBER "
			    + "AND "
			    + "CONTACT_TB.PHONE_NUMBER=USERS.PHONE_NUMBER "
			    + "AND " + "USERS.PHONE_NUMBER=?";

		    trPreparedStatement = con.prepareStatement(trSql);
		    trPreparedStatement.setString(1, phoneNumber);

		    rs3 = trPreparedStatement.executeQuery();
		    while (rs3.next()) {
			Therapist loginTherapist = new Therapist();

			Address therapistAddress = new Address();
			therapistAddress.setAddressType(rs3
				.getString("ADDR_TYPE"));
			therapistAddress.setDescription(rs3
				.getString("ADDR_DESCRIPTION"));
			therapistAddress.setLocalGovt(rs3
				.getString("ADDR_LOCAL_GOVT"));
			therapistAddress.setState(rs3.getString("ADDR_STATE"));
			therapistAddress.setCountry(rs3
				.getString("ADDR_COUNTRY"));

			// setting up the contact attributes
			Contact therapistContact = new Contact();
			therapistContact.setAddress(therapistAddress);
			therapistContact.setEmail(rs3.getString("EMAIL"));
			therapistContact.setPhoneNumber(rs3
				.getString("PHONE_NUMBER"));

			// adding all the client attributes
			loginTherapist
				.setFirstName(rs3.getString("FIRST_NAME"));
			loginTherapist.setLastName(rs3.getString("LAST_NAME"));
			loginTherapist.setGender(rs3.getString("GENDER"));
			loginTherapist.setCountry(rs3.getString("COUNTRY"));
			loginTherapist.setStateOfOrigin(rs3.getString("STATE"));
			loginTherapist
				.setLocalGovt(rs3.getString("LOCAL_GOVT"));
			loginTherapist.setStatus(rs3.getString("STATUS"));
			loginTherapist.setProfileText(rs3
				.getString("PROFILE_TEXT"));
			loginTherapist.setContact(therapistContact);
			loginTherapist.setRole(role);
			loginTherapist.setAge(Integer.parseInt(rs3
				.getString("AGE")));
			loginData.setUser(loginTherapist);
			loginData.setMessage("LOGIN SUCCESSFUL");

		    }

		} else if (role.equalsIgnoreCase("admin")) {
		    // admin
		}

		System.out.println("" + role);
		con.commit();
		return loginData;
	    }

	    // no results found...
	    loginData.setErrorMessage("No such user!");
	    loginData.setErrorCode(1);
	    System.out.println("No such user");
	    return loginData;

	} catch (SQLException e) {
	    // TODO Auto-generated catch block\
	    con.rollback();
	    loginData.setErrorCode(e.getErrorCode());
	    loginData.setErrorMessage("FAILED:" + e.getMessage());
	    e.printStackTrace();
	    return loginData;

	} finally {
	    // releasing resources to avoid memory leakages
	    System.out.println("Releasing Resources");
	    connector.release(rs);
	    connector.release(rs2);
	    connector.release(rs3);
	    connector.release(trPreparedStatement);
	    connector.release(clientPrepStatement);
	    connector.release(userPrepStatement);
	    connector.release(con);
	}

    }

    public static void main(String args[]) throws SQLException {

	Gson gson = new Gson();
	Credentials cre = new Credentials("09000000000", "laide122");
	System.out.println("JSON format:\n"
		+ gson.toJson(new LoginDao().authenticate(cre)));

    }
}
