package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * The servlet responsible for localizing the application interface.
 */
@WebServlet("/localization")
public class LocalizationServlet extends HttpServlet {

    @Override
    protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String language = req.getParameter(EntityConstants.LOCAL_PARAM);
        String from = req.getParameter(EntityConstants.FROM_PARAM);
        HttpSession session = req.getSession();
        session.setAttribute(EntityConstants.LANGUAGE_PARAM, language.toLowerCase());
        resp.sendRedirect(from);
    }
}
