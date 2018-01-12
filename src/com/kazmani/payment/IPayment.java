package com.kazmani.payment;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IPayment {

	public PaymentData makePayment(String payerId,String senderCardNumber,String senderCardExpirationDate,
			String senderCardVerificationCode,String beneficiaryBank,
			String beneficiaryAcctNum,String beneficiaryPhoneNumber,
			double amount,Date transactionDate,String paymentDescription) throws SQLException;
	
	public ArrayList<PaymentData> getPaymentsListById(String payerId);
}
