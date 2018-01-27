package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.api.UserService;
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
import java.util.List;

import static ua.nure.levushevskiy.SummaryTask4.util.View.ADMIN_USER_LIST_JSP;


@WebServlet("/userList")
public class AdminUserListServlet extends HttpServlet {

    /**
     * An object that contains account business logic.
     */
    UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initUserService(context);
    }

    private void initUserService(ServletContext context) {
        userService = (UserServiceImpl) context.getAttribute(EntityConstants.USER_SERVICE);
        if (userService == null) {
            throw new InitializationException("Account service is not initialized!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> userDTOList = userService.getAll();
        req.setAttribute(EntityConstants.USER_LIST_PARAM, userDTOList);
        req.getRequestDispatcher(ADMIN_USER_LIST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(EntityConstants.ERROR_CONTAINER_PARAM);
        int userId = Integer.parseInt(req.getParameter(EntityConstants.USER_CHOOSE_PARAM));
        if(userService.updateUserStatus(userId,req.getParameter(EntityConstants.STATUS_PARAM)))
        {
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Операция успешна!");
        }
        else{
            session.setAttribute(EntityConstants.OPERATION_SUCCESSFUL, "Повторите попытку.");
        }
        resp.sendRedirect(View.Mapping.ADMIN_USER_LIST + "#zatemnenie");
    }
}
