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
    public Qualification updateQualificationById(Qualification q) {
	// TODO Auto-generated method stub
	Qualification qualification = new Qualification();
	PreparedStatement qSt = null;
	String query = "UPDATE QUALIFICATIONS_TB SET DISCIPLINE=?,TYPE=?,YEAR=? where EMAIL=? OR PHONE_NUMBER=? ";
	conn = connector.makeConnection();
	try {
	    conn.setAutoCommit(false);
	    qSt = conn.prepareStatement(query);
	    qSt.setString(1, q.getDiscipline());
	    qSt.setString(2, q.getType());
	    qSt.setString(3, q.getYear());
	    qSt.setString(4, q.getId());
	    qSt.setString(5, q.getId());
	    qSt.executeUpdate();
	    conn.commit();
	    qualification.setMessage("qualifications successfully updated!");
	    return qualification;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    qualification.setErrorMessage("Error occured due to:"+e.getMessage());
            return qualification;
            
	} finally {
	    connector.release(qSt);
	    connector.release(conn);

	}
	

    }

    @Override
    public Qualification deleteQualificationById(String phoneNumberOrEmail)
	    throws SQLException {
	// TODO Auto-generated method stub
	PreparedStatement pst = null;
	Qualification qualification = new Qualification();
	String query = "DELETE from QUALIFICATIONS_TB where PHONE_NUMBER=? or EMAIL=? ";
	try {
	    conn = connector.makeConnection();
	    conn.setAutoCommit(false);
	    pst = conn.prepareStatement(query);
	    pst.setString(1, phoneNumberOrEmail);
	    pst.setString(1, phoneNumberOrEmail);
	    pst.executeUpdate();
	    conn.commit();
	    qualification.setMessage("qualification has been successfully deleted!");
	    return qualification;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    conn.rollback();
	    qualification.setErrorMessage("Error caused due to:" + e.getMessage());
	    e.printStackTrace();
	    return qualification;
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
	Qualification qualification = new Qualification();
	conn = connector.makeConnection();
	try {
	    pst = conn.prepareStatement(query);
	    pst.setString(1, phoneNumberOrEmail);
	    pst.setString(2, phoneNumberOrEmail);
	    rs = pst.executeQuery();

	    if (rs.next() == true) {
		do {

		    qualification.setDiscipline(rs.getString("DISCIPLINE"));
		    qualification.setEmail(rs.getString("EMAIL"));
		    qualification.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		    qualification.setType(rs.getString("TYPE"));
		    qualification.setYear(rs.getString("YEAR"));
		    qualification.setMessage("successfully fetched!");

		} while (rs.next());

	    } else {
		qualification
			.setMessage("No qualification matched the user's Id");
	    }
	    return qualification;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    qualification.setErrorMessage("Error happened due to:"
		    + e.getMessage());
	    e.printStackTrace();
	    return qualification;
	} finally {
	    connector.release(conn);
	    connector.release(pst);
	    connector.release(rs);
	}

    }

}
