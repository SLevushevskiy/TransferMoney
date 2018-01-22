package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentNameDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentNameServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.levushevskiy.SummaryTask4.util.View.PAYMENT_RECHARGE_JSP;
import static ua.nure.levushevskiy.SummaryTask4.util.View.PAYMENT_TRANSFER_JSP;

@WebServlet("/trasferPayment")
public class TransferPaymentServlet extends HttpServlet {
    /**
     * An object that contains payment business logic.
     */
    PaymentServiceImpl paymentService;

    /**
     * An object that contains payment name logic.
     */
    PaymentNameServiceImpl paymentNameService;

    /**
     * An object that contains account business logic.
     */
    private AccountServiceImpl accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<PaymentNameDTO> paymentNameDTOList = paymentNameService.getAll();
        req.setAttribute(EntityConstants.PAYMENT_NAME_LIST_PARAM, paymentNameDTOList);
        List<AccountDTO> accountDTOList = accountService.getAll();
        accountDTOList = removeAccount(accountDTOList, Integer.parseInt(session.getAttribute(EntityConstants.USER_ID_PARAM).toString()));
        req.setAttribute(EntityConstants.ACCOUNT_LIST_PARAM, accountDTOList);
        req.getRequestDispatcher(PAYMENT_TRANSFER_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//создаем сессию
        int accountId = Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_CHOOSE_PARAM));
        AccountDTO accountDTO = accountService.getById(accountId);
        PaymentDTO paymentDTO = getPaymentFromRequest(req);
        paymentDTO = paymentService.savePayment(paymentDTO);
        AccountDTO accountDTO2 = accountService.getById(Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_ID_PARAM)));
        if(!accountDTO2.getAccountStatusDTO().getStatus().equals("active")){
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция не выполнена! Карта получателя заблокирована.");
            resp.sendRedirect(View.Mapping.PAYMENT_TRANSFER+"#zatemnenie");//redirect
            return;
        }
        if(accountDTO.getAmound()+paymentDTO.getTotal()<0){
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция не выполнена! Недостаточно средств.");
            resp.sendRedirect(View.Mapping.PAYMENT_TRANSFER+"#zatemnenie");//redirect
            return;
        }
        session.setAttribute(EntityConstants.PAYMENT_NAME_PARAM, paymentNameService.getById(1));//для 2-го платежа пополнение
        session.setAttribute(EntityConstants.ACCOUNT_ID_PARAM, req.getParameter(EntityConstants.ACCOUNT_ID_PARAM));
        session.setAttribute(EntityConstants.ACCOUNT_NAME_PARAM,accountDTO.getAccountNameDTO().getName());
        session.setAttribute(EntityConstants.ACCOUNT_AMOUND_PARAM,accountDTO.getAmound());
        session.setAttribute(EntityConstants.ACCOUNT_CHOOSE_PARAM,accountId);
        session.setAttribute(EntityConstants.TRANSFER_PAYMENT, true);
        session.setAttribute(EntityConstants.PAYMENT_PARAM, paymentDTO);
        session.setAttribute(EntityConstants.ACCOUNT_ID_PARAM, req.getParameter(EntityConstants.ACCOUNT_ID_PARAM));

        resp.sendRedirect(View.Mapping.CONFIRM_PAYMENT);//redirect
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
        initPaymentTypeService(context);
        initAccountService(context);
    }

    /**
     * A method that receives information from request.
     *
     * @param req - request.
     * @return - formed object with the received data.
     */
    private PaymentDTO getPaymentFromRequest(final HttpServletRequest req) {
        Date datePayment = new Date(System.currentTimeMillis());

        PaymentDTO paymentDTO = new PaymentDTO();
        //ВЫБОР СЧЕТА НА ВИЮХЕ ДЛЯ ОПЛАТЫ
        paymentDTO.setPaymentNameDTO(paymentNameService.getById(Integer.parseInt(req.getParameter(EntityConstants.PAYMENT_NAME_PARAM))));
        paymentDTO.setAccountDTO(accountService.getById(Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_CHOOSE_PARAM))));
        paymentDTO.setTotal(Double.parseDouble(req.getParameter(EntityConstants.PAYMENT_TOTAL_PARAM).toString()));
        paymentDTO.setDescription((String) req.getParameter(EntityConstants.PAYMENT_DESCRIPTION_PARAM));
        paymentDTO.setDatePayment(datePayment);
        return paymentDTO;
    }

    /**
     * Method that initializes payment service.
     *
     * @param context - servlet context.
     */
    private void initPaymentService(final ServletContext context) {
        paymentService = (PaymentServiceImpl) context.getAttribute(EntityConstants.PAYMENT_SERVICE);
        if (paymentService == null) {
            throw new InitializationException("Account service is not initialized!");
        }
    }

    /**
     * Method that initializes payment type service.
     *
     * @param context - servlet context.
     */
    private void initPaymentTypeService(final ServletContext context) {
        paymentNameService = (PaymentNameServiceImpl) context.getAttribute(EntityConstants.PAYMENT_NAME_SERVICE);
        if (paymentNameService == null) {
            throw new InitializationException("Payment Type service is not initialized!");
        }
    }

    /**
     * Method that initializes user service.
     *
     * @param context - servlet context.
     */
    private void initAccountService(final ServletContext context) {
        accountService = (AccountServiceImpl) context.getAttribute(EntityConstants.ACCOUNT_SERVICE);
        if (accountService == null) {
            throw new InitializationException("Account service is not initialized!");
        }
    }

    /**
     *  The method that removes the accounts not of user from the list.
     *
     * @param accountDTOList - account.
     * @param userId - user id.
     * @return - list accounts.
     */
    private List<AccountDTO> removeAccount(final List<AccountDTO> accountDTOList,int userId) {
        List<AccountDTO> modifiedList = new ArrayList<>();
        for (AccountDTO accountDTO : accountDTOList) {
            if (accountDTO.getUserDTO().getIdUser()==userId & accountDTO.getAccountStatusDTO().getStatus().equals("active")) {
                modifiedList.add(accountDTO);
            }
        }
        return modifiedList;
    }

}
