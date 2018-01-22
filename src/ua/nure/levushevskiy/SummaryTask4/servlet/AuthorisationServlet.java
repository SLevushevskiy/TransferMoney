package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.dto.UserStatusDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;
import ua.nure.levushevskiy.SummaryTask4.validation.EmailValidator;
import ua.nure.levushevskiy.SummaryTask4.validation.PasswordValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.nure.levushevskiy.SummaryTask4.util.View.AUTHORIZATION_JSP;

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
        req.getRequestDispatcher(AUTHORIZATION_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(EntityConstants.ERROR_CONTAINER_PARAM);
        Map<String, String> errorContainer = new HashMap<>();
        String password = req.getParameter(EntityConstants.PASSWORD_PARAM);
        String email = req.getParameter(EntityConstants.EMAIL_PARAM);
        errorContainer = EmailValidator.validateEmail(errorContainer, email);
        errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, password, password);
        if (!errorContainer.isEmpty()) {
            session.setAttribute(EntityConstants.ERROR_CONTAINER_PARAM, errorContainer);
            resp.sendRedirect(View.Mapping.AUTHORIZATION+ "#zatemnenie");
            return;
        }
        try {
            UserDTO userDTO = userService.getByEmailAndPassword(email, password);
            session.setAttribute(EntityConstants.USER_PARAM, userDTO);
            session.setAttribute(EntityConstants.USER_ID_PARAM, userDTO.getIdUser());
        } catch (Exception e) {
            errorContainer.put(EntityConstants.ERROR_PARAM, e.getMessage());
            session.setAttribute(EntityConstants.ERROR_CONTAINER_PARAM, errorContainer);
            resp.sendRedirect(View.Mapping.AUTHORIZATION+ "#zatemnenie");
            return;
        }
        resp.sendRedirect(View.Mapping.ACCOUNT_LIST);
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
