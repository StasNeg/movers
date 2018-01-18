package com.tomove.controller;

/**
 * Created by administrator on 19/12/16.
 */

        import java.io.*;
        import java.net.HttpURLConnection;
        import java.net.URLEncoder;
        import java.net.URL;


public class SmsUtils {

    private static final String ENCRYPT_PASSWORD = "telranashkelon"; //
    private static final String USER_NAME = "toMove";
    private static final String DELIVERY_DELAY_INMINUTES = "0";
    private static final String EXPIRATION_DELAY_INMINUTES = "120";

    // SenderName only one word;
    private static boolean sendSMS(String Subscribers, String Message, String SenderName) throws Exception {
        String requestURL = "http://simplesms.co.il/WebService/sendsmsws.asmx/SendSms";
        StringBuilder data = new StringBuilder();
        data.append("Subscribers=" + URLEncoder.encode(Subscribers, "UTF-8"));
        data.append("&SenderName=" + URLEncoder.encode(SenderName, "UTF-8"));
        data.append("&Message=" + URLEncoder.encode(Message, "WINDOWS-1255"));
        data.append("&DeliveryDelayInMinutes=" + URLEncoder.encode(DELIVERY_DELAY_INMINUTES, "UTF-8"));
        data.append("&ExpirationDelayInMinutes=" + URLEncoder.encode(EXPIRATION_DELAY_INMINUTES, "UTF-8"));
        data.append("&UserName=" + URLEncoder.encode(USER_NAME, "UTF-8"));
        data.append("&EncryptPassword=" + URLEncoder.encode(ENCRYPT_PASSWORD, "UTF-8"));
        // prepare HTTP connection
        URL urladdress = new URL(requestURL);
        // Open Connection
        HttpURLConnection connection = (HttpURLConnection) urladdress.openConnection();
        // Specify POST method
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        // send data
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());

        out.writeBytes(data.toString());
        out.flush();
        out.close();

        // read response
        int responce = connection.getResponseCode();
        System.out.println(responce);
        return responce == 200;

    }

   

}
