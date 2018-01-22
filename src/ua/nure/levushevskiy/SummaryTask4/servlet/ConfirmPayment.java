package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentNameDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.Cryptographer;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;
import ua.nure.levushevskiy.SummaryTask4.validation.EmailValidator;
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

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
        initAccountService(context);
        initUserService(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CONFIRM_PAYMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//создаем сессию
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
            if(userDTO.getPassword().equals(password)){
                PaymentDTO paymentDTO = (PaymentDTO) session.getAttribute(EntityConstants.PAYMENT_PARAM);
                int accountId = (int) paymentDTO.getAccountDTO().getIdAccount();
                if(!accountService.changeAccountAmound(accountId, paymentDTO.getTotal())){
                    throw new IllegalStateException();
                }
                paymentService.updatePaymentStatusById((int)paymentDTO.getIdPayment(),"sent");
                int paymentId = (int)paymentDTO.getIdPayment();
                paymentDTO = paymentService.getById(paymentId);
                session.setAttribute(EntityConstants.PAYMENT_PARAM, paymentDTO);
                if(session.getAttribute(EntityConstants.TRANSFER_PAYMENT)!=null&&
                (boolean)session.getAttribute(EntityConstants.TRANSFER_PAYMENT)){
                    transferPayment(paymentDTO,req);
                }
                session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция успешна!");
            }
        } catch (Exception e) {
            errorContainer.put(EntityConstants.ERROR_PARAM, e.getMessage());
            session.setAttribute(EntityConstants.ERROR_CONTAINER_PARAM, errorContainer);
            resp.sendRedirect(View.Mapping.CONFIRM_PAYMENT+"#zatemnenie");
            return;
        }
        resp.sendRedirect(View.Mapping.REPORT_PAYMENT);
    }

    private boolean transferPayment(final PaymentDTO paymentDTO,final HttpServletRequest req){
        HttpSession session = req.getSession();//создаем сессию
        session.getAttribute(EntityConstants.PAYMENT_PARAM);//перевод
        paymentDTO.setPaymentNameDTO((PaymentNameDTO) session.getAttribute(EntityConstants.PAYMENT_NAME_PARAM));//для 2-го платежа пополнение
        int accountId = Integer.parseInt((String) session.getAttribute(EntityConstants.ACCOUNT_ID_PARAM));
        paymentDTO.setAccountDTO(accountService.getById(accountId));//2-й аккаунт
        paymentDTO.setTotal(Math.abs(paymentDTO.getTotal()));//сумма для пополнения
        //пополнение
        if(!accountService.changeAccountAmound(accountId,paymentDTO.getTotal())){
            throw new IllegalStateException();
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
}
