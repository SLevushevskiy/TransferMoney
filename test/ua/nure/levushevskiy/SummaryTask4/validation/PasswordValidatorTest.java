package ua.nure.levushevskiy.SummaryTask4.validation;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PasswordValidatorTest {
    private Map<String, String> errorContainer;

    private static final String VALID_PASSWORD_1 = "password";

    private static final String VALID_PASSWORD_2 = "123456";

    private static final String INVALID_PASSWORD_1 = "12345";

    private static final String INVALID_PASSWORD_2 = "русский1";

    /**
     * Error message about invalid password.
     */
    private static final String ERROR_INVALID_PASSWORD = "The password contains invalid characters OR length!";

    /**
     * Error message about password mismatch and confirmation.
     */
    private static final String ERROR_CONFIRM_NOT_EQUAL = "Password and confirm don't equal!";


    @Before
    public void init() {
        errorContainer = new HashMap<>();
    }

    @Test
    public void validateCorrectPasswordTest() {
        errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, VALID_PASSWORD_1, VALID_PASSWORD_1);
        errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, VALID_PASSWORD_2, VALID_PASSWORD_2);
        assertEquals(0, errorContainer.size());
    }

    @Test
    public void validateIncorrectLengthPasswordTest() {
        errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, INVALID_PASSWORD_1, INVALID_PASSWORD_1);
        assertTrue(errorContainer.containsValue(ERROR_INVALID_PASSWORD));
        assertEquals(1, errorContainer.size());
    }

    @Test
    public void validateIncorrectPasswordTest() {
        errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, INVALID_PASSWORD_2, INVALID_PASSWORD_2);
        assertTrue(errorContainer.containsValue(ERROR_INVALID_PASSWORD));
        assertEquals(1, errorContainer.size());
    }

    @Test
    public void differentPasswordsTest() {
        errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, VALID_PASSWORD_1, VALID_PASSWORD_2);
        assertTrue(errorContainer.containsValue(ERROR_CONFIRM_NOT_EQUAL));
        assertEquals(1, errorContainer.size());
    }
}