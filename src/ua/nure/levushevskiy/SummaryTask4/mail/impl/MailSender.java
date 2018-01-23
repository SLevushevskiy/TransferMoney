package ua.nure.levushevskiy.SummaryTask4.mail.impl;

import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.mail.api.ConfirmationMailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Implementing the ConfirmationMailSender interface.
 */
public class MailSender implements ConfirmationMailSender {

    /**
     * Mail of the sender.
     */
    private final String username;

    /**
     * Password from mail.
     */
    private final String password;

    /**
     * Constructor initializing sender data.
     *
     * @param bundle - object with properties.
     */
    public MailSender(final ResourceBundle bundle) {
        username = bundle.getString("mail.username");
        password = bundle.getString("mail.password");
    }

    @Override
    public final void sendMail(final UserDTO userDTO, final String subject, final String message) throws MessagingException {
        Message msg = new MimeMessage(getSession());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDTO.getEmail()));
        msg.setSubject(subject);
        msg.setText(message);
        Transport.send(msg);
    }

    /**
     * The method that receives the session object.
     *
     * @return - session.
     */
    private Session getSession() {
        return Session.getDefaultInstance(getProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    /**
     * The method that initializes properties.
     *
     * @return - properties.
     */
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }
}
