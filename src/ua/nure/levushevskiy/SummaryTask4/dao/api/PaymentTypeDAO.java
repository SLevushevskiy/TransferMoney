package ua.nure.levushevskiy.SummaryTask4.dao.api;

import ua.nure.levushevskiy.SummaryTask4.entity.PaymentType;

import java.util.List;

public interface PaymentTypeDAO {

    /**
     * Gets the type object by payment ID.
     *
     * @param paymentId - payment ID.
     * @return - payment type object.
     */
    PaymentType getPaymentType(int paymentId);

    /**
     * Set the type object by payment ID.
     *
     * @param paymentId - payment ID.
     * @param type - type.
     * @return - payment type object.
     */
    PaymentType setPaymentType(int paymentId, String type);

    /**
     * Gets the list type object by payment type.
     *
     * @return - list of payment type object.
     */
    List<PaymentType> getAll();
}
