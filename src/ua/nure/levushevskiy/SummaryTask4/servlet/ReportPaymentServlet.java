package ua.nure.levushevskiy.SummaryTask4.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.levushevskiy.SummaryTask4.util.View.REPORT_JSP;

@WebServlet("/reportPayment")
public class ReportPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REPORT_JSP).forward(req, resp);
    }
}
