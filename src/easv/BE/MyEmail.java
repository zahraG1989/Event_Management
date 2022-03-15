package easv.BE;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MyEmail {

    public static void main(String[] args) throws MessagingException, IOException {
        System.out.println("preapring ");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth" , "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host" , "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        // properties.put("mail.transport.protocl" , "smtp");
        String acountname = "@gmail.com";
        String password = "" ;
        String recipeant = "@gmail.com";
                Session session =  Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(acountname,password);
            }
        });
        Message message = preparemessage(session,acountname , recipeant);
        Transport.send(message);
        System.out.println("message sent ");
            /*
        Message message = new MimeMessage(session);
        //  message.setText();
        message.setSubject("Email form event manager ");
        // message.setContent("<h1> email from my cool program to send teckets <h2>" ,"text/html");
        // pass the email of the customer
        Address addressto = new InternetAddress("@yahoo.com");
        message.setRecipient(Message.RecipientType.TO ,addressto );

        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File("/resourse/fil.pdf"));

        MimeBodyPart measagebodypart = new MimeBodyPart();
        measagebodypart.setContent("<h1> email from my cool program to send teckets <h2>" ,"text/html");

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(measagebodypart);
        multipart.addBodyPart(attachment);

        message.setContent(multipart);

        Transport.send(message);
        
             */
    }

    private static Message preparemessage(Session session , String myaccount , String recipeant  ) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myaccount));
            message.setRecipient(Message.RecipientType.TO , new InternetAddress(recipeant));
            message.setSubject("first test");
            message.setText("he is a  champ ");

            return  message ;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null ;
    }


}
