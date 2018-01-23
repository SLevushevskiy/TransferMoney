package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.AccountDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.AccountServiceImpl;
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

import static ua.nure.levushevskiy.SummaryTask4.util.View.ACCOUNT_LIST_JSP;

@WebServlet("/accountList")
public class AccountListServlet extends HttpServlet {

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
        accountDTOList = removeAccount(accountDTOList, Integer.parseInt(session.getAttribute(EntityConstants.USER_ID_PARAM).toString()));
        if(req.getParameter(EntityConstants.SORT_ACCOUNT)!=null){
            switch (req.getParameter(EntityConstants.SORT_ACCOUNT)){
                case "numUp": accountDTOList.sort(Sort.AccountIdCompare); break;
                case "numDown": accountDTOList.sort(Sort.AccountIdCompare);
                    Collections.reverse(accountDTOList); break;
                case "amoundUp": accountDTOList.sort(Sort.AccountAmoundCompare); break;
                case "amoundDown":accountDTOList.sort(Sort.AccountAmoundCompare);
                    Collections.reverse(accountDTOList); break;
                case "nameUp": accountDTOList.sort(Sort.AccountNameCompare); break;
                case "nameDown": accountDTOList.sort(Sort.AccountNameCompare);
                    Collections.reverse(accountDTOList);break;
            }
        }
        req.setAttribute(EntityConstants.ACCOUNT_LIST_PARAM, accountDTOList);
        req.getRequestDispatcher(ACCOUNT_LIST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int accountId = Integer.parseInt(req.getParameter(EntityConstants.ACCOUNT_CHOOSE_PARAM));
        if (accountService.updateAccountStatusById(accountId, req.getParameter(EntityConstants.STATUS_PARAM))) {
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция успешна!");
        } else {
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Повторите попытку.");
        }
       resp.sendRedirect(View.Mapping.ACCOUNT_LIST + "#zatemnenie");
    }

    private void sort(){

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
            if (accountDTO.getUserDTO().getIdUser()==userId) {
                modifiedList.add(accountDTO);
            }
        }
        return modifiedList;
    }
}
