package ua.nure.levushevskiy.SummaryTask4.dao.api;


import ua.nure.levushevskiy.SummaryTask4.entity.Payment;

/**
 * An interface that contains methods for interacting with the database.
 * Namely with the table Payment.
 */
public interface PaymentDAO extends DAO<Payment, Integer> {

    /**
     * Updates the payment status.
     *
     * @param paymentId     - payment ID.
     * @param paymentStatus - status.
     * @return - true (if status was updated).
     */
    boolean updatePaymentStatus(int paymentId, String paymentStatus);

}
