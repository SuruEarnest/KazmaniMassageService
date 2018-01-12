package com.kazmani.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.kazmani.address.Address;
import com.kazmani.config.Cypherizer;
import com.kazmani.config.DbConnector;
import com.kazmani.contact.Contact;
import com.kazmani.credentials.Credentials;
import com.kazmani.role.IRole;

/**
 * This class simply helps to create new kazmani home service massage
 * customers/clients and to perform other client related operations
 * 
 * @author Earnest Suru
 */
public class ClientDao implements IRole, IClient {

	private String role;
	private Client newBie = new Client();
	private Connection con = null;
	private PreparedStatement ps = null, prepStatement = null,
			prepStatement2 = null, prepStatement3 = null;
	private ResultSet rs = null;
	DbConnector connector = new DbConnector();

	/**
	 * @param client
	 *            ,to add a new client to the community
	 */
	public Client addNewClient(Client client) throws SQLException {

		String password = client.getUserCredentials().getPassword();
		String encryptedPassword = Cypherizer.enCrypt(password);
		try {
			con = connector.makeConnection();
			con.setAutoCommit(false);
			String sqlQuery = "insert into CLIENT_TB"
					+ "(PHONE_NUMBER,FIRST_NAME,LAST_NAME,GENDER) VALUES(?,?,?,?)";
			// System.out.println("about to execute query");
			prepStatement = con.prepareStatement(sqlQuery);
			prepStatement.setString(1, client.getContact().getPhoneNumber());
			prepStatement.setString(2, client.getFirstName());
			prepStatement.setString(3, client.getLastName());
			prepStatement.setString(4, client.getGender());

			String sqlQuery2 = "insert into CONTACT_TB(EMAIL,PHONE_NUMBER,ADDR_COUNTRY,"
					+ "ADDR_TYPE,ADDR_STATE,ADDR_LOCAL_GOVT,ADDR_DESCRIPTION)"
					+ " VALUES(?,?,?,?,?,?,?)";

			prepStatement2 = con.prepareStatement(sqlQuery2);
			Contact contact = client.getContact();
			prepStatement2.setString(1, contact.getEmail());
			prepStatement2.setString(2, contact.getPhoneNumber());
			prepStatement2.setString(3, contact.getAddress().getCountry());
			prepStatement2.setString(4, contact.getAddress().getAddressType());
			prepStatement2.setString(5, contact.getAddress().getState());
			prepStatement2.setString(6, contact.getAddress().getLocalGovt());
			prepStatement2.setString(7, contact.getAddress().getDescription());

			String sqlQuery3 = "insert into USERS(PASS_WORD,PHONE_NUMBER,EMAIL,ROLE) VALUES(?,?,?,?)";
			prepStatement3 = con.prepareStatement(sqlQuery3);

			prepStatement3.setString(1, encryptedPassword);
			prepStatement3.setString(2, contact.getPhoneNumber());
			prepStatement3.setString(3, contact.getEmail());

			this.setRole("client");
			prepStatement3.setString(4, this.getRole());

			System.out.println("about to execute query 1");
			prepStatement.executeUpdate();
			System.out.println("finished execute query 1");

			System.out.println("about to execute query2");
			prepStatement2.executeUpdate();
			System.out.println("finished execute query2");

			System.out.println("about to execute query3");
			prepStatement3.executeUpdate();
			System.out.println("finished execute query3");

			con.commit();
			System.out.println("just commited!");
			newBie.setSuccessMessage("SUCCESS");

			return newBie;

		} catch (SQLException ex) {

			con.rollback();
			System.out.println("failed txn error code:");
			newBie.setErrorMessage("FAILED:" + ex.getMessage());
			newBie.setErrorCode(1);
			ex.printStackTrace();
			return newBie;

		} finally {
			connector.release(prepStatement);
			connector.release(prepStatement2);
			connector.release(prepStatement3);
			connector.release(con);
		}

	}

