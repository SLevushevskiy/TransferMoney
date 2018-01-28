package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.User;

/**
 * An interface that contains methods for interacting with the database.
 * Namely with the table User.
 */
public interface UserDAO extends DAO<User, Integer> {
    /**
     * Checks for the presence of an object at address.
     *
     * @param email - email address.
     * @return - true (if object was removed).
     */
    boolean hasUserWithEmail(String email);

    /**
     * Gets the object by email address and password.
     *
     * @param email    - email address.
     * @param password - user password.
     * @return - user object.
     */
    User getByEmailAndPassword(String email, String password);

    /**
     * Updates the user status.
     *
     * @param userId     - user ID.
     * @param userStatus - status.
     * @return - true (if status was updated).
     */
    boolean updateUserStatus(int userId, String userStatus);

}
