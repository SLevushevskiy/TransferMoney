package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
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
import java.util.Collections;
import java.util.List;

import static ua.nure.levushevskiy.SummaryTask4.util.View.PAYMENT_WAIT_JSP;

@WebServlet("/paymentDelete")
public class PaymentDeleteServlet extends HttpServlet {

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
    }

    /**
     * An object that contains payment business logic.
     */
    private PaymentServiceImpl paymentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        paymentService.delete(Integer.parseInt(request.getParameter(EntityConstants.PAYMENT_CHOOSE_PARAM)));
        response.sendRedirect(View.Mapping.PAYMENT_WAIT);
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
}