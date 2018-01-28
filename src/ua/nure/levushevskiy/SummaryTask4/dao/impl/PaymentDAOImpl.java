package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    /**
     * Request to retrieve all payment objects by ID.
     */
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.payment WHERE idPayment = ?";
    /**
     * Request to retrieve all account objects.
     */
    public static final String SQL_SELECT_ALL = "SELECT * FROM st4db.payment";
    /**
     * Request for insert account.
     */
    public static final String SQL_PAYMENT_INSERT = "INSERT INTO st4db.payment (date, total, description, account_id, status_id, payment_name_id) "
            + "VALUES(?, ?, ?, ?, ?, ?)";

    /**
     * Request to update the payment status in the database.
     */
    public static final String SQL_UPDATE_PAYMENT_STATUS = "UPDATE st4db.payment SET status_id = "
            + "(SELECT idPayment_status FROM st4db.payment_status WHERE status = ?) WHERE idPayment = ?";
    /**
     * Request to delete payment objects .
     */
    public static final String SQL_DELETE_PAYMENT = " DELETE FROM st4db.payment WHERE idPayment = ?";

    /**
     * Object of connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(PaymentDAOImpl.class);

    /**
     * A constructor that takes a pool of connections.
     *
     * @param dataSource - connection pool.
     */
    public PaymentDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Updates the payment status.
     *
     * @param paymentId     - payment ID.
     * @param paymentStatus - status.
     * @return - true (if status was updated).
     */
    @Override
    public boolean updatePaymentStatus(int paymentId, String paymentStatus) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PAYMENT_STATUS)) {
            preparedStatement.setString(1, paymentStatus);
            preparedStatement.setLong(2, paymentId);
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
     * A method that saves the specified object.
     *
     * @param payment - object name.
     * @return - this object.
     */
    @Override
    public Payment save(Payment payment) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_PAYMENT_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, payment.getDatePayment());
            preparedStatement.setDouble(2, payment.getTotal());
            preparedStatement.setNString(3, payment.getDescription());
            preparedStatement.setLong(4, payment.getAccountId());
            preparedStatement.setLong(5, payment.getStatusId());
            preparedStatement.setLong(6, payment.getPaymentNameId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    payment.setIdPayment(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while saving payment!", e);
        } finally {
            closeConnection(connection);
        }
        return payment;
    }

    /**
     * The method that gets the object by the identifier.
     *
     * @param id - ID.
     * @return - object by this ID.
     */
    @Override
    public Payment getById(Integer id) {
        List<Payment> paymentList = null;
        Payment payment = null;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            paymentList = extractResultSet(rs);

            if (paymentList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            payment = paymentList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return payment;
    }

    /**
     * Method to delete an object.
     *
     * @param paymentId - id object to be deleted.
     * @return - true (if object was removed).
     */
    @Override
    public boolean delete(Integer paymentId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PAYMENT)) {
            preparedStatement.setLong(1, paymentId);
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
    public List<Payment> getAll() {
        List<Payment> paymentList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            paymentList = extractResultSet(rs);

        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return paymentList;
    }

    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<Payment> extractResultSet(final ResultSet rs) throws SQLException {
        List<Payment> statusList = new ArrayList<>();
        Payment payment;
        while (rs.next()) {
            payment = new Payment();
            payment.setIdPayment(rs.getInt("idPayment"));
            payment.setDatePayment(rs.getDate("date"));
            payment.setTotal(rs.getDouble("total"));
            payment.setDescription(rs.getString("description"));
            payment.setAccountId(rs.getLong("account_id"));
            payment.setStatusId(rs.getLong("status_id"));
            payment.setPaymentNameId(rs.getLong("payment_name_id"));
            statusList.add(payment);
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
