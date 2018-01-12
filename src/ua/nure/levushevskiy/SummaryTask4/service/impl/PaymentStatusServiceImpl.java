package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentStatusDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentStatus;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentStatusService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

public class PaymentStatusServiceImpl implements PaymentStatusService {

    /**
     * The object to interact with the table 'payment_status' in database.
     */
    private final PaymentStatusDAO paymentStatusDAO;

    public PaymentStatusServiceImpl(PaymentStatusDAO paymentStatusDAO) {
        this.paymentStatusDAO = paymentStatusDAO;
    }

    /**
     * Getting payment status by id.
     *
     * @param id - payment status id.
     * @return - PaymentStatusDTO object.
     */
    @Override
    public PaymentStatusDTO getById(int id) {
        PaymentStatus paymentStatus = paymentStatusDAO.getPaymentStatus(id);
        return Transformer.paymentStatus2PaymentStatusDTO(paymentStatus);
    }
}
