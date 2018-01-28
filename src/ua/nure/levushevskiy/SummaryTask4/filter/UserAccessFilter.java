package ua.nure.levushevskiy.SummaryTask4.filter;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.nure.levushevskiy.SummaryTask4.util.EntityConstants.ERROR_PARAM;

@WebFilter(filterName = "UserAccessFilter",
        urlPatterns = {"/accountAdd","/accountList","/confirmPayment","/mobilePayment","/paymentList","/rechargePayment",
        "/paymentWait","/reportPayment","/savePdfReport","/trasferPayment"})
public class UserAccessFilter implements Filter {

    /**
     * The Logger object for logging events of filter class.
     */
    private static final Logger LOG = Logger.getLogger(UserAccessFilter.class);

    @Override
    public final void init(final FilterConfig filterConfig) throws ServletException {
        LOG.debug("UserAccessFilter started.");
    }

    @Override
    public final void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                               final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        UserDTO userDTO = null;
           if (session.getAttribute(EntityConstants.USER_PARAM) != null) {
                userDTO = (UserDTO) session.getAttribute(EntityConstants.USER_PARAM);
                if (userDTO.getUserStatusDTO().getStatus().equals("blocked")) {
                    req.setAttribute(ERROR_PARAM, "You was blocked by admin.");
                    resp.sendRedirect(View.Mapping.ERROR);
                    return;
                }
            }
            else {
                resp.sendRedirect(View.Mapping.AUTHORIZATION);
                return;
            }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public final void destroy() {
        LOG.debug("UserAccessFilter finished.");
    }

}
