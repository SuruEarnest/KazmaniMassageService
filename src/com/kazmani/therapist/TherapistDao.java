package com.kazmani.therapist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.kazmani.address.Address;
import com.kazmani.address.Location;
import com.kazmani.config.Cypherizer;
import com.kazmani.config.DbConnector;
import com.kazmani.contact.Contact;
import com.kazmani.credentials.Credentials;
import com.kazmani.role.IRole;

public class TherapistDao implements IRole, ITherapist {

	DbConnector connector = new DbConnector();
	private Connection conn = null;
	private PreparedStatement preStatement = null;
	private PreparedStatement preStatement2 = null;
	private PreparedStatement preStatement3 = null;
	private PreparedStatement cSt = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String role;

	@Override
	public void setRole(String role) {
		
		this.role = role;
	}

	@Override
	public String getRole() {
	
		return this.role;
	}

	@Override
	public Therapist addNewTherapist(Therapist therapist) throws SQLException {

		Therapist thr = new Therapist();
		String password = therapist.getUserCredentials().getPassword();
		String encryptedPassword = Cypherizer.enCrypt(password);

		try {

			conn = connector.makeConnection();
			conn.setAutoCommit(false);
			// don't forget to call commit() and rollback()
			String sqlQuery = "insert into THERAPIST_TB"
					+ "(FIRST_NAME,LAST_NAME,GENDER,AGE,"
					+ "COUNTRY,STATE,LOCAL_GOVT,STATUS,PROFILE_TEXT,PHONE_NUMBER) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			preStatement = conn.prepareStatement(sqlQuery);
			preStatement.setString(1, therapist.getFirstName());
			preStatement.setString(2, therapist.getLastName());
			preStatement.setString(3, therapist.getGender());
			preStatement.setInt(4, therapist.getAge());
			preStatement.setString(5, therapist.getCountry());
			preStatement.setString(6, therapist.getStateOfOrigin());
			preStatement.setString(7, therapist.getLocalGovt());
			preStatement.setString(8, therapist.getStatus());
			preStatement.setString(9, therapist.getProfileText());
			preStatement.setString(10, therapist.getContact().getPhoneNumber());

			String sqlQuery2 = "insert into CONTACT_TB(EMAIL,PHONE_NUMBER,ADDR_COUNTRY,"
					+ "ADDR_TYPE,ADDR_STATE,ADDR_LOCAL_GOVT,ADDR_DESCRIPTION)"
					+ " VALUES(?,?,?,?,?,?,?)";

			preStatement2 = conn.prepareStatement(sqlQuery2);
			Contact contact = therapist.getContact();
			preStatement2.setString(1, contact.getEmail());
			preStatement2.setString(2, contact.getPhoneNumber());
			preStatement2.setString(3, contact.getAddress().getCountry());
			preStatement2.setString(4, contact.getAddress().getAddressType());
			preStatement2.setString(5, contact.getAddress().getState());
			preStatement2.setString(6, contact.getAddress().getLocalGovt());
			preStatement2.setString(7, contact.getAddress().getDescription());

			String sqlQuery3 = "insert into USERS(PASS_WORD,PHONE_NUMBER,EMAIL,ROLE) VALUES(?,?,?,?)";
			preStatement3 = conn.prepareStatement(sqlQuery3);
			preStatement3.setString(1, encryptedPassword);
			preStatement3.setString(2, contact.getPhoneNumber());
			preStatement3.setString(3, contact.getEmail());

			this.setRole("therapist");
			preStatement3.setString(4, this.getRole());

			System.out.println("about to execute query 1");
			preStatement.executeUpdate();
			System.out.println("finished execute query 1");

			System.out.println("about to execute query2");
			preStatement2.executeUpdate();
			System.out.println("finished execute query2");

			System.out.println("about to execute query3");
			preStatement3.executeUpdate();
			System.out.println("finished execute query3");

			conn.commit();
			System.out.println("just commited!");
			thr.setMessage("successfully registered!");
			return thr;

		} catch (SQLException ex) {
			conn.rollback();
			System.out.println("failed txn error code:");
			thr.setErrorMessage("FAILED:" + ex.getMessage());
			thr.setErrorCode(1);
			ex.printStackTrace();
			return thr;

		} finally {
			// release resources here
			connector.release(preStatement2);
			connector.release(preStatement);
			// connector.close(preStatement3);
			connector.release(conn);

		}

	}

	@Override
	public Therapist checkStatus(String uniqueId) {
		
		String sql = "SELECT STATUS from THERAPIST_TB where PHONE_NUMBER=?";
		Therapist thr = new Therapist();

		conn = connector.makeConnection();
		try {
			cSt = conn.prepareStatement(sql);
			cSt.setString(1, uniqueId);
			rs = cSt.executeQuery();
			String statusFrmDb = null;

			if (rs.next() == true) {
				do {
					statusFrmDb = rs.getString("STATUS");
					System.out.println("Status from db:" + statusFrmDb);
					thr.setStatus(rs.getString("STATUS"));
					thr.setMessage("Status successfully fetched!");
				} while (rs.next());

			} else {

				System.out.println("Status from db:" + statusFrmDb);
				thr.setMessage("No such therapist with the specified ID");

			}

			return thr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			thr.setErrorMessage("Failed:" + e.getMessage());
			return thr;
		} finally {
			connector.release(conn);
			connector.release(cSt);
			connector.release(rs);
		}

	}

