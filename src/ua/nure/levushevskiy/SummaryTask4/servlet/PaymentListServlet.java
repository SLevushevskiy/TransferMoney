package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentService;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static ua.nure.levushevskiy.SummaryTask4.util.View.PAYMENT_LIST_JSP;

@WebServlet("/paymentList")
public class PaymentListServlet extends HttpServlet {

    /**
     * An object that contains payment business logic.
     */
    PaymentServiceImpl paymentService;

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<PaymentDTO> paymentDTOList = paymentService.getAll();
       // paymentDTOList = removePayment(paymentDTOList, Integer.parseInt(session.getAttribute(EntityConstants.ACCOUNT_ID_PARAM).toString()));
        req.setAttribute(EntityConstants.PAYMENT_LIST_PARAM, paymentDTOList);
        req.getRequestDispatcher(PAYMENT_LIST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
     * @param accountId - account id.
     * @return - list accounts.
     */
    private List<PaymentDTO> removePayment(final List<PaymentDTO> paymentDTOList, int accountId) {
        List<PaymentDTO> modifiedList = new ArrayList<>();
        for (PaymentDTO paymentDTO : paymentDTOList) {
            if (paymentDTO.getAccountDTO().getIdAccount()==accountId) {
                modifiedList.add(paymentDTO);
            }
        }
        return modifiedList;
    }

}
