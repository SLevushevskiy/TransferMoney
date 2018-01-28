package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AccountDAO interface.
 */
public class AccountDAOImpl implements AccountDAO {

    /**
     * Request to retrieve all account objects by ID.
     */
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.account WHERE idAccount = ?";
    /**
     * Request to retrieve all account objects by ID.
     */
    public static final String SQL_SELECT_BY_DATE = "SELECT * FROM st4db.account WHERE end_date = ?";
    /**
     * Request to retrieve all account objects.
     */
    public static final String SQL_SELECT_ALL = "SELECT * FROM st4db.account";
    /**
     * Request for insert account.
     */
    public static final String SQL_ACCOUNT_INSERT = "INSERT INTO st4db.account (amound, end_date, user_id, account_status_id, account_name_id) "
            + "VALUES(?, ?, ?, ?, ?)";
    /**
     * Request to retrieve all accounts objects by user id.
     */
    public static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM st4db.account WHERE user_id = ?";
    /**
     * Request to retrieve all accounts objects by user id and account name id.
     */
    public static final String SQL_SELECT_BY_USER_ID_AND_NAME_ID = "SELECT * FROM st4db.account WHERE user_id = ? AND account_name_id = ?";
    /**
     * Request to retrieve all accounts objects by user id and account status id.
     */
    public static final String SQL_SELECT_BY_USER_ID_AND_STATUS_ID = "SELECT * FROM st4db.account WHERE user_id = ? AND account_status_id = ?";
    /**
     * Request to update the account status in the database.
     */
    public static final String SQL_UPDATE_ACCOUNT_STATUS = "UPDATE st4db.account SET account_status_id = "
            + "(SELECT idAccount_status FROM st4db.account_status WHERE status = ?) WHERE idAccount = ?";
    /**
     * Request to debit the account amound in the database.
     */
    public static final String SQL_UPDATE_ACCOUNT_AMOUND = "UPDATE st4db.account SET amound = ? WHERE idAccount = ?";
     /**
     * Request to retrieve amound ny account id.
     */
    public static final String SQL_SELECT_AMOUND_BY_ACCOUNT_ID = "SELECT amound FROM st4db.account WHERE idAccount = ?";
    /**
     * Request to delete payment objects .
     */
    public static final String SQL_DELETE_ACCOUNT = " DELETE FROM st4db.account WHERE idAccount = ?";



    /**
     * Object of connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(AccountDAOImpl.class);

    /**
     * A constructor that takes a pool of connections.
     *
     * @param dataSource - connection pool.
     */
    public AccountDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Updates the account status.
     *
     * @param accountId     - account ID.
     * @param accountStatus - status.
     * @return - true (if status was updated).
     */
    @Override
    public boolean updateAccountStatus(int accountId, String accountStatus) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ACCOUNT_STATUS)) {
            preparedStatement.setString(1, accountStatus);
            preparedStatement.setLong(2, accountId);
            int changes = preparedStatement.executeUpdate();
            if (changes > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    /**
     * Updates the account amound.
     *
     * @param accountId     - account ID.
     * @param paymentTotal - payment total.
     * @return - true (if status was updated).
     */
    @Override
    public boolean changeAccountAmound(int accountId, double paymentTotal) {
        double amound = getAmoundByAccountId(accountId);

        amound += paymentTotal;
        if(amound < 0){
            throw new DAOException("Account amound insufficient!");
        }

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ACCOUNT_AMOUND)) {
            preparedStatement.setDouble(1, amound);
            preparedStatement.setLong(2, accountId);
            int changes = preparedStatement.executeUpdate();
            if (changes > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return false;
    }

////разобраться
    public double getAmoundByAccountId(int accountId){
        Connection connection = null;
        ResultSet rs = null;
        double amound;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_AMOUND_BY_ACCOUNT_ID)) {
            preparedStatement.setLong(1, accountId);
            rs = preparedStatement.executeQuery();
            rs.next();
                amound = rs.getDouble("amound");
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return amound;
    }

    /**
     * A method that saves the specified object.
     *
     * @param account - object name.
     * @return - this object.
     */
    @Override
    public Account save(Account account) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ACCOUNT_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, account.getAmound());
            preparedStatement.setDate(2, account.getEndDate());
            preparedStatement.setLong(3, account.getAccountUserId());
            preparedStatement.setLong(4, account.getAccountStatusId());
            preparedStatement.setLong(5, account.getAccountNameId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setIdAccount(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while saving user!", e);
        } finally {
            closeConnection(connection);
        }
        return account;
    }

    /**
     * The method that gets the object by the identifier.
     *
     * @param id - ID.
     * @return - object by this ID.
     */
    @Override
    public Account getById(Integer id) {
        List<Account> accountList = null;
        Account account = null;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            accountList = extractResultSet(rs);

            if (accountList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            account = accountList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return account;
    }

    /**
     * Method to delete an object.
     *
     * @param accountId - id object to be deleted.
     * @return - true (if object was removed).
     */
    @Override
    public boolean delete(Integer accountId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ACCOUNT)) {
            preparedStatement.setLong(1, accountId);
            int changes = preparedStatement.executeUpdate();
            if (changes > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    /**
     * Method for retrieving all table objects.
     *
     * @return - got objects.
     */
    @Override
    public List<Account> getAll() {
        List<Account> accountList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            accountList = extractResultSet(rs);

        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return accountList;
    }

    /**
     * Method for retrieving all table objects by date.
     *
     * @return - got objects.
     */
    public List<Account> getAllByDate(Date date) {
        List<Account> accountList = null;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_DATE)) {
            preparedStatement.setDate(1, date);
            rs = preparedStatement.executeQuery();
            accountList = extractResultSet(rs);

        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return accountList;
    }

    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<Account> extractResultSet(final ResultSet rs) throws SQLException {
        List<Account> accountList = new ArrayList<>();
        Account account;
        while (rs.next()) {
            account = new Account();
            account.setIdAccount(rs.getInt("idAccount"));
            account.setAmound(rs.getDouble("amound"));
            account.setEndDate(rs.getDate("end_date"));
            account.setAccountUserId(rs.getInt("user_id"));
            account.setAccountStatusId(rs.getInt("account_status_id"));
            account.setAccountNameId(rs.getInt("account_name_id"));
            accountList.add(account);
        }
        return accountList;
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
