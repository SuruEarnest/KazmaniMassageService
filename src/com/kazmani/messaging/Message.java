package com.kazmani.messaging;

/*
 * To change this license header, choose License Headers in Project Properties.   
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;     
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author suru-earnest
 */
public class Message {

    public static String[] getRecipientNumbers(String phoneNumbers) {
        phoneNumbers = phoneNumbers.trim();//.trim() to remove white space characters

        // using regular expression to specify string splitting pattern
        String phoneNumbersTokens[] = phoneNumbers.split("[,]");// the phone
        // numbres are
        // expected to
        // be Strings
        // separated by
        // comma.
        //returns a String array,whose elements are the phone numbers
        return phoneNumbersTokens;
    }

    public static Object[] processNumbers(String phoneNumbers[]) {

        // gets the recipient Numbers arrays and converts the numbers into the
        // 234 format
        Set<String> numbersSet = new HashSet<>();
        //using HashSet to retain only one instance of a number entered twice or more.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < phoneNumbers.length; i++) {

            char[] no = phoneNumbers[i].toCharArray();
            sb.insert(0, no);

            if (phoneNumbers[i].startsWith("0")) {
                // remove the first character of the string and attach prefix
                // 234 to the remaining
                sb.deleteCharAt(0);
                sb.insert(0, "234");
                numbersSet.add(sb.toString());
            } else if (phoneNumbers[i].startsWith("+234")) {
                // just removes the +
                sb.deleteCharAt(0);
                numbersSet.add(sb.toString());
            }

            sb.setLength(0);// doing this sets the element in the StringBuilder
            // object to zero.i.e clears the String builder
            // object.

        }
        
        // converting the numbersSet into an array
        // of objects and returning the array
        return numbersSet.toArray();

    }

    public static String sendBulkMessages(String username, String password,
            String sender, Object recipientNumbers[], String textMessage,
            boolean scheduleMessage, int smsType) {
            String response = "";

        try {

            URL url = new URL("http://api.infobip.com/api/v3/sendsms/xml");//the gateway URL
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/xml");
            // this is always needed for POST or PUT HTTP method
            con.connect();

            StringBuilder req = new StringBuilder();

            if (scheduleMessage == true) {
                //specifying the XML format for scheduled messages
                req.append("<SMS>");
                req.append("<authentication>");
                req.append("<username>" + username + "</username>");
                req.append("<password>" + password + "</password>");
                req.append("</authentication>");
                req.append("<message>");
                req.append("<sender>" + sender + "</sender>");
                req.append("<text>" + textMessage + "</text>");
                req.append("<flash>" + smsType + "</flash>");
                req.append("<type>" + "LongSms" + "</type>");
                req.append("<sendDateTime>" + getScheduledDate(new Date()) + "</sendDateTime>");
                req.append("<recipients>");

                for (int i = 0; i < recipientNumbers.length; i++) {

                    req.append("<gsm>" + recipientNumbers[i] + "</gsm>");
                    //req.append("<gsm messageId="+i+">" + recipientNumbers[i] + "</gsm>");

                }

                req.append("</recipients>");
                req.append("</message>");
                req.append("</SMS>");

            } else {
                //specifying the XML format for instant messages
                req.append("<SMS>");
                req.append("<authentication>");
                req.append("<username>" + username + "</username>");
                req.append("<password>" + password + "</password>");
                req.append("</authentication>");
                req.append("<message>");
                req.append("<sender>" + sender + "</sender>");
                req.append("<text>" + textMessage + "</text>");
                req.append("<flash>" + smsType + "</flash>");
                req.append("<type>" + "LongSms" + "</type>");// enables sending
                // of long
                // sms...
                 req.append("<recipients>");

                for (int i = 0; i < recipientNumbers.length; i++) {

                    req.append("<gsm>" + recipientNumbers[i] + "</gsm>");
                    //	req.append("<gsm messageId="+i+">" + recipientNumbers[i] + "</gsm>");

                }
                req.append("</recipients>");

                req.append("</message>");
                req.append("</SMS>");

            }

            System.out.println("Printing the XML format request string:");
            System.out.println(req);

            OutputStream out = con.getOutputStream();
            out.write(req.toString().getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                response += line;
            }

        } catch (Exception e) {

            System.out.println("Error in connection");
            e.printStackTrace();
           
        }

        System.out.println("Printing the XML format response:");
        System.out.println(response);

        return response;
    }

    public static String getScheduledDate(Date date) {
        //this method simply helps to format the date values obtained from the DateField component into 
        //the string format specified by the infobip sms API.
        String schDate = "";

        Date selectedDate = date;
        Date currentDate = new Date();

        long diff = selectedDate.getTime() - currentDate.getTime();
        // long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        schDate = diffDays + "d" + diffHours + "h" + diffMinutes + "m";

        System.out.println(schDate);
        return schDate;
    }

    public void sendMails(String[] emails) {

        for (int i = 0; i < emails.length; i++) {
            
        }
    }

  

}
