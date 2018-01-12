package com.kazmani.payment;

import java.io.Serializable;      
import java.sql.Date;

public class PaymentData implements Serializable {

	private static final long serialVersionUID = 1L;

	String senderId;
	String senderCardNumber;
	String senderCardExpirationDate;
	String senderCardVerificationCode;
	String beneficiaryBank;
	String beneficiaryAcctNum;
	String beneficiaryPhoneNumber;
	double amount;
	Date transactionDate;
	String paymentDescription;
	
	String message;
	String errorMessage;
	
	
	/**
	 * @param payerId
	 * @param senderCardNumber
	 * @param senderCardExpirationDate
	 * @param senderCardVerificationCode
	 * @param beneficiaryBank
	 * @param beneficiaryAcctNum
	 * @param beneficiaryPhoneNumber
	 * @param amount
	 * @param transactionDate
	 * @param message
	 * @param errorMessage
	 */

	public PaymentData(String senderId, String senderCardNumber,
			String senderCardExpirationDate, String senderCardVerificationCode,
			String beneficiaryBank, String beneficiaryAcctNum,
			String beneficiaryPhoneNumber, double amount,
			Date transactionDate,String paymentDescription ,String message, String errorMessage) {

		this.senderId = senderId;
		this.senderCardNumber = senderCardNumber;
		this.senderCardExpirationDate = senderCardExpirationDate;
		this.senderCardVerificationCode = senderCardVerificationCode;
		this.beneficiaryBank = beneficiaryBank;
		this.beneficiaryAcctNum = beneficiaryAcctNum;
		this.beneficiaryPhoneNumber = beneficiaryPhoneNumber;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.paymentDescription = paymentDescription;
		this.message = message;
		this.errorMessage = errorMessage;
	
	}

	public PaymentData() {
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


	/**
	 * @return the senderCardNumber
	 */
	public String getSenderCardNumber() {
		return senderCardNumber;
	}

	/**
	 * @param senderCardNumber
	 *            the senderCardNumber to set
	 */
	public void setSenderCardNumber(String senderCardNumber) {
		this.senderCardNumber = senderCardNumber;
	}

	/**
	 * @return the senderCardExpirationDate
	 */
	public String getSenderCardExpirationDate() {
		return senderCardExpirationDate;
	}

	/**
	 * @param senderCardExpirationDate
	 *            the senderCardExpirationDate to set
	 */
	public void setSenderCardExpirationDate(String senderCardExpirationDate) {
		this.senderCardExpirationDate = senderCardExpirationDate;
	}

	/**
	 * @return the senderCardVerificationCode
	 */
	public String getSenderCardVerificationCode() {
		return senderCardVerificationCode;
	}

	/**
	 * @param senderCardVerificationCode
	 *            the senderCardVerificationCode to set
	 */
	public void setSenderCardVerificationCode(String senderCardVerificationCode) {
		this.senderCardVerificationCode = senderCardVerificationCode;
	}

	/**
	 * @return the beneficiaryBank
	 */
	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}

	/**
	 * @param beneficiaryBank
	 *            the beneficiaryBank to set
	 */
	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}

	/**
	 * @return the beneficiaryAcctNum
	 */
	public String getBeneficiaryAcctNum() {
		return beneficiaryAcctNum;
	}

	/**
	 * @param beneficiaryAcctNum
	 *            the beneficiaryAcctNum to set
	 */
	public void setBeneficiaryAcctNum(String beneficiaryAcctNum) {
		this.beneficiaryAcctNum = beneficiaryAcctNum;
	}

	/**
	 * @return the beneficiaryPhoneNumber
	 */
	public String getBeneficiaryPhoneNumber() {
		return beneficiaryPhoneNumber;
	}

	/**
	 * @param beneficiaryPhoneNumber
	 *            the beneficiaryPhoneNumber to set
	 */
	public void setBeneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
		this.beneficiaryPhoneNumber = beneficiaryPhoneNumber;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the paymentDescription
	 */
	public String getPaymentDescription() {
		return paymentDescription;
	}

	/**
	 * @param paymentDescription the paymentDescription to set
	 */
	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
