package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentStatus;

public class PaymentStatusDAOImpl implements PaymentStatusDAO {

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
        return null;
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
}
