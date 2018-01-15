package com.kazmani.access;

import java.io.Serializable;

import com.kazmani.user.User;

/**
 * The Entry point into the kazmani System to be accessed by Admins,Therapists
 * and Clients
 * 
 * @author Earnest
 * 
 */
public class Login implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private User user;
    private int errorCode;
    private String message;
    private String errorMessage;

    /**
     * @return the user
     */
    public User getUser() {
	return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
	return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
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
    public void setMessage(String message) {
	this.message = message;
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

}
