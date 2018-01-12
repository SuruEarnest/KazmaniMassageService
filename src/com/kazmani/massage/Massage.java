package com.kazmani.massage;

import java.io.Serializable;

public class Massage  implements Serializable {

	/**
	 * 
	 */   
	private static final long serialVersionUID = 1L;
	private String massageId;// I could use time_stamp on the system for Ids
	private String massageType;//sms or mail
	private String massageDescription;
	private String message;
	private String errorMessage;

	public Massage()
	{
		
	}
	public Massage(String massageId, String massageType, String massageDesc) {
		super();
		this.massageId = massageId;
		this.massageType = massageType;
		this.massageDescription = massageDesc;
	}

	public String getMassageId() {
		return massageId;
	}

	public void setMassageId(String massageId) {
		this.massageId = massageId;
	}

	public String getMassageType() {
		return massageType;
	}

	public void setMassageType(String massageType) {
		this.massageType = massageType;
	}

	/**
	 * @return the massageDescription
	 */
	public String getMassageDescription() {
		return massageDescription;
	}

	/**
	 * @param massageDescription
	 *            the massageDescription to set
	 */
	public void setMassageDescription(String massageDescription) {
		this.massageDescription = massageDescription;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
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
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
