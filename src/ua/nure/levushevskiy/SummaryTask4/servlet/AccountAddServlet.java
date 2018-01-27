package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.AccountNameDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.api.AccountNameService;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountNameServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.service.impl.UserServiceImpl;
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

import static ua.nure.levushevskiy.SummaryTask4.util.View.ACCOUNT_ADD_JSP;
import static ua.nure.levushevskiy.SummaryTask4.util.View.ACCOUNT_LIST_JSP;

@WebServlet("/accountAdd")
public class AccountAddServlet extends HttpServlet {

    /**
     * An object that contains account business logic.
     */
    private AccountServiceImpl accountService;

    /**
     * An object that contains account name business logic.
     */
    private AccountNameServiceImpl accountNameService;

    /**
     * An object that contains user business logic.
     */
    private UserServiceImpl userService;

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initAccountService(context);
        initAccountNameService(context);
        initUserService(context);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AccountNameDTO> accountNameDTOList = accountNameService.getAll();
        req.setAttribute(EntityConstants.ACCOUNT_NAME_LIST_PARAM, accountNameDTOList);
        req.getRequestDispatcher(ACCOUNT_ADD_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//создаем сессию
        session.removeAttribute(EntityConstants.ERROR_CONTAINER_PARAM);
        session.removeAttribute(EntityConstants.OPERATION_SUCCESSFUL);
        try {
            AccountDTO accountDTO = getAccountFromRequest(req);
            accountService.saveAccount(accountDTO);
        } catch (Exception e) {
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL,"Ошибка! Повторите операцию.");
            resp.sendRedirect(View.Mapping.ACCOUNT_ADD + "#zatemnenie");
            return;
        }
        resp.sendRedirect(View.Mapping.ACCOUNT_LIST);
    }

    /**
     * A method that receives information from request.
     *
     * @param req - request.
     * @return - formed object with the received data.
     */
    private AccountDTO getAccountFromRequest(final HttpServletRequest req) {
        HttpSession session = req.getSession();
        Date endDate = new Date(System.currentTimeMillis());
        endDate.setYear(endDate.getYear()+4);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserDTO((UserDTO) session.getAttribute(EntityConstants.USER_PARAM));
        accountDTO.setAccountNameDTO(accountNameService.getById(Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_NAME_PARAM))));
        accountDTO.setEndDate(endDate);
       // Взять юзера из базы
       // accountDTO.setUserDTO(req.getParameter(EntityConstants.ACCOUNT_USER_ID_PARAM));
       // accountDTO.setAccountNameDTO(req.getParameter(EntityConstants.ACCOUNT_NAME_PARAM));
        return accountDTO;
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
     * Method that initializes user service.
     *
     * @param context - servlet context.
     */
    private void initAccountNameService(final ServletContext context) {
        accountNameService = (AccountNameServiceImpl) context.getAttribute(EntityConstants.ACCOUNT_NAME_SERVICE);
        if (accountNameService == null) {
            throw new InitializationException("Account name service is not initialized!");
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
