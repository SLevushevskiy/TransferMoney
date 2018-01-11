package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.PaymentStatus;

public interface PaymentStatusDAO {
    /**
     *
     * Gets the status object by payment ID.
     *
     * @param paymentId     - payment ID.
     * @return - payment status object
     */
    PaymentStatus getPaymentStatus(int paymentId);

    /**
     *
     * Sets the status object by payment ID.
     *
     * @param paymentId     - payment ID.
     * @param status     - status.
     * @return - paymentStatus object
     */
    PaymentStatus setPaymentStatus(int paymentId, String status);
}
