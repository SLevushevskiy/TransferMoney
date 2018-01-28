package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentTypeDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentTypeDAOImpl implements PaymentTypeDAO {

    /**
     * Request to retrieve payment type objects by ID.
     */
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.payment_type WHERE idPayment_type = ?";

    /**
     * Request to retrieve all payment type objects.
     */
    public static final String SQL_SELECT_ALL = "SELECT * FROM st4db.payment_type";

    /**
     * Object of connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(PaymentTypeDAOImpl.class);

    /**
     * A constructor that takes a pool of connections.
     *
     * @param dataSource - connection pool.
     */
    public PaymentTypeDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Gets the type object by payment ID.
     *
     * @param paymentId - payment ID.
     * @return - payment type object.
     */
    @Override
    public PaymentType getPaymentType(int paymentId) {
        PaymentType paymentType = null;

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
            List<PaymentType> statusList = extractResultSet(rs);

            if (statusList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            paymentType = statusList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return paymentType;
    }

    /**
     * Set the type object by payment ID.
     *
     * @param paymentId - payment ID.
     * @param type      - type.
     * @return - payment type object.
     */
    @Override
    public PaymentType setPaymentType(int paymentId, String type) {
        return null;
    }

    /**
     * Gets the list type object by payment type.
     *
     * @return - list of payment type object.
     */
    @Override
    public List<PaymentType> getAll() {
        List<PaymentType> paymentTypeList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            paymentTypeList = extractResultSet(rs);

        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return paymentTypeList;
    }

    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<PaymentType> extractResultSet(final ResultSet rs) throws SQLException {
        List<PaymentType> statusList = new ArrayList<>();
        PaymentType paymentType;
        while (rs.next()) {
            paymentType = new PaymentType();
            paymentType.setIdPaymentType(rs.getInt("idPayment_type"));
            paymentType.setType(rs.getString("type"));
            statusList.add(paymentType);
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
