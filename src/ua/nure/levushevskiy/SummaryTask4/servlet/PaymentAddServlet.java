package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentTypeDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.api.PaymentStatusService;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentStatusServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentTypeServiceImpl;
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
import java.util.List;

import static ua.nure.levushevskiy.SummaryTask4.util.View.Mapping.PAYMENT_LIST_JSP;
import static ua.nure.levushevskiy.SummaryTask4.util.View.PAYMENT_ADD_JSP;

@WebServlet("/paymentAdd")
public class PaymentAddServlet extends HttpServlet {

    /**
     * An object that contains payment business logic.
     */
    PaymentServiceImpl paymentService;

    /**
     * An object that contains payment type logic.
     */
    PaymentTypeServiceImpl paymentTypeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PaymentTypeDTO> paymentTypeDTOList = paymentTypeService.getAll();
        req.setAttribute(EntityConstants.PAYMENT_TYPE_LIST_PARAM, paymentTypeDTOList);
        req.getRequestDispatcher(PAYMENT_ADD_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//создаем сессию
        // session.removeAttribute(EntityConstants.AUTHORIZATION_ERROR_CONTAINER_PARAM);

        PaymentDTO paymentDTO = getPaymentFromRequest(req);

        try {
        //    accountDTO = accountService.saveAccount(accountDTO);
        } catch (IllegalStateException e) {
       //     session.setAttribute(EntityConstants.INVALID_ACCOUNT_PARAM, accountDTO);
            resp.sendRedirect(View.Mapping.REGISTRATION);
            return;

        }
        resp.sendRedirect(View.Mapping.ACCOUNT_LIST_JSP);//redirect
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
    }

    /**
     * A method that receives information from request.
     *
     * @param req - request.
     * @return - formed object with the received data.
     */
    private PaymentDTO getPaymentFromRequest(final HttpServletRequest req) {
        HttpSession session = req.getSession();
        Date endDate = new Date(System.currentTimeMillis());

        PaymentDTO paymentDTO = new PaymentDTO();
        //ВЫБОР СЧЕТА НА ВИЮХЕ ДЛЯ ОПЛАТЫ
      //  paymentDTO.setAccountDTO((AccountDTO) session.getAttribute(EntityConstants.ACCOUNT_PARAM));
     //   paymentDTO.setAccountNameDTO(accountNameService.getById(Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_NAME_PARAM))));
      //  paymentDTO.setEndDate(endDate);
        // Взять юзера из базы
        // accountDTO.setUserDTO(req.getParameter(EntityConstants.ACCOUNT_USER_ID_PARAM));
        // accountDTO.setAccountNameDTO(req.getParameter(EntityConstants.ACCOUNT_NAME_PARAM));
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

}
