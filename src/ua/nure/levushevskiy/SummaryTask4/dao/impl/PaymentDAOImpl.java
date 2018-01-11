package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentDAO;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

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
        return null;
    }

    /**
     * The method that gets the object by the identifier.
     *
     * @param integer - ID.
     * @return - object by this ID.
     */
    @Override
    public Payment getById(Integer integer) {
        return null;
    }

    /**
     * Method to delete an object.
     *
     * @param payment - object to be deleted.
     * @return - true (if object was removed).
     */
    @Override
    public boolean delete(Payment payment) {
        return false;
    }

    /**
     * Method for updating the state of an object in a table.
     *
     * @param payment - object to be updated.
     * @return - true (if object was updated).
     */
    @Override
    public boolean update(Payment payment) {
        return false;
    }

    /**
     * Method for retrieving all table objects.
     *
     * @return - got objects.
     */
    @Override
    public List<Payment> getAll() {
        return null;
    }
}
