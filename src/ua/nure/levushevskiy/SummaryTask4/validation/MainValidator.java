package ua.nure.levushevskiy.SummaryTask4.validation;


import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;

import java.util.Map;

/**
 * The validator that manages all validators..
 */
public class MainValidator {

    /**
     * Name target.
     */
    private static final String NAME = "NAME";

    /**
     * Surname target.
     */
    private static final String SURNAME = "SURNAME";

    /**
     * Country target.
     */
    private static final String COUNTRY = "COUNTRY";

    /**
     * City target.
     */
    private static final String CITY = "CITY";

    /**
     * The main method that controls all other validators.
     *
     * @param userDTO        - user object (data).
     * @param errorContainer - empty error container.
     * @return - fill container, if there was invalid data.
     */
    public static Map<String, String> validate(final UserDTO userDTO, Map<String, String> errorContainer) {
        errorContainer = DenominationValidator.validateDenomination(errorContainer, NAME, userDTO.getName());
        errorContainer = DenominationValidator.validateDenomination(errorContainer, SURNAME, userDTO.getSurname());
        errorContainer = EmailValidator.validateEmail(errorContainer, userDTO.getEmail());

        return errorContainer;
    }
}
