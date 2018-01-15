package com.kazmani.qualification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public Qualification deleteQualificationById(String phoneNumberOrEmail)
	    throws SQLException {
	// TODO Auto-generated method stub
	PreparedStatement pst = null;
	Qualification q = new Qualification();
	String query = "DELETE from QUALIFICATIONS_TB where PHONE_NUMBER=? or EMAIL=? ";
	try {
	    conn = connector.makeConnection();
	    conn.setAutoCommit(false);
	    pst = conn.prepareStatement(query);
	    pst.setString(1, phoneNumberOrEmail);
	    pst.setString(1, phoneNumberOrEmail);
	    pst.executeUpdate();
	    conn.commit();
	    q.setMessage("qualification has been successfully deleted!");
	    return q;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    conn.rollback();
	    q.setErrorMessage("Error caused due to:" + e.getMessage());
	    e.printStackTrace();
	    return q;
	} finally {
	    connector.release(conn);
	    connector.release(pst);
	}

    }

    @Override
    public Qualification getQualificationById(String phoneNumberOrEmail) {
	// TODO Auto-generated method stub
	String query = "SELECT * from QUALIFICATIONS_TB where EMAil=? or PHONE_NUMBER=?";
	PreparedStatement pst = null;
	ResultSet rs = null;
	Qualification q = new Qualification();
	conn = connector.makeConnection();
	try {
	    pst = conn.prepareStatement(query);
	    pst.setString(1, phoneNumberOrEmail);
	    pst.setString(2, phoneNumberOrEmail);
	    rs = pst.executeQuery();

	    if (rs.next() == true) {
		do {

		    q.setDiscipline(rs.getString("DISCIPLINE"));
		    q.setEmail(rs.getString("EMAIL"));
		    q.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		    q.setType(rs.getString("TYPE"));
		    q.setYear(rs.getString("YEAR"));
		    q.setMessage("successfully fetched!");
		    
		} while (rs.next());

		
	    } else {
		q.setMessage("No qualification matched the user's Id");
	    }
	    return q;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    q.setErrorMessage("Error happened due to:"+e.getMessage());
	    e.printStackTrace();
	    return q;
	}
	finally{
	    connector.release(conn);
	    connector.release(pst);
	    connector.release(rs);
	}
	
    }

}
