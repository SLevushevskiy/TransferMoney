package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentTypeDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentTypeDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentType;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentTypeService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

public class PaymentTypeServiceImpl implements PaymentTypeService {

    /**
     * The object to interact with the table 'payment_status' in database.
     */
    private final PaymentTypeDAO paymentTypeDAO;

    public PaymentTypeServiceImpl(PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }

    /**
     * Getting payment type by id.
     *
     * @param id - payment type id.
     * @return - PaymentTypeDTO object.
     */
    @Override
    public PaymentTypeDTO getById(int id) {
        PaymentType paymentType = paymentTypeDAO.getPaymentType(id);
        return Transformer.paymentType2PaymentTypeDTO(paymentType);
    }
}
