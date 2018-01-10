package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.AccountStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountStatusDAOImpl implements AccountStatusDAO {
    /**
     * Request to retrieve all role objects by ID.
     */
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.account_status WHERE idAccount_status = ?";

    /**
     * Object of connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(AccountStatusDAOImpl.class);

    /**
     * A constructor that takes a pool of connections.
     *
     * @param dataSource - connection pool.
     */
    public AccountStatusDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets the status object by account ID.
     *
     * @param accountId - account ID.
     * @return - accountStatus object
     */
    @Override
    public AccountStatus getAccountStatus(int accountId) {
        AccountStatus accountStatus = null;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, accountId);
            rs = preparedStatement.executeQuery();
            List<AccountStatus> statusList = extractResultSet(rs);

            if (statusList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            accountStatus = statusList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return accountStatus;
    }

    /**
     * Sets the status object by account ID.
     *
     * @param accountId - user ID.
     * @param status    - status.
     * @return - accountStatus object
     */
    @Override
    public AccountStatus setAccountStatus(int accountId, String status) {
        return null;
    }

    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<AccountStatus> extractResultSet(final ResultSet rs) throws SQLException {
        List<AccountStatus> statusList = new ArrayList<>();
        AccountStatus accountStatus;
        while (rs.next()) {
            accountStatus = new AccountStatus();
            accountStatus.setIdAccountStatus(rs.getInt("idAccount_status"));
            accountStatus.setStatus(rs.getString("status"));
            statusList.add(accountStatus);
        }
        return statusList;
    }

    /**
     * Method for closing the Result Set.
     *
     * @param rs - Result Set object.
     */
    private void closeResultSet(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(ex);
                throw new DAOException("Cannot close result set", ex);
            }
        }
    }

    /**
     * Method for closing the connection to the database.
     *
     * @param connection - connection object.
     */
    private void closeConnection(final Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e);
                throw new DAOException("Cannot close connection", e);
            }
        }
    }

}
