package com.kazmani.messaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MessageData implements Serializable {

	private String messageText;
	private Date dateSent;
	private String senderId;
	private String recipientId;
	private String successMessage;
	private String errorMessage;
	
	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}
	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	/**
	 * @return the dateSent
	 */
	public Date getDateSent() {
		return dateSent;
	}
	/**
	 * @param dateSent the dateSent to set
	 */
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	/**
	 * @return the senderId
	 */
	public String getSenderId() {
		return senderId;
	}
	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	/**
	 * @return the recipientId
	 */
	public String getRecipientId() {
		return recipientId;
	}
	/**
	 * @param recipientId the recipientId to set
	 */
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
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
