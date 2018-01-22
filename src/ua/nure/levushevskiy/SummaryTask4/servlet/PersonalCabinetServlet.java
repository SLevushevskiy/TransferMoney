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

@WebServlet("/personalCabinet")
public class PersonalCabinetServlet extends HttpServlet {
    @Override
    protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MAIN_JSP).forward(req, resp);
    }
}
