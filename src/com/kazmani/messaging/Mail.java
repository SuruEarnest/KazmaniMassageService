package com.kazmani.messaging;

public class Mail {

    private String subject;
    private String body;
    private String senderEmail;
    private String recipientEmail;
    private String errorMessage;
    private String message;

    public Mail() {

    }

    public Mail(String subject,String body, String senderEmail,
	    String recipientEmail) {
	super();

	this.subject = subject;
	this.body = body;
	this.senderEmail = senderEmail;
	this.recipientEmail = recipientEmail;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getSenderEmail() {
	return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
	this.senderEmail = senderEmail;
    }

    public String getRecipientEmail() {
	return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
	this.recipientEmail = recipientEmail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
