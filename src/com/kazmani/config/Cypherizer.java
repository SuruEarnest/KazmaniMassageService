package com.kazmani.config;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Cypherizer {

    public static String deCrypt(String encryptedText) {
	String decrypted = null;
	try {
	    String key = "MobileMassage1.0"; // 128 bit encryption key
	    // Create key and cipher
	    Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	    Cipher cipher = Cipher.getInstance("AES");
	    // decrypt the text
	    cipher.init(Cipher.DECRYPT_MODE, aesKey);
	    byte[] decodedText = DatatypeConverter
		    .parseBase64Binary(encryptedText);
	    decrypted = new String(cipher.doFinal(decodedText));

	} catch (Exception e) {
	    System.out.println("Error in decryption:" + e.getMessage());

	}
	return decrypted;

    }

    public static String enCrypt(String originalText) {
	String encodedText = null;
	try {
	    String key = "MobileMassage1.0"; // 128 bit encryption key

	    // Create key and cipher
	    Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	    Cipher cipher = Cipher.getInstance("AES");

	    // encrypt the text
	    cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	    byte[] encrypted = cipher.doFinal(originalText.getBytes());
	    encodedText = DatatypeConverter.printBase64Binary(encrypted);
	} catch (Exception e) {
	    System.out.println("Error in encryption:" + e.getMessage());
	}
	return encodedText;
    }

    public static void main(String args[]) {
	System.out.println(Cypherizer.deCrypt("q3h+sVArsCOZDIl3Me1ePA=="));
    }
}
