package com.consumer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendSMS {

    public static void sendMSG(String pno, String encode) throws MalformedURLException {
        try {
            System.out.println("pno:"+pno+ "  Encode:"+encode);
   
           URL url = new URL("https://smsapi.engineeringtgr.com/send/?Mobile=8097108354&Password=password8080&Message="+ encode +"&To="+pno+"&key=cloudJWO4LgKAdYzyqU69Q3CH0R8");
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            rd.close();
            conn.disconnect();
            System.out.println("Finish");
        } catch (IOException ex) {
            Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
