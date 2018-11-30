package br.ufc.pet.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/*
 * @author Gleyson
 */
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SendMail {

    public static void sendMail(String mailServer, String from, String to, String subject, String Mensagem)
            throws AddressException, MessagingException, FileNotFoundException, IOException {

        Properties mailProps = new Properties();
        
        //Pegando os valores do properties
        //Properties propeties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();           
        InputStream arq = loader.getResourceAsStream("mail.properties");
        
        mailProps.load(arq); 
        
        String usermail = mailProps.getProperty("mailuser");
        String mailpassword = mailProps.getProperty("mailpassword");	
                
        //definição do mailserver
        mailProps.put("mail.smtp.host", mailServer);
        mailProps.put("mail.smtp.auth", "true");
        
        Session mailSession = Session.getInstance(mailProps, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usermail,mailpassword );
            }
        });
       
        String texto = Mensagem;
        texto = texto.replaceAll("\n", "\r\n");

        mailSession.setDebug(false);
        mailProps.put("mail.debug", "false");
        mailProps.put("mail.smtp.debug", "false");
        mailProps.put("mail.mime.charset", "ISO-8859-1");
        mailProps.put("mail.smtp.port", "465");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.socketFactory.port", "465");
        mailProps.put("mail.smtp.socketFactory.fallback", "true");
        mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Message message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress(from));

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSentDate(new Date());

        message.setSubject(subject);

        message.setText(Mensagem);

        Transport.send(message);
    }

    public static void sendMail(String to, String subject, String Mensagem)
            throws AddressException, MessagingException, FileNotFoundException, IOException {
    	
    	//Pegando o email inserido no properties 
    	Properties propeties = new Properties();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();           
    	InputStream arq = loader.getResourceAsStream("mail.properties");
    	
    	propeties.load(arq);
                
        String usermail = propeties.getProperty("mailuser");
         
        SendMail.sendMail("smtp.gmail.com", usermail, to, subject, Mensagem);
    }


    public static void sendEmailSeven(String messageBody, String from, String to, String subject){

        try{
        Context initCtx = new InitialContext();

        Session s = (javax.mail.Session)initCtx.lookup("java:comp/env/"+"mail/Session");


        MimeMessage message = new MimeMessage( s);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(MimeUtility.encodeText(subject), "UTF-8");
        String messageBodyContent = "<html><body>";
        messageBodyContent+="<html><body> " + messageBody + "</body></html>";

        message.setContent(messageBodyContent, "text/html; charset=\"UTF-8\"");
       //Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = s.getTransport("smtp"); //define smtp para transporte
            Properties props = s.getProperties();
            String host =props.getProperty("mail.smtp.host");
            int port = Integer.parseInt(props.getProperty("mail.smtp.port"));
            String user = props.getProperty("mail.smtp.user");
            String password = s.getProperties().getProperty("mail.smtp.password");

            tr.connect(host, port, user, password);
            
            message.saveChanges(); // don't forget this
            //envio da mensagem
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }catch(Exception ex){
            System.out.println(ex.toString());

        }
    }


}