	@Override
	public Client getClientByPhoneNumber(String uniqueValue) {
		// TODO Auto-generated method stub
		Client client = new Client();
		con = connector.makeConnection();
		String sql = "SELECT CLIENT_TB.FIRST_NAME,CLIENT_TB.LAST_NAME,"
				+ "CLIENT_TB.PHONE_NUMBER,CLIENT_TB.GENDER,"
				+ "CONTACT_TB.EMAIL,CONTACT_TB.ADDR_COUNTRY,CONTACT_TB.ADDR_TYPE,"
				+ "CONTACT_TB.ADDR_STATE,CONTACT_TB.ADDR_LOCAL_GOVT,CONTACT_TB.ADDR_DESCRIPTION "
				+ "FROM CLIENT_TB,CONTACT_TB "
				+ "where CLIENT_TB.PHONE_NUMBER=CONTACT_TB.PHONE_NUMBER "
				+ "AND CONTACT_TB.PHONE_NUMBER =?";
		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, uniqueValue);
			rs = ps.executeQuery();

			if (rs.next() == true) {
				do {
					client.setFirstName(rs.getString("FIRST_NAME"));
					client.setLastName(rs.getString("LAST_NAME"));
					client.setGender(rs.getString("GENDER"));
					Contact contact = new Contact();
					contact.setPhoneNumber(rs.getString("PHONE_NUMBER"));
					contact.setEmail(rs.getString("EMAIL"));
					// setting up address properties
					Address address = new Address();
					address.setAddressType(rs.getString("ADDR_TYPE"));
					address.setDescription(rs.getString("ADDR_DESCRIPTION"));
					address.setLocalGovt(rs.getString("ADDR_LOCAL_GOVT"));
					address.setState(rs.getString("ADDR_STATE"));
					address.setCountry(rs.getString("ADDR_COUNTRY"));
					// setting up address of contact
					contact.setAddress(address);
					// setting up client's contact
					client.setContact(contact);
					// setting up client's mesage
					client.setSuccessMessage("client's record successfully fetched!");
				} while (rs.next());
			} else {

				client.setSuccessMessage("No client with the provided Phone number Id in the system.");

			}

			return client;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			client.setErrorMessage("Error in attempt to fetch client's details\n:"
					+ e.getMessage());
			return client;
		} finally {
			connector.release(ps);
			connector.release(rs);
			connector.release(con);
		}
	}

	@Override
	public void setRole(String role) {
		// TODO Auto-generated method stub
		this.role = role;
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public Client editDetails(String username, String password,
			String firstName, String lastName, String gender, String email,
			String phoneNumber, String addrCountry, String addrDesc,
			String addrType, String addrState, String addrLocalGvt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client retrievePassword(String uniqueValue) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT PASS_WORD from USERS where PHONE_NUMBER = ? or EMAIL = ?";
		
		return null;
	}

	public static void main(String args[]) throws SQLException {

		System.out.println("before the call");

		ClientDao nb = new ClientDao();
		Gson gson = new Gson();
		// setting up client
		Client client = new Client();
		client.setFirstName("Seyi");
		client.setLastName("Adewale");
		// setting up user credentials
		client.setUserCredentials(new Credentials("", "seyiwale111"));
		client.setGender("male");
		// setting up contact
		Contact contact = new Contact();
		contact.setAddress(new Address("Home", "10,Adelaja street,Iyana Ipaja",
				"Alimosho", "Lagos", "Nigeria"));
		contact.setEmail("seyi@rocketmail.com");
		contact.setPhoneNumber("08126353500");
		client.setContact(contact);
		System.out.println("\n\ngson request format\n\n" + gson.toJson(client)
				+ "\n\n");
		System.out.println(gson.toJson(nb.addNewClient(client)));

		System.out.println("after the call");

		System.out.println("Fetch client by phone number:\n"
				+ gson.toJson(nb.getClientByPhoneNumber("08126353500")));
	}

}
