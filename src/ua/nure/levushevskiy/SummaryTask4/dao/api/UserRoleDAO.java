package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.UserRole;

public interface UserRoleDAO {

    /**
     *
     * Gets the role object by user ID.
     *
     * @param userId     - user ID.
     * @return - userRole object
     */
    UserRole getUserRole(int userId);
}
