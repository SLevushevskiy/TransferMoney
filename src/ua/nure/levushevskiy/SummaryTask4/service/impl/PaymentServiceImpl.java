package ua.nure.levushevskiy.SummaryTask4.service.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.Payment;
import ua.nure.levushevskiy.SummaryTask4.service.api.*;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(PaymentServiceImpl.class);

    /**
     * The object to interact with the table 'payment' in database.
     */
    private final PaymentDAO paymentDAO;

    /**
     * Object to Convert AccountService to AccountServiceDTO.
     */
    private final AccountService accountService;

    /**
     * Object to Convert PaymentStatusService to PaymentStatusServiceDTO.
     */
    private final PaymentStatusService paymentStatusService;

    /**
     * Object to Convert PaymentNameService to PaymentNameServiceDTO.
     */
    private final PaymentNameService paymentNameService;

    public PaymentServiceImpl(final PaymentDAO paymentDAO,final AccountService accountService,final PaymentStatusService paymentStatusService,final PaymentNameService paymentNameService) {
        this.paymentDAO = paymentDAO;
        this.accountService = accountService;
        this.paymentStatusService = paymentStatusService;
        this.paymentNameService = paymentNameService;
    }

    /**
     * Saving payment.
     *
     * @param paymentDTO - saved object.
     * @return - PaymentDTO object.
     */
    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        Payment payment = Transformer.paymentDTO2Payment(paymentDTO);
        paymentDTO =  Transformer.payment2PaymentDTO(paymentDAO.save(payment),accountService, paymentStatusService, paymentNameService);
        LOG.info("New payment was added!");
        return paymentDTO;
    }



    /**
     * Getting payment by id.
     *
     * @param id - payment id.
     * @return - PaymentDTO object.
     */
    @Override
    public PaymentDTO getById(int id) {
        return Transformer.payment2PaymentDTO(paymentDAO.getById(id),accountService, paymentStatusService, paymentNameService);
    }

    /**
     * Delete payment by id.
     *
     * @param id - payment id.
     * @return - PaymentDTO object.
     */
    @Override
    public boolean delete(int id) {
        boolean key = paymentDAO.delete(id);
        if(key)
            LOG.info("Payment was delete!");
        else
            LOG.info("Payment was not delete!");
        return key;
    }

    /**
     * Getting all payments.
     *
     * @return list of payments.
     */
    @Override
    public List<PaymentDTO> getAll() {
        return Transformer.paymentList2PaymentDTOList(paymentDAO.getAll(),accountService, paymentStatusService, paymentNameService);
    }

    /**
     * Update payment status by id.
     *
     * @param id     - payment id.
     * @param status - new status.
     * @return - true (if object was updated).
     */
    @Override
    public boolean updatePaymentStatusById(int id, String status) {
        return paymentDAO.updatePaymentStatus(id, status);
    }
}
