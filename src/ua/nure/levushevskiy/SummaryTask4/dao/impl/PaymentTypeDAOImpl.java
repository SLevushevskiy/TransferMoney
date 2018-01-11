package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentTypeDAO;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentType;

public class PaymentTypeDAOImpl implements PaymentTypeDAO {

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
        return null;
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
}
