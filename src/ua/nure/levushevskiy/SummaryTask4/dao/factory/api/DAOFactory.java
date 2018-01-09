package ua.nure.levushevskiy.SummaryTask4.dao.factory.api;

import ua.nure.levushevskiy.SummaryTask4.dao.api.UserDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserRoleDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserStatusDAO;

public interface DAOFactory {
    /**
     * Returns UserDAO object.
     *
     * @return - object.
     */
    UserDAO getUserDAO();

    /**
     * Returns UserStatusDAO object.
     *
     * @return - object.
     */
    UserStatusDAO getUserStatusDAO();

    /**
     * Returns UserRoleDAO object.
     *
     * @return - object.
     */
    UserRoleDAO getUserRoleDAO();
}
