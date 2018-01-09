package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.UserStatus;

public interface UserStatusDAO {

    /**
     *
     * Gets the status object by user ID.
     *
     * @param userId     - user ID.
     * @return - userStatus object
     */
    UserStatus getUserStatus(int userId);

    /**
     *
     * Sets the status object by user ID.
     *
     * @param userId     - user ID.
     * @param status     - status.
     * @return - userStatus object
     */
    UserStatus setUserStatus(int userId, String status);
}
