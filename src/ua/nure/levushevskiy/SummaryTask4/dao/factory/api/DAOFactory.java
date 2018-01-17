package ua.nure.levushevskiy.SummaryTask4.dao.factory.api;

import ua.nure.levushevskiy.SummaryTask4.dao.api.*;
import ua.nure.levushevskiy.SummaryTask4.dao.impl.PaymentNameDAOImpl;

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

    /**
     * Returns AccountDAO object.
     *
     * @return - object.
     */
    AccountDAO getAccountDAO();

    /**
     * Returns AccountNameDAO object.
     *
     * @return - object.
     */
    AccountNameDAO getAccountNameDAO();

    /**
     * Returns AccountStatusDAO object.
     *
     * @return - object.
     */
    AccountStatusDAO getAccountStatusDAO();

    /**
     * Returns PaymentDAO object.
     *
     * @return - object.
     */
    PaymentDAO getPaymentDAO();

    /**
     * Returns PaymentStatusDAO object.
     *
     * @return - object.
     */
    PaymentStatusDAO getPaymentStatusDAO();

    /**
     * Returns PaymentTypeDAO object.
     *
     * @return - object.
     */
    PaymentTypeDAO getPaymentTypeDAO();

    /**
     * Returns PaymentNameDAO object.
     *
     * @return - object.
     */
    PaymentNameDAO getPaymentNameDAO();
}
