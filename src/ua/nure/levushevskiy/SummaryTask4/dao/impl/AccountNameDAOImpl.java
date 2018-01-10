package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountNameDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.AccountName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountNameDAOImpl implements AccountNameDAO {

    /**
     * Request to retrieve all role objects by ID.
     */
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.account_name WHERE idAccount_name = ?";

    /**
     * Object of connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(AccountNameDAOImpl.class);

    /**
     * A constructor that takes a pool of connections.
     *
     * @param dataSource - connection pool.
     */
    public AccountNameDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets the name object by account ID.
     *
     * @param accountId - account ID.
     * @return - accountName object
     */
    @Override
    public AccountName getAccountName(int accountId) {
        AccountName accountName = null;

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
            List<AccountName> statusList = extractResultSet(rs);

            if (statusList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            accountName = statusList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return accountName;
    }

    /**
     * Sets the name object by account ID.
     *
     * @param accountId - user ID.
     * @param name      - name.
     * @return - accountName object
     */
    @Override
    public AccountName setAccountName(int accountId, String name) {
        return null;
    }

    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<AccountName> extractResultSet(final ResultSet rs) throws SQLException {
        List<AccountName> nameList = new ArrayList<>();
        AccountName accountName;
        while (rs.next()) {
            accountName = new AccountName();
            accountName.setIdAccountName(rs.getInt("idAccount_name"));
            accountName.setName(rs.getString("name"));
            nameList.add(accountName);
        }
        return nameList;
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
