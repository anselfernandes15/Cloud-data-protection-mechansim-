/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consumer;

/**
 *
 * @author SONY
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL; 

/**
 *
 * @author MKS
 */
public class SMSAPIJAVA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            URL url = new URL("https://smsapi.engineeringtgr.com/send/?Mobile=8097108354&Password=password8080&Message=test&To=9167127981&Key=cloudJWO4LgKAdYzyqU69Q3CH0R8");
            URLConnection urlcon = url.openConnection();
            InputStream stream = urlcon.getInputStream();
            int i;
            String response="";
            while ((i = stream.read()) != -1) {
                response+=(char)i;
            }
            if(response.contains("success")){
                System.out.println("Successfully send SMS");
                //your code when message send success
            }else{
                System.out.println(response);
                //your code when message not send
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
