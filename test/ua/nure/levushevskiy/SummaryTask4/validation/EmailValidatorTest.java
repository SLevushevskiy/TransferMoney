package ua.nure.levushevskiy.SummaryTask4.validation;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EmailValidatorTest {

    private Map<String, String> errorContainer;

    private static final String VALID_EMAIL = "test@ukr.net";

    private static final String INVALID_EMAIL = "Test@ukr";

    private static final String EMPTY_STRING = "";

    /**
     * Error message about invalid email length.
     */
    private static final String ERROR_EMPTY_EMAIL = "Email length equals 0!";

    /**
     * Error message about invalid email.
     */
    private static final String ERROR_INVALID_EMAIL = "Invalid email!";

    @Before
    public void init() {
        errorContainer = new HashMap<>();
    }

    @Test
    public void validateCorrectEmailTest() {
        errorContainer = EmailValidator.validateEmail(errorContainer, VALID_EMAIL);
        assertEquals(0, errorContainer.size());
    }

    @Test
    public void validateIncorrectEmailTest() {
        errorContainer = EmailValidator.validateEmail(errorContainer, INVALID_EMAIL);
        assertTrue(errorContainer.containsValue(ERROR_INVALID_EMAIL));
        assertEquals(1, errorContainer.size());
    }

    @Test
    public void validateEmptyEmailTest() {
        errorContainer = EmailValidator.validateEmail(errorContainer, EMPTY_STRING);
        assertTrue(errorContainer.containsValue(ERROR_EMPTY_EMAIL));
        assertEquals(1, errorContainer.size());
    }

}