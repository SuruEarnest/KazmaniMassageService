package com.kazmani.massage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.kazmani.config.DbConnector;

public class MassageDao implements IMassage {

    private DbConnector connector = new DbConnector();
    private Connection conn = null;
    private Massage massage = null;

    @Override
    public Massage AddNewMassage(String massageType, String massageDescription,
	    String adminId, Date dateTime) throws SQLException {

	massage = new Massage();
	conn = connector.makeConnection();
	PreparedStatement pst = null;

	try {
	    conn.setAutoCommit(false);
	    String sql = "insert into MASSAGE_TB(MASSAGE_TYPE,MASSAGE_DESC,ADMIN_ID,DATE_TIME) values (?,?,?,?)";
	    pst = conn.prepareStatement(sql);
	    pst.setString(1, massageType);
	    pst.setString(2, massageDescription);
	    pst.setString(3, adminId);
	    pst.setDate(4, dateTime);
	    pst.executeUpdate();
	    conn.commit();

	    massage.setMessage("Successfully added a new massage");
	    return massage;
	} catch (SQLException e) {
	    conn.rollback();
	    massage.setErrorMessage("Failed:" + e.getMessage());
	    e.printStackTrace();
	    return massage;
	} finally {
	    // release resources
	    connector.release(conn);
	    connector.release(pst);
	}

    }

    @Override
    public Massage getMassageById(String massageId) {
	// TODO Auto-generated method stub
	massage = new Massage();
	conn = connector.makeConnection();
	PreparedStatement pre = null;
	ResultSet rs = null;
	try {

	    String sql = "SELECT * from MASSAGE_TB where MASSAGE_ID = ?";
	    pre = conn.prepareStatement(sql);
	    pre.setString(1, massageId);
	    rs = pre.executeQuery();

	    if (rs.next()) {

		while (rs.next()) {
		    massage.setMassageId(rs.getString("MASSAGE_ID"));
		    massage.setMassageType(rs.getString("MASSAGE_TYPE"));
		    massage.setMassageDescription(rs.getString("MASSAGE_DESC"));

		}
	    }
	    return massage;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return massage;
	} finally {
	    // release resources
	    connector.release(conn);
	    connector.release(rs);
	    connector.release(pre);
	}

    }

    @Override
    public Massage editMassageById(String massageId, String adminId,
	    String massageType, String massageDesc) throws SQLException {
	// TODO Auto-generated method stub
	conn = connector.makeConnection();
	PreparedStatement ps = null;
	massage = new Massage();
	try {
	    conn.setAutoCommit(false);
	    String sql = "UPDATE MASSAGE_TB SET MASSAGE_TYPE=?,MASSAGE_DESC=? "
		    + "where MASSAGE_ID=? and ADMIN_ID = ?";
	    ps = conn.prepareStatement(sql);
	    ps.setString(1, massageType);
	    ps.setString(2, massageDesc);
	    ps.setString(3, massageId);
	    ps.setString(4, adminId);
	    ps.executeUpdate();
	    massage.setMessage("Massage details successfully edited!");
	    conn.commit();
	    return massage;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    conn.rollback();
	    massage.setErrorMessage("Error while trying to edit massage details!");
	    e.printStackTrace();
	    return massage;
	}

    }

    @Override
    public ArrayList<Massage> getMassageList() throws SQLException {
	// TODO Auto-generated method stub

	ArrayList<Massage> massageList = new ArrayList<>();

	String sql = "SELECT * from MASSAGE_TB";
	conn = connector.makeConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
	    pst = conn.prepareStatement(sql);

	    rs = pst.executeQuery();

	    if (rs.next()) {

		while (rs.next()) {
		    Massage massage = new Massage();
		    massage.setMassageDescription(rs.getString("MASSAGE_DESC"));
		    massage.setMassageType(rs.getString("MASSAGE_TYPE"));
		    // massage.setSuccessMessage("Successfully selected!");
		    massageList.add(massage);

		}
	    }

	    return massageList;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    Massage errMassage = new Massage();
	    errMassage.setErrorMessage("Failed:" + e.getMessage());
	    massageList.add(errMassage);
	    e.printStackTrace();

	    return massageList;

	} finally {
	    // release resources
	    connector.release(conn);
	    connector.release(pst);
	    connector.release(rs);
	}

    }

    public static void main(String[] args) throws SQLException {

	Gson gson = new Gson();
	System.out.println("JSON format:\n"
		+ gson.toJson(new MassageDao().getMassageList()));
	System.out.println("JSON format:\n"
		+ gson.toJson(new MassageDao().getMassageById("")));

    }

}
