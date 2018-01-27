package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
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

import static ua.nure.levushevskiy.SummaryTask4.util.View.*;

@WebServlet("/paymentWait")
public class PaymentWaitServlet extends HttpServlet {
    /**
     * An object that contains account business logic.
     */
    private AccountServiceImpl accountService;

    /**
     * An object that contains payment business logic.
     */
    private PaymentServiceImpl paymentService;

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initAccountService(context);
        initPaymentService(context);
    }
    @Override
    protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try{
        List<PaymentDTO> paymentDTOList = paymentService.getAll();
        paymentDTOList = removePayment(paymentDTOList,(int)session.getAttribute(EntityConstants.USER_ID_PARAM));
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
        req.setAttribute(EntityConstants.PAYMENT_LIST_PARAM, paymentDTOList);
        } catch (Exception e){
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, e.getMessage());
            resp.sendRedirect(View.Mapping.ERROR);
            return;
        }
        req.getRequestDispatcher(PAYMENT_WAIT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PaymentDTO paymentDTO = paymentService.getById(Integer.parseInt(req.getParameter(EntityConstants.PAYMENT_CHOOSE_PARAM)));
        session.setAttribute(EntityConstants.PAYMENT_PARAM, paymentDTO);
        resp.sendRedirect(Mapping.CONFIRM_PAYMENT);
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
     *  The method that removes the payment not of account from the list.
     *
     * @param paymentDTOList - payment list.
     * @return - list accounts.
     */
    private List<PaymentDTO> removePayment(final List<PaymentDTO> paymentDTOList, int userId) {
        List<PaymentDTO> modifiedList = new ArrayList<>();
        for (PaymentDTO paymentDTO : paymentDTOList) {
            if (paymentDTO.getAccountDTO().getUserDTO().getIdUser()==userId
                    && paymentDTO.getPaymentStatusDTO().getStatus().equals("prepared")) {
                modifiedList.add(paymentDTO);
            }
        }
        return modifiedList;
    }
}
