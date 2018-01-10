package ua.nure.levushevskiy.SummaryTask4.dao.factory;

import ua.nure.levushevskiy.SummaryTask4.dao.api.*;
import ua.nure.levushevskiy.SummaryTask4.dao.factory.api.DAOFactory;
import ua.nure.levushevskiy.SummaryTask4.dao.impl.*;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;

public class MySQLDAOFactory implements DAOFactory {


    /**
     * Connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * Constructor that gets connection pool object.
     *
     * @param ds - connection pool.
     */
    public MySQLDAOFactory(final ConnectionPool ds) {
        dataSource = ds;
    }


    @Override
    public final UserDAO getUserDAO() {
        return new UserDAOImpl(dataSource);
    }

    @Override
    public final UserStatusDAO getUserStatusDAO() { return new UserStatusDAOImpl(dataSource);
    }

    /**
     * Returns UserRoleDAO object.
     *
     * @return - object.
     */
    @Override
    public UserRoleDAO getUserRoleDAO() {  return new UserRoleDAOImpl(dataSource); }

    /**
     * Returns AccountDAO object.
     *
     * @return - object.
     */
    @Override
    public AccountDAO getAccountDAO() {
        return new AccountDAOImpl(dataSource);
    }

    /**
     * Returns AccountNameDAO object.
     *
     * @return - object.
     */
    @Override
    public AccountNameDAO getAccountNameDAO() {
        return new AccountNameDAOImpl(dataSource);
    }

    /**
     * Returns AccountStatusDAO object.
     *
     * @return - object.
     */
    @Override
    public AccountStatusDAO getAccountStatusDAO() {
        return new AccountStatusDAOImpl(dataSource);
    }

}
