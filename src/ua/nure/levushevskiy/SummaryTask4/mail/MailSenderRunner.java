package ua.nure.levushevskiy.SummaryTask4.mail;

import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.mail.api.ConfirmationMailSender;

import javax.mail.MessagingException;

/**
 * Thread, to send a message as demon.
 */
public class MailSenderRunner extends Thread {
    /**
     * Object to send a message.
     */
    private final ConfirmationMailSender confirmationMailSender;

    /**
     * Registered user.
     */
    private final UserDTO userDTO;

    /**
     * Number of characters in the confirmation key.
     */
    private static final int NUM_OF_LETTERS = 5;

    /**
     * A constructor that accepts an object to send a message and a registered user.
     *
     * @param confirmationMailSender - an object to send a message.
     * @param userDTO                - registered user.
     */
    public MailSenderRunner(final ConfirmationMailSender confirmationMailSender, final UserDTO userDTO) {
        this.confirmationMailSender = confirmationMailSender;
        this.userDTO = userDTO;
    }

    @Override
    public final void run() {
        try {
            confirmationMailSender.sendMail(userDTO, "Confirmation of registration", "Hello, " + userDTO.getName()
                    + userDTO.getSurname() + ". To confirm the registration, you should enter the key below in the special field on the site. \nYour key: "
                    + userDTO.getPassword().substring(0, NUM_OF_LETTERS));
        } catch (MessagingException e) {
            throw new IllegalStateException("An error occurred while sending a letter with the key to confirm the registration."
                    + " Please check the correctness of the e-mail address you entered.");
        }
    }
}
