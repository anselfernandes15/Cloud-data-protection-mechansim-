package com.consumer;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static ArrayList<String> eid = new ArrayList<String>();

    public static void sendMail(String m_from, ArrayList<String> eid, String m_subject, String m_body) {
        try {
            System.out.println(" hello :");
            String address = "";

        for (int i = 0; i < eid.size(); i++) {
            address = "," + eid.get(i);
            System.out.println("addr" + address);
        }

        System.out.println("" + address.substring(1, address.length()));
        System.out.println("------------------");
//            String recp = "ashwini.bedre@eiosys.com,vijay.gharal@eiosys.com,avinash.ghogade@eiosys.com,dipesh.sharma@eiosys.com";
            InternetAddress[] recipientAddress = InternetAddress.parse(address);
            Session m_Session;
            Message m_simpleMessage;
            InternetAddress m_fromAddress;
            InternetAddress m_toAddress;
            Properties m_properties;
            InternetAddress[] myList = new InternetAddress[2];

            m_properties = new Properties();
            m_properties.put("mail.smtp.host","smtp.gmail.com");
            m_properties.put("mail.smtp.socketFactory.port","465");
            m_properties.put("mail.smtp.socketFactory.class",
                   "javax.net.ssl.SSLSocketFactory");
            m_properties.put("mail.smtp.auth","true");
             m_properties.put("mail.transport.protocol","smtp");
            m_properties.put("mail.transport.protocol","true");
            m_properties.put("mail.smtp.port","465");

            m_Session = Session.getDefaultInstance(m_properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("cloudprotection8@gmail.com","password8080"); // username and the password
                }
            });

            m_simpleMessage = new MimeMessage(m_Session);
            m_fromAddress = new InternetAddress(m_from);
//            m_toAddress = new InternetAddress(m_to);
            m_simpleMessage.setFrom(m_fromAddress);
//            m_simpleMessage.setRecipient(RecipientType.TO, m_toAddress);
            m_simpleMessage.setRecipients(RecipientType.TO, recipientAddress);
            m_simpleMessage.setSubject(m_subject);
            m_simpleMessage.setContent(m_body, "text/html");
            //m_simpleMessage.setContent(m_body,"text/plain");

            Transport.send(m_simpleMessage);
            System.out.println("mail has been send ...");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendMailOther(String m_from, ArrayList<String> eid, String m_subject, String m_body) {
        try {
            System.out.println(" hello :");
            String address = "";

        for (int i = 0; i < eid.size(); i++) {
            address += "," + eid.get(i);
            System.out.println("addr" + address);
        }

        System.out.println("" + address.substring(1, address.length()));
        System.out.println("------------------");
//            String recp = "ashwini.bedre@eiosys.com,vijay.gharal@eiosys.com,avinash.ghogade@eiosys.com,dipesh.sharma@eiosys.com";
            InternetAddress[] recipientAddress = InternetAddress.parse(address);
            Session m_Session;
            Message m_simpleMessage;
            InternetAddress m_fromAddress;
            InternetAddress m_toAddress;
            Properties m_properties;
            InternetAddress[] myList = new InternetAddress[2];

            m_properties = new Properties();
            m_properties.put("mail.smtp.host","smtp.gmail.com");
            m_properties.put("mail.smtp.socketFactory.port","465");
            m_properties.put("mail.smtp.socketFactory.class",
                   "javax.net.ssl.SSLSocketFactory");
            m_properties.put("mail.smtp.auth","true");
             m_properties.put("mail.transport.protocol","smtp");
            m_properties.put("mail.transport.protocol","true");
            m_properties.put("mail.smtp.port","465");

            m_Session = Session.getDefaultInstance(m_properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("cloudprotection8@gmail.com","password8080");  // username and the password
                }
            });
            System.out.println(m_Session);
            m_simpleMessage = new MimeMessage(m_Session);
            m_fromAddress = new InternetAddress(m_from);
//            m_toAddress = new InternetAddress(m_to);
            m_simpleMessage.setFrom(m_fromAddress);
//            m_simpleMessage.setRecipient(RecipientType.TO, m_toAddress);
            m_simpleMessage.setRecipients(RecipientType.TO, recipientAddress);
            m_simpleMessage.setSubject(m_subject);
            m_simpleMessage.setContent(m_body, "text/html");
            //m_simpleMessage.setContent(m_body,"text/plain");

            Transport.send(m_simpleMessage);
            
            System.out.println("mail has been sent ...");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException, Exception {

        SendMail send_mail = new SendMail();
        String empName = "Antony Raj S";
         ArrayList<String> eid=new ArrayList<String>();
      
        eid.add("pranu7496@gmail.com");
        String title = "<b>Hi !" + empName + " Welcome to DeDuplication</b>";
//      send_mail.sendMail("testproject201@gmail.com", "pranaya.jadhav@eiosys.com", "Please apply for leave for the following dates", title+"<br>by<br><b>HR<b>");
        send_mail.sendMailOther("cloudprotection8@gmail.com", eid, "CLOUD DATA PROTECTION", "filename:abc" + "key:daretodream");
        System.out.println("mail has been send ...");
    }
}
