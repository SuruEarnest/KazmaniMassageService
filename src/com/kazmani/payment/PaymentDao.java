package com.kazmani.payment;

import java.sql.Connection;     
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kazmani.config.Cypherizer;
import com.kazmani.config.DbConnector;

public class PaymentDao implements IPayment {
    
    private DbConnector connector = new DbConnector();
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public PaymentData makePayment(String senderId, String senderCardNumber,
	    String senderCardExpirationDate, String senderCardVerificationCode,
	    String beneficiaryBank, String beneficiaryAcctNum,
	    String beneficiaryPhoneNumber, double amount, Date transactionDate,
	    String paymentDescription) throws SQLException {

	PaymentData payData = new PaymentData();
	conn = connector.makeConnection();

	String sql = "insert into PAYMENT_TB"
		+ "(PAYER_ID,CARD_NUMBER,CARD_EXP_DATE,CARD_CVC,BEN_BANK,"
		+ "BEN_ACCT_NUMBER,BEN_PHONE_NUMBER,AMOUNT,TRANSACTION_DATE,PAYMENT_DESC) "
		+ "values (?,?,?,?,?,?,?,?,?,?)";

	String cardNumber = Cypherizer.enCrypt(senderCardNumber);
	String cvc = Cypherizer.enCrypt(senderCardVerificationCode);
	String expDate = Cypherizer.enCrypt(senderCardExpirationDate);

	try {
	    conn.setAutoCommit(false);
	    pst = conn.prepareStatement(sql);
	    pst.setString(1, senderId);
	    pst.setString(2, cardNumber);
	    pst.setString(3, expDate);
	    pst.setString(4, cvc);
	    pst.setString(5, beneficiaryBank);
	    pst.setString(6, beneficiaryAcctNum);
	    pst.setString(7, beneficiaryPhoneNumber);
	    pst.setDouble(8, amount);
	    pst.setDate(9, transactionDate);
	    pst.setString(10, paymentDescription);
	    pst.executeUpdate();
	    conn.commit();
	    payData.setMessage("Payment Successful!");
	    return payData;

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    conn.rollback();
	    payData.setErrorMessage("Failed:" + e.getMessage());
	    e.printStackTrace();
	    return payData;
	} finally {
	    connector.release(pst);
	    connector.release(conn);

	}

    }

    /**
     * @param payerId
     *            is the phoneNumber of the payer
     */
    @Override
    public ArrayList<PaymentData> getPaymentsListById(String payerId) {
	// TODO Auto-generated method stub
	ArrayList<PaymentData> payList = new ArrayList<>();
	conn = connector.makeConnection();
	PaymentData payData = new PaymentData();
	try {
	    String sql = "SELECT * FROM PAYMENT_TB where sender_id = ? order by TRANSACTION_DATE ";

	    pst = conn.prepareStatement(sql);
	    pst.setString(1, payerId);
	    rs = pst.executeQuery();

	    if (rs.next() == true) {
		do {

		    payData.setSenderId(rs.getString("SENDER_ID"));
		    payData.setSenderCardExpirationDate(rs
			    .getString("CARD_EXP_DATE"));
		    payData.setSenderCardNumber(rs.getString("CARD_NUMBER"));
		    payData.setSenderCardVerificationCode(rs
			    .getString("CARD_CVC"));
		    payData.setTransactionDate(rs.getDate("TRANSACTION_DATE"));
		    payData.setAmount(rs.getDouble("AMOUNT"));
		    payData.setBeneficiaryAcctNum(rs
			    .getString("BEN_ACCT_NUMBER"));
		    payData.setBeneficiaryBank(rs.getString("BEN_BANK"));
		    payData.setBeneficiaryPhoneNumber(rs
			    .getString("BEN_PHONE_NUMBER"));
		    payData.setPaymentDescription(rs.getString("PAYMET_DESC"));
		    payData.setMessage("SUCCESSFUL!");
		    payList.add(payData);

		} while (rs.next());
		return payList;
	    }
	    payData.setMessage("No payment done so far!");
	    payList.add(payData);
	    return payList;

	} catch (SQLException e) {
	    e.printStackTrace();
	    payData.setErrorMessage("FAILED:" + e.getMessage());
	    payList.add(payData);
	    return payList;
	}

    }

}
