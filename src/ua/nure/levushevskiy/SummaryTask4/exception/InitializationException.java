package ua.nure.levushevskiy.SummaryTask4.exception;

/**
 * A custom unchecked exception that may occur during execution of service methods.
 */
public class InitializationException extends RuntimeException {

    /**
     * Default constructor.
     */
    public InitializationException() {
    }

    /**
     * The constructor that receives the error message.
     *
     * @param message - error message.
     */
    public InitializationException(final String message) {
        super(message);
    }
}
