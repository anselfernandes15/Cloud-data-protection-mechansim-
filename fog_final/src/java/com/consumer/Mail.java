package com.consumer;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Mail {

    public static String mail_username = "cloudprotection8@gmail.com";
    public static String mail_pwd = "password8080";

    public static void main(String args[]) {
//        String to = "avinashg.eiosys@gmail.com";
//        String[] cc = {};
//        String[] bcc = {};
//        String subject = "sending shared key", body = "a";

        //This is for google
//        Mail.sendMail(Mail.mail_username, Mail.mail_pwd, "smtp.gmail.com", "465", "true", "true", true,
//                "javax.net.ssl.SSLSocketFactory", "false", to,subject,body);
    }

    public synchronized static boolean sendMail(String userName, String passWord, String host, String port, String starttls,
            String auth, boolean debug, String socketFactoryClass, String fallback, String to,String subject, String text) {
        System.out.println("in sendMail");
        Properties props = new Properties();
        //Properties props=System.getProperties();
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.host", host);
        if (!"".equals(port)) {
            props.put("mail.smtp.port", port);
        }
        if (!"".equals(starttls)) {
            props.put("mail.smtp.starttls.enable", starttls);
        }
        props.put("mail.smtp.auth", auth);
        if (debug) {
            props.put("mail.smtp.debug", "true");
        } else {
            props.put("mail.smtp.debug", "false");
        }
        if (!"".equals(port)) {
            props.put("mail.smtp.socketFactory.port", port);
        }
        if (!"".equals(socketFactoryClass)) {
            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        }
        if (!"".equals(fallback)) {
            props.put("mail.smtp.socketFactory.fallback", fallback);
        }
        try {
            System.out.println("in catch()");
            Session session = Session.getDefaultInstance(props, null);
            System.out.println("session=" + session);
            session.setDebug(debug);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(text);
            msg.setSubject(subject);
//            msg.setFrom(new InternetAddress("p_sambasivarao@sutyam.com"));
//            for (int i = 0; i < to.length; i++) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to.trim()));
//            }
//            for (int i = 0; i < cc.length; i++) {
//                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
//            }
//            for (int i = 0; i < bcc.length; i++) {
//                msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
//            }
            msg.saveChanges();
            Transport transport = session.getTransport("smtp");
            System.out.println("transport=" + transport);
            transport.connect(host, userName, passWord);
            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
            transport.close();

            return true;
        } catch (Exception mex) {
            System.out.println("in catch()");
            mex.printStackTrace();
            return false;
        }
    }

//    static void sendSharedKey(String ShareKey, String docname,String emails) {
//        System.out.println(emails);
////        String[] to = (String[]) emails.toArray();;
//        String to = emails;
////        String[] cc = emails.toArray(new String[emails.size()]);
////        String[] bcc = emails.toArray(new String[emails.size()]);
//
//        String subject = "sending shared key";
//        String body = "sending shared key " + ShareKey + " for " + docname;
//
//        Mail.sendMail(mail_username, mail_pwd, "smtp.gmail.com", "465", "true", "true", true,
//                "javax.net.ssl.SSLSocketFactory", "false", to, subject,
//                body);
//
//    }

    public static void sendUserDetails(String uname, String emails,String key) {
        System.out.println(emails);
//        String[] to = (String[]) emails.toArray();;
        String to = emails.trim();
        System.out.println("to:===="+to);
//        String[] cc = emails.toArray(new String[emails.size()]);
//        String[] bcc = emails.toArray(new String[emails.size()]);

        String subject = "User Credentials";
        String body = "Username: " + uname + " your Key: " + key;

        Mail.sendMail(mail_username, mail_pwd, "smtp.gmail.com", "465", "true", "true", true,
                "javax.net.ssl.SSLSocketFactory", "false", to, subject,
                body);

    }
}