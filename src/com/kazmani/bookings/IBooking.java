package com.kazmani.bookings;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBooking {

	public Booking getLastBookingByClientId(String phoneNumber);

	public Booking bookMassage(/*String clientPhoneNumber, String lengthTime,
			String sessionType, Date date, String therapistId,
			String massageId, String timeSpecified, String status,
			double latitude, double longitude, int defaultAddressChecker*/Booking bk)
			throws SQLException;

	public ArrayList<Booking> getAllBookingsByClientId(String phoneNumber);

	public Booking editBookedMassage(String bookingId);

	Booking editBookedMassage(Booking bk);

}
