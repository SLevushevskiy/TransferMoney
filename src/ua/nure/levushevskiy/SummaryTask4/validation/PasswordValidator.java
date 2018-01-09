package ua.nure.levushevskiy.SummaryTask4.validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator checks password.
 */
public class PasswordValidator {

    /**
     * Regular expression for password validation.
     */
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{6,15}$";

    /**
     * Error message about invalid password.
     */
    private static final String ERROR_INVALID_PASSWORD = "The password contains invalid characters OR length!";

    /**
     * Error message about password mismatch and confirmation.
     */
    private static final String ERROR_CONFIRM_NOT_EQUAL = "Password and confirm don't equal!";

    /**
     * Name of the item being checked.
     */
    private static final String VALIDATOR_TARGET = "PASSWORD";

    /**
     * Method that validates password and confirm of password.
     *
     * @param errorContainer - container with errors.
     * @param password       - input password..
     * @param confirm        - confirm of password.
     * @return - error container.
     */
    public static Map<String, String> validatePasswordAndConfirm(final Map<String, String> errorContainer, final String password, final String confirm) {

        if (!password.equals(confirm)) {
            errorContainer.put(VALIDATOR_TARGET, ERROR_CONFIRM_NOT_EQUAL);
        } else {
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                errorContainer.put(VALIDATOR_TARGET, ERROR_INVALID_PASSWORD);
            }
        }

        return errorContainer;
    }
}
