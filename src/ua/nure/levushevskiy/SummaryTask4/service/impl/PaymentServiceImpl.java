package ua.nure.levushevskiy.SummaryTask4.service.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentDAO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.entity.Payment;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountService;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentService;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentStatusService;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentTypeService;
import ua.nure.levushevskiy.SummaryTask4.util.Transformer;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

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
     * Object to Convert PaymentTypeService to PaymentTypeServicesDTO.
     */
    private final PaymentTypeService paymentTypeService;

    public PaymentServiceImpl(final PaymentDAO paymentDAO,final AccountService accountService,final PaymentStatusService paymentStatusService,final PaymentTypeService paymentTypeService) {
        this.paymentDAO = paymentDAO;
        this.accountService = accountService;
        this.paymentStatusService = paymentStatusService;
        this.paymentTypeService = paymentTypeService;
    }

    /**
     * Saving payment.
     *
     * @param paymentDTO - saved object.
     * @return - PaymentDTO object.
     */
    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        Payment account = Transformer.paymentDTO2Payment(paymentDTO);
        paymentDTO =  Transformer.payment2PaymentDTO(paymentDAO.save(account),accountService, paymentStatusService, paymentTypeService);
        LOG.info("New account was added!");
        //LOG.info("Sending confirmation letter.");
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
        return Transformer.payment2PaymentDTO(paymentDAO.getById(id),accountService, paymentStatusService, paymentTypeService);
    }

    /**
     * Getting all payments.
     *
     * @return list of payments.
     */
    @Override
    public List<PaymentDTO> getAll() {
        return Transformer.paymentList2PaymentDTOList(paymentDAO.getAll(),accountService, paymentStatusService, paymentTypeService);
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