package ua.nure.levushevskiy.SummaryTask4.dao.factory;

import ua.nure.levushevskiy.SummaryTask4.dao.api.UserDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserRoleDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.factory.api.DAOFactory;
import ua.nure.levushevskiy.SummaryTask4.dao.impl.UserDAOImpl;
import ua.nure.levushevskiy.SummaryTask4.dao.impl.UserRoleDAOImpl;
import ua.nure.levushevskiy.SummaryTask4.dao.impl.UserStatusDAOImpl;
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

}
