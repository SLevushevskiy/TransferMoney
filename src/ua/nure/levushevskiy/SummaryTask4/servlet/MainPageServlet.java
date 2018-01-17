package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.nure.levushevskiy.SummaryTask4.util.View.MAIN_JSP;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
    @Override
    protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute(EntityConstants.USER_PARAM)== null){
            resp.sendRedirect(View.Mapping.AUTHORIZATION);
            return;
        }

        req.getRequestDispatcher(MAIN_JSP).forward(req, resp);
    }
}
