package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentStatusDAOImpl implements PaymentStatusDAO {

    /**
     * Request to retrieve payment status objects by ID.
     */
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.payment_status WHERE idPayment_status = ?";

    /**
     * Request to retrieve all payment status objects.
     */
    public static final String SQL_SELECT_ALL = "SELECT * FROM st4db.payment_status";


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
    public PaymentStatusDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets the status object by payment ID.
     *
     * @param paymentId - payment ID.
     * @return - payment status object
     */
    @Override
    public PaymentStatus getPaymentStatus(int paymentId) {
        PaymentStatus paymentStatus = null;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, paymentId);
            rs = preparedStatement.executeQuery();
            List<PaymentStatus> statusList = extractResultSet(rs);

            if (statusList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            paymentStatus = statusList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return paymentStatus;
    }

    /**
     * Sets the status object by payment ID.
     *
     * @param paymentId - payment ID.
     * @param status    - status.
     * @return - paymentStatus object
     */
    @Override
    public PaymentStatus setPaymentStatus(int paymentId, String status) {
        return null;
    }


    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<PaymentStatus> extractResultSet(final ResultSet rs) throws SQLException {
        List<PaymentStatus> statusList = new ArrayList<>();
        PaymentStatus paymentStatus;
        while (rs.next()) {
            paymentStatus = new PaymentStatus();
            paymentStatus.setIdPaymentStatus(rs.getInt("idPayment_status"));
            paymentStatus.setStatus(rs.getString("status"));
            statusList.add(paymentStatus);
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
