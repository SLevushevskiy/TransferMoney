package ua.nure.levushevskiy.SummaryTask4.dao.exeption;

/**
 * A custom unchecked exception that may occur during execution of DAO methods.
 */
public class DAOException extends RuntimeException {
    /**
     * Default constructor.
     */
    public DAOException() {
    }

    /**
     * The constructor that receives the error message.
     *
     * @param message - error message.
     */
    public DAOException(final String message) {
        super(message);
    }

    /**
     * The constructor that receives the error message and cause-exception.
     *
     * @param message - error message.
     * @param cause   - cause-exception.
     */
    public DAOException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * The constructor that receives the cause-exception.
     *
     * @param cause - cause-exception.
     */
    public DAOException(final Throwable cause) {
        super(cause);
    }
}
