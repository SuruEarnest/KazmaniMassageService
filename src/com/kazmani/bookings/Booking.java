package com.kazmani.bookings;

import java.io.Serializable;
import java.util.Date;

import com.kazmani.address.Location;
import com.kazmani.massage.Massage;
import com.kazmani.therapist.Therapist;

public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;
	private String clientPhoneNumber;
	// a user requests for massage..this can be a client/therapist who wants a
	// massage as well
	private Massage massage;
	private String therapistId;
	Therapist chosenTherapist;
	private Location MassageRequestLocation;
	/*
	 * this could be more than one for a group massage or for a group massage or
	 * couples
	 */
	private Date date;
	private String lengthOfTime; // in minutes
	private String sessionType;//

	private int defaultAddressChecker;
	private String status, timeSpecified;
	private String message, errorMessage;

	public Booking() {

	}

	public Booking(Massage massage, String therapistId, Date date,
			String lengthOfTime, String sessionType) {
		super();
		this.massage = massage;
		/* this.chosenTherapist = chosenTherapist; */
		this.therapistId = therapistId;
		this.date = date;
		this.lengthOfTime = lengthOfTime;
		this.sessionType = sessionType;
	}

	public Massage getMassage() {
		return massage;
	}

	public void setMassage(Massage massage) {
		this.massage = massage;
	}

	public Therapist getChosenTherapist() {
		return chosenTherapist;
	}

	public void setChosenTherapist(Therapist chosenTherapist) {
		this.chosenTherapist = chosenTherapist;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLengthOfTime() {
		return lengthOfTime;
	}

	public void setLengthOfTime(String lengthOfTime) {
		this.lengthOfTime = lengthOfTime;
	}

	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	/**
	 * @return the clientPhoneNumber
	 */
	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}

	/**
	 * @param clientPhoneNumber
	 *            the clientPhoneNumber to set
	 */
	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}

	/**
	 * @return the therapistId
	 */
	public String getTherapistId() {
		return therapistId;
	}

	/**
	 * @param therapistId
	 *            the therapistId to set
	 */
	public void setTherapistId(String therapistId) {
		this.therapistId = therapistId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String successMessage) {
		this.message = successMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the timeSpecified
	 */
	public String getTimeSpecified() {
		return timeSpecified;
	}

	/**
	 * @param timeSpecified
	 *            the timeSpecified to set
	 */
	public void setTimeSpecified(String timeSpecified) {
		this.timeSpecified = timeSpecified;
	}

	/**
	 * @return the massageRequestLocation
	 */
	public Location getMassageRequestLocation() {
		return MassageRequestLocation;
	}

	/**
	 * @param massageRequestLocation
	 *            the massageRequestLocation to set
	 */
	public void setMassageRequestLocation(Location massageRequestLocation) {
		MassageRequestLocation = massageRequestLocation;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the defaultAddressChecker
	 */
	public int getDefaultAddressChecker() {
		return defaultAddressChecker;
	}

	/**
	 * @param defaultAddressChecker
	 *            the defaultAddressChecker to set
	 */
	public void setDefaultAddressChecker(int defaultAddressChecker) {
		this.defaultAddressChecker = defaultAddressChecker;
	}

	public static void main(String args[]) {

	}

}
