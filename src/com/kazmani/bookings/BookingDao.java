package com.kazmani.bookings;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kazmani.address.Location;
import com.kazmani.config.DbConnector;
import com.kazmani.massage.MassageDao;
import com.kazmani.therapist.TherapistDao;

public class BookingDao implements IBooking {

    private DbConnector connector = new DbConnector();
    private Connection conn = null;
    private PreparedStatement pre = null, ps = null;
    private ResultSet rs = null;

    public Booking bookMassage(/*
			        * String clientPhoneNumber, String timeLength,
			        * String sessionType, Date date, String
			        * therapistId, String massageId, String
			        * timeSpecified, String status, double latitude,
			        * double longitude, int defaultAddressChecker
			        */Booking bk) throws SQLException {

	Booking bkRespnse = new Booking();
	String sql = "insert into BOOKINGS_TB(CLIENT_PHONE,MASSAGE_ID,"
		+ "THERAPIST_ID,MS_DATE,TIME_LENGTH,"
		+ "TIME_SPECIFIED,SESSION_TYPE,STATUS,LOCATION) "
		+ "values (?,?,?,?,?,?,?,?,?)";
	conn = connector.makeConnection();
	try {
	    // set the auto-commit to false
	    conn.setAutoCommit(false);
	    pre = conn.prepareStatement(sql);
	    pre.setString(1, bk.getClientPhoneNumber());
	    pre.setString(2, bk.getMassage().getMassageId());
	    pre.setString(3, bk.getTherapistId());
	    pre.setDate(4, (Date) bk.getDate());
	    pre.setString(5, bk.getLengthOfTime());
	    pre.setString(6, bk.getTimeSpecified());
	    pre.setString(7, bk.getSessionType());
	    pre.setString(8, bk.getStatus());

	    String locationString = ""
		    + bk.getMassageRequestLocation().getLatitude() + ","
		    + bk.getMassageRequestLocation().getLongitude() + "";
	    pre.setString(9, locationString);
	    pre.setInt(10, bk.getDefaultAddressChecker());
	    pre.executeUpdate();
	    // commit changes to database
	    conn.commit();
	    // I am also supposed to add a block of code that
	    // will send notification to the admin of this request

	    bkRespnse
		    .setMessage("Massage-Booking Successful,we'd get back to you right away!");
	    return bkRespnse;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    // roll-back changes
	    conn.rollback();
	    bkRespnse.setErrorMessage("Error occured due to:" + e.getMessage());
	    return bkRespnse;
	} finally {
	    // release resources
	    connector.release(conn);
	    connector.release(pre);

	}

    }

    public Booking getLastBookingByClientId(String phoneNumber) {

	Booking bk = new Booking();
	String sql = "select * from BOOKINGS_TB where MS_DATE = (select max(MS_DATE) "
		+ "from BOOKING_TB where CLIENT_PHONE=?)";
	conn = connector.makeConnection();

	try {

	    pre = conn.prepareStatement(sql);
	    pre.setString(1, phoneNumber);
	    rs = pre.executeQuery();

	    if (rs.next() == true) {
		do {

		    bk.setDate(rs.getDate("MS_DATE"));
		    bk.setClientPhoneNumber(rs.getString("CLIENT_PHONE"));
		    bk.setMassage(new MassageDao().getMassageById(rs
			    .getString("MASSAGE_ID")));
		    bk.setChosenTherapist(new TherapistDao()
			    .getTherapistById(rs
				    .getString("THERAPIST_PHONE_NUMBER")));
		    bk.setLengthOfTime(rs.getString(rs.getString("TIME_LENGTH")));
		    bk.setSessionType(rs.getString("SESSION_TYPE"));
		    bk.setStatus(rs.getString("STATUS"));
		    bk.setTimeSpecified(rs.getString("TIME_SPECIFIED"));
		    String locationStringFrmDb = rs.getString("LOCATION");
		    String[] location = locationStringFrmDb.trim().split(",");
		    double latitude = Double.parseDouble(location[0]);
		    double longitude = Double.parseDouble(location[1]);
		    Location massageLoc = new Location(latitude, longitude);
		    bk.setMassageRequestLocation(massageLoc);
		    bk.setDefaultAddressChecker(rs
			    .getInt("DEFAULT_ADDRESS_CHECK"));
		    bk.setMessage("SUCCESS!");
		} while (rs.next());
	    } else {
		bk.setMessage("No result found!");
	    }
	    return bk;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    bk.setErrorMessage("Error occured due to:" + e.getMessage());
	    return bk;
	} finally {
	    connector.release(conn);
	    connector.release(rs);
	    connector.release(pre);
	}

    }

    public ArrayList<Booking> getAllBookingsByClientId(String phoneNumber) {
	ArrayList<Booking> bookingsList = new ArrayList<>();
	conn = connector.makeConnection();

	String sql = "SELECT * from BOOKINGS_TB where CLIENT_PHONE = ?";
	try {
	    ps = conn.prepareStatement(sql);
	    rs = ps.executeQuery();
	    if (rs.next()) {
		do {
		    Booking bk = new Booking();
		    bk.setDate(rs.getDate("MS_DATE"));
		    bk.setClientPhoneNumber(rs.getString("CLIENT_PHONE"));
		    bk.setMassage(new MassageDao().getMassageById(rs
			    .getString("MASSAGE_ID")));
		    bk.setChosenTherapist(new TherapistDao()
			    .getTherapistById("THERAPIST_PHONE_NUMBER"));
		    bk.setLengthOfTime(rs.getString(rs.getString("TIME_LENGTH")));
		    bk.setSessionType(rs.getString("SESSION_TYPE"));
		    bk.setStatus(rs.getString("STATUS"));
		    bk.setTimeSpecified(rs.getString("TIME_SPECIFIED"));
		    String locationStringFrmDb = rs.getString("LOCATION");
		    String[] location = locationStringFrmDb.trim().split(",");
		    double latitude = Double.parseDouble(location[0]);
		    double longitude = Double.parseDouble(location[1]);
		    Location massageLoc = new Location(latitude, longitude);
		    bk.setMassageRequestLocation(massageLoc);
		    bk.setDefaultAddressChecker(rs
			    .getInt("DEFAULT_ADDRESS_CHECK"));
		    bk.setMessage("SUCCESS!");
		    bookingsList.add(bk);
		} while (rs.next());

	    }
	    return bookingsList;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return bookingsList;
	} finally {
	    connector.release(rs);
	    connector.release(ps);
	    connector.release(conn);
	}

    }

    @Override
    public Booking editBookedMassage(String bookingId) {
	// TODO Auto-generated method stub
	return null;
    }

    public static void main(String args[]) {

	Booking bk = new Booking();
	bk.setTherapistId("");
	bk.setClientPhoneNumber("");

    }
}
