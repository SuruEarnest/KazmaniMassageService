package com.kazmani.qualification;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kazmani.config.DbConnector;

public class QualificationDao implements IQualification {

	DbConnector connector = new DbConnector();
	private Connection conn = null;
	
	
	@Override
	public Qualification addQualification(Qualification q) throws SQLException {
		// TODO Auto-generated method stub
		Qualification qualification = new Qualification();
		PreparedStatement qSt = null;
		try {
			String query = "INSERT into QUALIFICATIONS_TB(EMAIL,PHONE_NUMBER,DISCIPLINE,TYPE,YEAR) VALUES(?,?,?,?,?)";
			conn = connector.makeConnection();
			conn.setAutoCommit(false);
			qSt = conn.prepareStatement(query);
			qSt.setString(1, q.getEmail());
			qSt.setString(2, q.getPhoneNumber());
			qSt.setString(3, q.getDiscipline());
			qSt.setString(4, q.getType());
			qSt.setString(5, q.getYear());
			qSt.executeUpdate();
			conn.commit();

			System.out.println("Qualification Commited!");
			qualification.setMessage("qualification added successfully!");
			
			return qualification;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
			qualification.setErrorMessage("FAILED!" + e.getMessage());
			e.printStackTrace();
			return qualification;
		} finally {
			connector.release(qSt);
			connector.release(conn);
		}

	}


	@Override
	public Qualification updateQualificationById(String phoneNumberOrEmail,
			Qualification q) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Qualification deleteQualificationById(String phoneNumberOrEmail) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Qualification getQualificationById(String phoneNumberOrEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
