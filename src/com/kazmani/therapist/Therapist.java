package com.kazmani.therapist;

import java.util.ArrayList; 

import com.kazmani.contact.Contact;
import com.kazmani.languages.Language;
import com.kazmani.photo.Photo;
import com.kazmani.qualification.Qualification;
import com.kazmani.user.User;

/**
 * 
 * @author Earnest-Suru Erihbra
 * 
 */
public class Therapist extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	// don't forget that contact has email,phoneNumber and Address object
	private Contact contact;
	// profileText is a short text where the therapist describes his
	// specialities to convince clients
	private String profileText;
	// status has two possible values,status={validated,unvalidated}
	private String status;
	// profileSetUpPercentage assigns a percentage to the therapist as he/she
	// sets up his/her profile
	private int profileSetUpPercentage;
	// a therapist might be able to speak more than one language
	private ArrayList<Language> languages;
	private Photo photo;
	private String gender;

	private String stateOfOrigin;
	private String localGovt;
	private String country;
	private int age;

	// private int rating;
	private Qualification qualification;
	private String errorMessage;
	private int errorCode;
	private String message;

	/**
	 * @param firstName
	 * @param lastName
	 * @param contact
	 * @param profileText
	 * @param status
	 * @param gender
	 * @param stateOfOrigin
	 * @param localGovt
	 * @param country
	 */
	public Therapist(String firstName, String lastName, Contact contact,
			String profileText, String status, String gender,int age,
			String stateOfOrigin, String localGovt, String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.profileText = profileText;
		this.status = status;
		this.gender = gender;
		this.age = age;
		this.stateOfOrigin = stateOfOrigin;
		this.localGovt = localGovt;
		this.country = country;
		
	}

	public Therapist() {

	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<Language> languages) {
		this.languages = languages;
	}

	/*
	 * About Rating: 1- poor 2- fair 3-good 4-very good 5-excellent
	 */
	/*
	 * public int getRating() { return rating; }
	 * 
	 * public void setRating(int rating) { this.rating = rating; }
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getProfileText() {
		return profileText;
	}

	public void setProfileText(String profileText) {
		this.profileText = profileText;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;

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
	 * @return the profileSetUpPercentage
	 */
	
	  public int getProfileSetUpPercentage()
	  { 
		  return profileSetUpPercentage; 
	  }
	 /**
	 * @param profileSetUpPercentage
	 *            the profileSetUpPercentage to set
	 */
	
	 public void setProfileSetUpPercentage(int profileSetUpPercentage) 
	 {
	  this.profileSetUpPercentage = profileSetUpPercentage; 
	  }
	 

	/**
	 * @return the stateOfOrigin
	 */
	public String getStateOfOrigin() {
		return stateOfOrigin;
	}

	/**
	 * @param stateOfOrigin
	 *            the stateOfOrigin to set
	 */
	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
	}

	/**
	 * @return the localGovt
	 */
	public String getLocalGovt() {
		return localGovt;
	}

	/**
	 * @param localGovt
	 *            the localGovt to set
	 */
	public void setLocalGovt(String localGovt) {
		this.localGovt = localGovt;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the age
	 */

	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */

	public void setAge(int age) {
		this.age = age;
	}

	public void setErrorMessage(String errorMessage) {
		// TODO Auto-generated method stub
		this.errorMessage = errorMessage;
	}

	public void setMessage(String successMessage) {
		// TODO Auto-generated method stub
		this.message = successMessage;

	}

	public void setErrorCode(int errorCode) {
		// TODO Auto-generated method stub
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



}
