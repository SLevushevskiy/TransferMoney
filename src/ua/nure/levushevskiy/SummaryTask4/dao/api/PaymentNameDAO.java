package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.PaymentName;

import java.util.List;

public interface PaymentNameDAO {

    /**
     * Gets the name object by payment name ID.
     *
     * @param paymentId - payment name ID.
     * @return - payment name object.
     */
    PaymentName getPaymentName(int paymentId);

    /**
     * Set the name and type object by payment name ID.
     *
     * @param paymentId - payment ID.
     * @param name - name.
     * @param type - payment type id.
     * @return - payment name object.
     */
    PaymentName setPaymentName(int paymentId, String name, int type);

    /**
     * Gets the list name object by payment name.
     *
     * @return - list of payment name object.
     */
    List<PaymentName> getAll();
}
