package ua.nure.levushevskiy.SummaryTask4.service.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentNameDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentNameDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.PaymentName;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentNameService;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentTypeService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

import java.util.List;

public class PaymentNameServiceImpl implements PaymentNameService{

    /**
     * The object to interact with the table 'payment_name' in database.
     */
    private final PaymentNameDAO paymentNameDAO;

    /**
     * Object to Convert PaymentTypeService to PaymentTypeServicesDTO.
     */
    private final PaymentTypeService paymentTypeService;

    public PaymentNameServiceImpl(PaymentNameDAO paymentNameDAO, final PaymentTypeService paymentTypeService) {
        this.paymentNameDAO = paymentNameDAO;
        this.paymentTypeService = paymentTypeService;
    }


    /**
     * Getting payment name by id.
     *
     * @param id - payment name id.
     * @return - PaymentNameDTO object.
     */
    @Override
    public PaymentNameDTO getById(int id) {
        PaymentName paymentName = paymentNameDAO.getPaymentName(id);
        return Transformer.paymentName2PaymentNameDTO(paymentName,paymentTypeService);
    }

    /**
     * Getting all payment name.
     *
     * @return list of payment name.
     */
    @Override
    public List<PaymentNameDTO> getAll() {
        return  null;//Transformer.paymentTypeList2PaymentTypeDTOList(paymentNameDAO.getAll());
    }
}
