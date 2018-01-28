package ua.nure.levushevskiy.SummaryTask4.validation;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DenominationValidatorTest {

    private Map<String, String> errorContainer;

    private static final String VALID_NAME_EN = "Sergey";

    private static final String VALID_NAME_RU = "Сергей";

    private static final String INVALID_NAME = "сергей";

    private static final String EMPTY_NAME = "";

    /**
     * Error message about invalid length.
     */
    private static final String ERROR_INVALID_LENGTH = "Invalid length!";

    /**
     * Error message about invalid denomination.
     */
    private static final String ERROR_INVALID_DENOMINATION = "This should start with a capital letter and not contain extraneous characters!";

    @Before
    public void init() {
        errorContainer = new HashMap<>();
    }

    @Test
    public void validateCorrectNameEnTest() {
        errorContainer = DenominationValidator.validateDenomination(errorContainer, "Test",VALID_NAME_EN);
        assertEquals(0, errorContainer.size());
    }

    @Test
    public void validateCorrectNameRuTest() {
        errorContainer = DenominationValidator.validateDenomination(errorContainer, "Test",VALID_NAME_RU);
        assertEquals(0, errorContainer.size());
    }

    @Test
    public void validateIncorrectNameTest() {
        errorContainer = DenominationValidator.validateDenomination(errorContainer,"Test", INVALID_NAME);
        assertTrue(errorContainer.containsValue(ERROR_INVALID_DENOMINATION));
    }

    @Test
    public void validateEmptyNameTest() {
        errorContainer = DenominationValidator.validateDenomination(errorContainer,"Test", EMPTY_NAME);
        assertTrue(errorContainer.containsValue(ERROR_INVALID_LENGTH));
    }
}