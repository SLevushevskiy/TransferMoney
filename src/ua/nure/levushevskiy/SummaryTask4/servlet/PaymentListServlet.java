package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentService;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.Sort;
import ua.nure.levushevskiy.SummaryTask4.util.View;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static ua.nure.levushevskiy.SummaryTask4.util.View.PAYMENT_LIST_JSP;

@WebServlet("/paymentList")
public class PaymentListServlet extends HttpServlet {

    /**
     * An object that contains payment business logic.
     */
    private PaymentServiceImpl paymentService;

    /**
     * An object that contains account business logic.
     */
    private AccountServiceImpl accountService;

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
        initAccountService(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<PaymentDTO> paymentDTOList = paymentService.getAll();
        int accountId =  (int)session.getAttribute(EntityConstants.ACCOUNT_CHOOSE_PARAM);
        paymentDTOList = removePayment(paymentDTOList, accountId);
        if(req.getParameter(EntityConstants.SORT_PAYMENT)!=null){
            switch (req.getParameter(EntityConstants.SORT_PAYMENT)){
                case "numUp": paymentDTOList.sort(Sort.PaymentIdCompare); break;
                case "numDown": paymentDTOList.sort(Sort.PaymentIdCompare);
                    Collections.reverse(paymentDTOList); break;
                case "dateUp": paymentDTOList.sort(Sort.PaymentDateCompare); break;
                case "dateDown":paymentDTOList.sort(Sort.PaymentDateCompare);
                    Collections.reverse(paymentDTOList); break;
            }
        }
        session.setAttribute(EntityConstants.ACCOUNT_CHOOSE_PARAM, accountId);
        req.setAttribute(EntityConstants.ACCOUNT_NAME_PARAM, accountService.getById(accountId).getAccountNameDTO().getName());
        req.setAttribute(EntityConstants.ACCOUNT_AMOUND_PARAM, accountService.getById(accountId).getAmound());
        req.setAttribute(EntityConstants.PAYMENT_LIST_PARAM, paymentDTOList);
        req.getRequestDispatcher(PAYMENT_LIST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<PaymentDTO> paymentDTOList = paymentService.getAll();
        int accountId =  Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_CHOOSE_PARAM).toString());
        paymentDTOList = removePayment(paymentDTOList, accountId);
        session.setAttribute(EntityConstants.ACCOUNT_CHOOSE_PARAM, accountId);
        req.setAttribute(EntityConstants.ACCOUNT_NAME_PARAM, accountService.getById(accountId).getAccountNameDTO().getName());
        req.setAttribute(EntityConstants.ACCOUNT_AMOUND_PARAM, accountService.getById(accountId).getAmound());
        req.setAttribute(EntityConstants.PAYMENT_LIST_PARAM, paymentDTOList);
        req.getRequestDispatcher(PAYMENT_LIST_JSP).forward(req, resp);
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
    private void initAccountService(final ServletContext context) {
        accountService = (AccountServiceImpl) context.getAttribute(EntityConstants.ACCOUNT_SERVICE);
        if (accountService == null) {
            throw new InitializationException("Account service is not initialized!");
        }
    }

    /**
     *  The method that removes the payment not of account from the list.
     *
     * @param paymentDTOList - payment list.
     * @param accountId - account id.
     * @return - list accounts.
     */
    private List<PaymentDTO> removePayment(final List<PaymentDTO> paymentDTOList, int accountId) {
        List<PaymentDTO> modifiedList = new ArrayList<>();
        for (PaymentDTO paymentDTO : paymentDTOList) {
            if (paymentDTO.getAccountDTO().getIdAccount()==accountId &&
                    paymentDTO.getPaymentStatusDTO().getStatus().equals("sent")) {
                modifiedList.add(paymentDTO);
            }
        }
        return modifiedList;
    }

}
