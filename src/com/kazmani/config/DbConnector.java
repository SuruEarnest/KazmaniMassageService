package com.kazmani.config;

import java.sql.CallableStatement; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

/**
 * This is the connector class that powers the kazmani's database engine
 * 
 * @author Earnest Erihbra Suru
 * 
 */
public class DbConnector {

	private static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
	private static String driverName = "oracle.jdbc.driver.OracleDriver";
	private static String username = "kazmani";
	private static String password = "kazmani101";
	private Connection conn = null;

	/**
	 * 
	 * @return a connection object used to access the database
	 */
	public Connection makeConnection() {
		try {

			TimeZone timeZone = TimeZone.getTimeZone("UTC+1");
			TimeZone.setDefault(timeZone);

			System.out.println("This is makeConnection method");
			Class.forName(driverName);
			System.out.println("passed driver name...");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("connection succesful");

		} catch (Exception ex) {
			// log an exception. for example:
			ex.printStackTrace();

		}

		return conn;
	}

	/**
	 * Helps to release database resources so as to avoid memory leakages
	 * 
	 * @param ob
	 */
	public void release(Object ob) {

		try {
			if (ob instanceof ResultSet && !ob.equals(null)) {

				((ResultSet) ob).close();
			} else if (ob instanceof PreparedStatement && !ob.equals(null)) {
				((PreparedStatement) ob).close();
			} else if (ob instanceof Statement && !ob.equals(null)) {
				((Statement) ob).close();
			} else if (ob instanceof Connection && !ob.equals(null)) {
				((Connection) ob).close();
			} else if (ob instanceof CallableStatement && !ob.equals(null)) {
				((CallableStatement) ob).close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {

		System.out.println("Inside of the main method:");
		DbConnector db = new DbConnector();
		db.makeConnection();

	}
}
