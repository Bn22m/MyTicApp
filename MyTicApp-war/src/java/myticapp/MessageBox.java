/*
 * MyTicApp
 */
package myticapp;

/**
 *
 * @author Brian
 * This class is used for mail messages.
 */

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;


public class MessageBox
{
    public static final Logger LOGGS = Logger.getLogger("myticapp");

    public static void SendMessage(String mes,String eml) 
    {
        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!
        String to = eml;
        String from = eml;
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!
        String host = "smtp.yourisp.invalid";//

        // Create properties, get Session
        Properties props = new Properties();

        // If using static Transport.send(),
        // need to specify which host to send it to
        props.put("mail.smtp.host", host);
        // To see what is going on behind the scene
        props.put("mail.debug", "true");
        Session session = Session.getInstance(props);

        try 
        {
            FileHandler fh = new FileHandler("freshLog.txt");
            System.out.println(to);
            System.out.println(mes);
            LOGGS.addHandler (fh);
            LOGGS.setLevel ( Level.ALL );
            LOGGS.log (Level.INFO, "SendMail@message() to: {0}", to);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("MyTicApp...Message..");
            msg.setSentDate(new Date());

            // Set message content
            msg.setText(mes);

            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException me) 
        {
            // Prints all nested (chained) exceptions as well
            System.err.print(me.getMessage());
            LOGGS.log(Level.SEVERE, "mEx: {0}", me.getMessage());
        } 
        catch (IOException | SecurityException ex) 
        {
            LOGGS.log(Level.SEVERE, "ExLog2: {0}", ex.getMessage());
        }
    } 
    
}//