	@Override
	public Therapist editTherapistDetails(Therapist therapist) {
		String sql = "UPDATE ";
		return null;
	}

	@Override
	public Therapist editProfileText(String uniqueId, String newProfileText)
			throws SQLException {

		String sql = "UPDATE THERAPIST_TB SET PROFILE_TEXT=? where PHONE_NUMBER =?";
		Therapist thr = new Therapist();
		conn = connector.makeConnection();

		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, newProfileText);
			pst.setString(2, uniqueId);
			pst.executeUpdate();
			conn.commit();
			thr.setMessage("profile text has been updated successfully");
			return thr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			thr.setErrorMessage("Failed:" + e.getMessage());
			return thr;
		} finally {
			// release resources
			connector.release(conn);
			connector.release(pst);

		}

	}

	/**
	 * parameter is phoneNumber
	 */
	@Override
	public Therapist getTherapistById(String phoneNumber) {

		/* gets a few details of the therapist */
		Therapist thr = new Therapist();
		String sql = "SELECT THERAPIST_TB.FIRST_NAME,THERAPIST_TB.LAST_NAME,THERAPIST_TB.GENDER,"
				+ "THERAPIST_TB.AGE,THERAPIST_TB.COUNTRY,THERAPIST_TB.STATE,"
				+ "THERAPIST_TB.LOCAL_GOVT,THERAPIST_TB.STATUS,THERAPIST_TB.PROFILE_TEXT,"
				+ "THERAPIST_TB.PHONE_NUMBER,CONTACT_TB.EMAIL,CONTACT_TB.ADDR_COUNTRY,"
				+ "CONTACT_TB.ADDR_TYPE,CONTACT_TB.ADDR_STATE,CONTACT_TB.ADDR_LOCAL_GOVT,CONTACT_TB.ADDR_DESCRIPTION"
				+ " FROM THERAPIST_TB,CONTACT_TB where THERAPIST_TB.PHONE_NUMBER = CONTACT_TB.PHONE_NUMBER AND CONTACT_TB.PHONE_NUMBER=? ";
		try {
			conn = connector.makeConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, phoneNumber);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				System.out.println("row is false..begins");
				thr.setMessage("No such therapist with Phone-Number ID provided!");
				System.out.println("row is false ended");
			} else {
				System.out.println("row is true begins");
				do {
					thr.setFirstName(rs.getString("FIRST_NAME"));
					thr.setLastName(rs.getString("LAST_NAME"));
					thr.setGender(rs.getString("GENDER"));
					thr.setCountry(rs.getString("COUNTRY"));
					thr.setStateOfOrigin(rs.getString("STATE"));
					thr.setStatus(rs.getString("STATUS"));
					thr.setProfileText(rs.getString("PROFILE_TEXT"));
					Contact contact = new Contact();

					contact.setEmail(rs.getString("EMAIL"));
					contact.setPhoneNumber(rs.getString("PHONE_NUMBER"));

					Address ad = new Address();
					ad.setState(rs.getString("ADDR_STATE"));
					ad.setCountry(rs.getString("ADDR_COUNTRY"));
					ad.setAddressType(rs.getString("ADDR_TYPE"));
					ad.setDescription(rs.getString("ADDR_DESCRIPTION"));
					ad.setLocalGovt(rs.getString("ADDR_LOCAL_GOVT"));
					contact.setAddress(ad);
					thr.setContact(contact);
					thr.setMessage("Therapist details successfully fetched!");

				} while (rs.next());
				System.out.println("row is true ended");
			}

			return thr;

		} catch (SQLException e) {
			e.printStackTrace();
			thr.setErrorMessage("Failed:" + e.getMessage());
			return thr;
		} finally {

			connector.release(pst);
			connector.release(rs);
			connector.release(conn);
		}

	}

	@Override
	public ArrayList<Therapist> getAllTherapistsInLocation(Location location,
			Address address) {

		String sqlQuery = "";
		return null;
	}

	@Override
	public Therapist editBioData(Therapist therapist) {
		
		String sqlQuery = "";
		return null;
	}

	public static void main(String args[]) throws SQLException {
		Gson gson = new Gson();

		String firstName = "Shade";
		String lastName = "Adekunle";
		Contact contact = new Contact("shadi@rocketmail.com", "09019999900",
				new Address("Home", "2,Parklane,Apapa GRA", "Lagos",
						"Ajegunle", "Nigeria"));
		String profileText = "I am an awesome therapist with cutting-edge skills";
		String status = "unvalidated";
		String gender = "Female";
		String stateOfOrigin = "Ondo";
		String localGovt = "Akoko";
		String country = "Nigeria";

		Therapist thr = new Therapist(firstName, lastName, contact,
				profileText, status, gender, 25, stateOfOrigin, localGovt,
				country);
		thr.setUserCredentials(new Credentials("", "sha1"));

		System.out.println("therapist JSON:" + gson.toJson(thr));

		System.out.println("JSON FORMAT:\n"
				+ gson.toJson(new TherapistDao().addNewTherapist(thr)));

		/*
		 * System.out.println("JSON FORMAT:\n" + gson.toJson(new TherapistDao()
		 * .checkTherapistStatus("09003787111")));
		 */
	}
}
