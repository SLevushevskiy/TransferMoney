package ua.nure.levushevskiy.SummaryTask4.service.api;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;

import java.util.List;

/**
 * The interface that defines the logic of working with the entity Payment.
 */
public interface PaymentService {

    /**
     * Saving payment.
     *
     * @param paymentDTO - saved object.
     * @return - PaymentDTO object.
     */
    PaymentDTO savePayment(PaymentDTO paymentDTO);

    /**
     * Getting payment by id.
     *
     * @param id - payment id.
     * @return - PaymentDTO object.
     */
    PaymentDTO getById(int id);

    /**
     * Delete payment by id.
     *
     * @param id - payment id.
     * @return - PaymentDTO object.
     */
    boolean delete(int id);

    /**
     * Getting all payments.
     *
     * @return list of payments.
     */
    List<PaymentDTO> getAll();

    /**
     * Update payment status by id.
     *
     * @param id - payment id.
     * @param status - new status.
     * @return - true (if object was updated).
     */
    boolean updatePaymentStatusById(int id,String status);

}
