package br.ufc.pet.util;

/*
 * @author Gleyson
 */
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SendMail {

    public static void sendMail(String mailServer, String from, String to, String subject, String Mensagem)
            throws AddressException, MessagingException {

        Properties mailProps = new Properties();
        //definição do mailserver

        mailProps.put("mail.smtp.host", mailServer);
        mailProps.put("mail.smtp.auth", "true");
        
        Session mailSession = Session.getInstance(mailProps, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("petufc.quixada@gmail.com", "senhorjesus2017");
            }
        });

        
        String texto = Mensagem;
        texto = texto.replaceAll("\n", "\r\n");

        mailSession.setDebug(true);
        mailProps.put("mail.debug", "true");
        mailProps.put("mail.smtp.debug", "true");
        mailProps.put("mail.mime.charset", "ISO-8859-1");
        mailProps.put("mail.smtp.port", "465");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.socketFactory.port", "465");
        mailProps.put("mail.smtp.socketFactory.fallback", "false");
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
            throws AddressException, MessagingException {
        SendMail.sendMail("smtp.gmail.com", "petufc.quixada@gmail.com", to, subject, Mensagem);
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
