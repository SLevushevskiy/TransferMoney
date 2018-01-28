package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentNameServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.Cryptographer;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;
import ua.nure.levushevskiy.SummaryTask4.validation.PasswordValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.nure.levushevskiy.SummaryTask4.util.View.CONFIRM_PAYMENT_JSP;

@WebServlet("/confirmPayment")
public class ConfirmPayment extends HttpServlet{

    /**
     * An object that contains account business logic.
     */
    private AccountServiceImpl accountService;
    /**
     * An object that contains payment business logic.
     */
    private PaymentServiceImpl paymentService;
    /**
     * An object that contains user business logic.
     */
    private UserServiceImpl userService;

    /**
     * An object that contains payment name logic.
     */
    PaymentNameServiceImpl paymentNameService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
        initPaymentNameService(context);
        initAccountService(context);
        initUserService(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CONFIRM_PAYMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(EntityConstants.ERROR_CONTAINER_PARAM);
        Map<String, String> errorContainer = new HashMap<>();
        String password = req.getParameter(EntityConstants.PASSWORD_PARAM);
        String confirm = req.getParameter(EntityConstants.PASSWORD_CONFIRM_PARAM);
        errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, password, confirm);
        if (!errorContainer.isEmpty()) {
            session.setAttribute(EntityConstants.ERROR_CONTAINER_PARAM, errorContainer);
            resp.sendRedirect(View.Mapping.CONFIRM_PAYMENT+"#zatemnenie");
            return;
        }
        try {
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция не выполнена!");
            UserDTO userDTO = userService.getById(Integer.parseInt(session.getAttribute(EntityConstants.USER_ID_PARAM).toString()));
            password = Cryptographer.md5Custom(password);
            if (userDTO.getPassword().equals(password)) {
                PaymentDTO paymentDTO = (PaymentDTO) session.getAttribute(EntityConstants.PAYMENT_PARAM);
                int accountId = (int) paymentDTO.getAccountDTO().getIdAccount();
                int toAccountId;
                if (paymentDTO.getPaymentNameDTO().getPaymentName().equals("Transfer to the card")) {
                    toAccountId = toAccountId(paymentDTO.getDescription());
                    if (!accountService.getById(toAccountId).getAccountStatusDTO().getStatus().equals("active")) {
                        throw new IllegalStateException("Account blocked");
                    }
                    if (!accountService.changeAccountAmound(accountId, paymentDTO.getTotal())) {
                        throw new IllegalStateException("Amount is insufficiently");
                    }
                    paymentService.updatePaymentStatusById((int)paymentDTO.getIdPayment(),"sent");
                    paymentDTO = paymentService.getById((int)paymentDTO.getIdPayment());
                    session.setAttribute(EntityConstants.PAYMENT_PARAM, paymentDTO);
                    transferPayment(paymentDTO,toAccountId, req);
                } else{
                    if (!accountService.changeAccountAmound(accountId, paymentDTO.getTotal())) {
                        throw new IllegalStateException("Amount is insufficiently");
                    }
                    paymentService.updatePaymentStatusById((int)paymentDTO.getIdPayment(),"sent");
                    paymentDTO = paymentService.getById((int)paymentDTO.getIdPayment());
                    session.setAttribute(EntityConstants.PAYMENT_PARAM, paymentDTO);
                }
                session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция успешна!");
            }
        } catch (Exception e) {
            errorContainer.put(EntityConstants.ERROR_PARAM, e.getMessage());
            session.setAttribute(EntityConstants.ERROR_CONTAINER_PARAM, errorContainer);
            resp.sendRedirect(View.Mapping.CONFIRM_PAYMENT + "#zatemnenie");
            return;
        }
        resp.sendRedirect(View.Mapping.REPORT_PAYMENT);
    }

    private int toAccountId(final String description){
        int start = description.indexOf("[ ")+2;
        String accountId = description.substring(start);
        accountId = accountId.substring(0,accountId.indexOf(" ]"));
        return Integer.parseInt(accountId);
    }

    private boolean transferPayment(final PaymentDTO paymentDTO,final int toAccountId, final HttpServletRequest req){
        paymentDTO.setDescription("Пополнение от "+paymentDTO.getAccountDTO().getUserDTO().getName()+" "+
        paymentDTO.getAccountDTO().getUserDTO().getSurname());
        paymentDTO.setPaymentNameDTO(paymentNameService.getById(1));//для 2-го платежа пополнение
        paymentDTO.setAccountDTO(accountService.getById(toAccountId));//2-й аккаунт
        paymentDTO.setTotal(Math.abs(paymentDTO.getTotal()));//сумма для пополнения
        //пополнение
        if(!accountService.changeAccountAmound(toAccountId,paymentDTO.getTotal())){
            throw new IllegalStateException("Невышло пополнить счет");
        }
        //сохранение платежа
        paymentService.savePayment(paymentDTO);
        return true;
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
     * Method that initializes user service.
     *
     * @param context - servlet context.
     */
    private void initUserService(final ServletContext context) {
        userService = (UserServiceImpl) context.getAttribute(EntityConstants.USER_SERVICE);
        if (userService == null) {
            throw new InitializationException("User service is not initialized!");
        }
    }

    /**
     * Method that initializes payment type service.
     *
     * @param context - servlet context.
     */
    private void initPaymentNameService(final ServletContext context) {
        paymentNameService = (PaymentNameServiceImpl) context.getAttribute(EntityConstants.PAYMENT_NAME_SERVICE);
        if (paymentNameService == null) {
            throw new InitializationException("Payment Type service is not initialized!");
        }
    }
}
