package com.kazmani.client;

import com.kazmani.contact.Contact;
import com.kazmani.photo.Photo;
import com.kazmani.user.User;


public class Client extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Photo profilePhoto;
	private String firstName;
	private String LastName;
	private Contact contact;
	// contains email,address and phone number
	private String gender;
	private String errorMessage;
	private String successMessage;
    private int errorCode;
	
	public Client()
	{
		
	}
	public Client(Photo profilePhoto, String firstName, String lastName,
			Contact contact, String gender) {

		this.profilePhoto = profilePhoto;
		this.firstName = firstName;
		LastName = lastName;
		this.contact = contact;
		this.gender = gender;
	}

	public Client(String firstName, String lastName, Contact contact,
			String gender) {

		this.firstName = firstName;
		LastName = lastName;
		this.contact = contact;
		this.gender = gender;
	}

	public Photo getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(Photo profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	/**
	 * @return the message
	 */
	public String getSuccessMessage() {
		return successMessage;
	}
	/**
	 * @param message the message to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
