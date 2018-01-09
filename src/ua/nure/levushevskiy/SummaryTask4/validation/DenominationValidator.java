package ua.nure.levushevskiy.SummaryTask4.validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A validator that checks a specific set of elements.
 */
public class DenominationValidator {

    /**
     * Error message about invalid length.
     */
    private static final String ERROR_INVALID_LENGTH = "Invalid length!";

    /**
     * Error message about invalid denomination.
     */
    private static final String ERROR_INVALID_DENOMINATION = "This should start with a capital letter and not contain extraneous characters!";

    /**
     * Denomination regex.
     */
    private static final String DENOMINATION_PATTERN = "^[А-ЯЁA-Z]{1}[а-яёa-z]+$";

    /**
     * Метод, валидирующий denominations.
     *
     * @param errorContainer - container with errors.
     * @param target         - name of the item being checked.
     * @param denomination   - checked item.
     * @return - error container.
     */
    public static Map<String, String> validateDenomination(final Map<String, String> errorContainer,
                                                           final String target, final String denomination) {

        if (denomination.length() < 2) {
            errorContainer.put(target, ERROR_INVALID_LENGTH);
        } else {
            Pattern pattern = Pattern.compile(DENOMINATION_PATTERN);
            Matcher matcher = pattern.matcher(denomination);
            if (!matcher.matches()) {
                errorContainer.put(target, ERROR_INVALID_DENOMINATION);
            }
        }

        return errorContainer;
    }
}
