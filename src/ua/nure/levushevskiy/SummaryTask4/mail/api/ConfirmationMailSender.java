package ua.nure.levushevskiy.SummaryTask4.mail.api;

import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;

import javax.mail.MessagingException;


/**
 * Interface for sending emails.
 */
public interface ConfirmationMailSender {
    /**
     * The method that generates and sends messages.
     *
     * @param userDTO - registered user.
     * @param subject - subject of letter.
     * @param message - contents of the letter.
     * @throws MessagingException - an exception that can be thrown away when sending a message.
     */
    void sendMail(UserDTO userDTO, String subject, String message) throws MessagingException;
}
