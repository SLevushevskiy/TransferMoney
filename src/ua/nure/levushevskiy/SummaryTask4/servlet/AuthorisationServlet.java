package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.levushevskiy.SummaryTask4.util.View.LOGIN_JSP;

@WebServlet("/authorisation")
public class AuthorisationServlet extends HttpServlet {

    /**
     * An object that contains user business logic.
     */
    private UserServiceImpl userService;

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initUserService(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
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
