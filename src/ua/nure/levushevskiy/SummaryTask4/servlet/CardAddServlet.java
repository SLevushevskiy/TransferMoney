package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;
import ua.nure.levushevskiy.SummaryTask4.validation.MainValidator;
import ua.nure.levushevskiy.SummaryTask4.validation.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.nure.levushevskiy.SummaryTask4.util.View.CARD_ADD_JSP;

@WebServlet("/cardAdd")
public class CardAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CARD_ADD_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//создаем сессию
        // session.removeAttribute(EntityConstants.AUTHORIZATION_ERROR_CONTAINER_PARAM);

        AccountDTO accountDTO = getAccountFromRequest(req);

            try {
                accountDTO = accountDTO.saveAccount(accountDTO);
            } catch (IllegalStateException e) {
                session.setAttribute(EntityConstants.INVALID_ACCOUNT_PARAM, accountDTO);
                resp.sendRedirect(View.Mapping.REGISTRATION);
                return;

            }
        req.getRequestDispatcher(CARD_ADD_JSP).forward(req, resp);
    }
    /**
     * A method that receives information from request.
     *
     * @param req - request.
     * @return - formed object with the received data.
     */
    private AccountDTO getAccountFromRequest(final HttpServletRequest req) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setIdAccount(Long.parseLong(req.getParameter(EntityConstants.ACCOUNT_ID_PARAM)));
        accountDTO.setAmound(Double.parseDouble(req.getParameter(EntityConstants.ACCOUNT_AMOUND_PARAM)));
       // Взять юзера из базы
       // accountDTO.setUserDTO(req.getParameter(EntityConstants.ACCOUNT_USER_ID_PARAM));
       // accountDTO.setAccountNameDTO(req.getParameter(EntityConstants.ACCOUNT_NAME_PARAM));
        return accountDTO;
    }

}
