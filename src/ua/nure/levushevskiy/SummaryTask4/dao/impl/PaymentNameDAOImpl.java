package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentNameDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentNameDAOImpl implements PaymentNameDAO {

    /**
     * Request to retrieve payment name objects by ID.
     */
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.payment_name WHERE idpayment_name = ?";
    /**
     * Request to retrieve all payment name objects.
     */
    public static final String SQL_SELECT_ALL = "SELECT * FROM st4db.payment_name";

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
    public PaymentNameDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets the name object by payment name ID.
     *
     * @param paymentId - payment name ID.
     * @return - payment name object.
     */
    @Override
    public PaymentName getPaymentName(int paymentId) {
        PaymentName paymentName = null;

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
            List<PaymentName> statusList = extractResultSet(rs);

            if (statusList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            paymentName = statusList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return paymentName;
    }

    /**
     * Set the name and type object by payment name ID.
     *
     * @param paymentId - payment ID.
     * @param name      - name.
     * @param type      - payment type id.
     * @return - payment name object.
     */
    @Override
    public PaymentName setPaymentName(int paymentId, String name, int type) {
        return null;
    }

    /**
     * Gets the list name object by payment name.
     *
     * @return - list of payment name object.
     */
    @Override
    public List<PaymentName> getAll() {
        List<PaymentName> paymentNameList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            paymentNameList = extractResultSet(rs);

        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return paymentNameList;
    }

    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<PaymentName> extractResultSet(final ResultSet rs) throws SQLException {
        List<PaymentName> statusList = new ArrayList<>();
        PaymentName paymentName;
        while (rs.next()) {
            paymentName = new PaymentName();
            paymentName.setIdPaymentName(rs.getInt("idpayment_name"));
            paymentName.setPaymentName(rs.getString("payment_name"));
            paymentName.setPaymentTypeId(rs.getInt("payment_type_id"));
            statusList.add(paymentName);
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
