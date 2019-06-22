/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.email;

import ao.co.a2x.model.ConfiguracaoApp;
import ao.co.a2x.model.Email;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author a2x
 */
public class EnvioDeEmail {

    public String sendMail(Email email, ConfiguracaoApp config) throws AddressException, MessagingException {

        System.setProperty("java.net.preferIPv4Stack", config.getPreferIPv4Stack());
        Properties props = System.getProperties();
        // -- Attaching to default Session, or we could start a new one --
        props.put("mail.transport.protocol", config.getProtocol());
        props.put("mail.smtp.starttls.enable", config.getStarttls());
        props.put("mail.smtp.host", config.getHost());
        props.put("mail.smtp.auth", config.getAuth());
        props.put("mail.smtp.port", config.getPort());
        Authenticator auth = new SMTPAuthenticator(config.getEmailDoSistema(),config.getSenhaDoEmail());
        Session session = Session.getInstance(props, auth);

        // -- Create a new message --
        Message message = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        message.setFrom(new InternetAddress(email.getFrom()));

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));
        if (email.getBcc() != null && !email.getBcc().isEmpty()) {
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(email.getBcc(), false));
        }
        message.setSubject(email.getSubject());

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        //messageBodyPart.setText(text);
        if (email.getContentType() != null) {
            messageBodyPart.setContent(email.getText(), email.getContentType());
        } else {
            messageBodyPart.setText(email.getText());
        }

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        if (email.getFiles() != null && email.getFiles().size() > 0) {
            for (String filePath : email.getFiles()) {
                if (filePath != null && !filePath.isEmpty()) {
                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(filePath);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(obterNomeDoFicheiro(filePath));
                    multipart.addBodyPart(messageBodyPart);
                }
            }
        }

        // Send the complete message parts
        message.setContent(multipart);

        // -- Send the message --
        Transport.send(message);
        //response="Criou sessão";

        return "OK";

    }

    private String obterNomeDoFicheiro(String file) {
        return file.substring(file.lastIndexOf(File.separator), file.length());
    }

    // Also include an inner class that is used for authentication purposes
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        private final String username;
        private final String password;

        public SMTPAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}
