package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.*;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.*;
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

@WebServlet("/rechargePayment")
public class PaymentRechargeServlet extends HttpServlet {

    public static final String OPERATION_SUCCESSFUL_ERROR = "Ошибка! Повторите операцию.";

    public static final String WINDOW_POP_UP = "#zatemnenie";

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
        List<AccountDTO> accountDTOList = accountService.getAll();
        accountDTOList = removeAccount(accountDTOList, Integer.parseInt(session.getAttribute(EntityConstants.USER_ID_PARAM).toString()));
        req.setAttribute(EntityConstants.ACCOUNT_LIST_PARAM, accountDTOList);
        req.getRequestDispatcher(PAYMENT_RECHARGE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//создаем сессию
        session.removeAttribute(EntityConstants.ERROR_CONTAINER_PARAM);
        session.removeAttribute(EntityConstants.OPERATION_SUCCESSFUL);
        req.setCharacterEncoding("UTF-8");
        try{
        PaymentDTO paymentDTO = getPaymentFromRequest(req);
            paymentDTO = paymentService.savePayment(paymentDTO);
            session.setAttribute(EntityConstants.PAYMENT_PARAM, paymentDTO);
        }catch (Exception e){
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, OPERATION_SUCCESSFUL_ERROR);
            resp.sendRedirect(View.Mapping.PAYMENT_RECHARGE + WINDOW_POP_UP);//redirect
            return;
        }
        resp.sendRedirect(View.Mapping.CONFIRM_PAYMENT);
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
        paymentDTO.setTotal(Double.parseDouble(req.getParameter(EntityConstants.PAYMENT_TOTAL_PARAM).toString().replace(",",".")));
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
            if (accountDTO.getUserDTO().getIdUser()==userId && accountDTO.getAccountStatusDTO().getStatus().equals("active")) {
                modifiedList.add(accountDTO);
            }
        }
        return modifiedList;
    }

}
