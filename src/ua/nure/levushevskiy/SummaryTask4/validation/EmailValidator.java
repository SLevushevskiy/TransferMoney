package ua.nure.levushevskiy.SummaryTask4.validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator checks email.
 */
public class EmailValidator {

    /**
     * Regular expression for address validation.
     */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Error message about invalid email length.
     */
    private static final String ERROR_EMPTY_EMAIL = "Email length equals 0!";

    /**
     * Error message about invalid email.
     */
    private static final String ERROR_INVALID_EMAIL = "Invalid email!";

    /**
     * Name of the item being checked.
     */
    private static final String VALIDATOR_TARGET = "EMAIL";

    /**
     * Метод, валидирующий email.
     *
     * @param errorContainer - container with errors.
     * @param email          - input data.
     * @return - error container.
     */
    public static Map<String, String> validateEmail(final Map<String, String> errorContainer, final String email) {

        if (email.length() == 0) {
            errorContainer.put(VALIDATOR_TARGET, ERROR_EMPTY_EMAIL);
        } else {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                errorContainer.put(VALIDATOR_TARGET, ERROR_INVALID_EMAIL);
            }
        }

        return errorContainer;
    }
}
