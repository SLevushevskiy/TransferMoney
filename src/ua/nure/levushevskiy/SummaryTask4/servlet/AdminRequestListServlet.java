package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

import static ua.nure.levushevskiy.SummaryTask4.util.View.ADMIN_ACCOUNT_LIST_JSP;
import static ua.nure.levushevskiy.SummaryTask4.util.View.ADMIN_REQUEST_LIST_JSP;

@WebServlet("/adminRequestList")
public class AdminRequestListServlet extends HttpServlet {
    /**
     * An object that contains account business logic.
     */
    private AccountServiceImpl accountService;

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initAccountService(context);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<AccountDTO> accountDTOList = accountService.getAll();
        accountDTOList = removeAccount(accountDTOList);
        req.setAttribute(EntityConstants.ACCOUNT_LIST_PARAM, accountDTOList);
        req.getRequestDispatcher(ADMIN_REQUEST_LIST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int accountId = Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_CHOOSE_PARAM));
        if(accountService.updateAccountStatusById(accountId, req.getParameter(EntityConstants.STATUS_PARAM)))
        {
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция успешна!");
        }
        else{
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Повторите попытку.");
        }
        resp.sendRedirect(View.Mapping.ADMIN_REQUEST_LIST + "#zatemnenie");
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
     *  The method that removes the accounts not of reqActive status from the list.
     *
     * @param accountDTOList - account.
     * @return - list accounts.
     */
    private List<AccountDTO> removeAccount(final List<AccountDTO> accountDTOList) {
        List<AccountDTO> modifiedList = new ArrayList<>();
        for (AccountDTO accountDTO : accountDTOList) {
            if (accountDTO.getAccountStatusDTO().getStatus().equals("reqActive")) {
                modifiedList.add(accountDTO);
            }
        }
        return modifiedList;
    }
}
