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

import static ua.nure.levushevskiy.SummaryTask4.util.View.LOGIN_JSP;

@WebFilter(filterName = "UserAccessFilter",
        urlPatterns = {"/*"})
public class UserAccessFilter implements Filter {

    /**
     * The Logger object for logging events of filter class.
     */
    private static final Logger LOG = Logger.getLogger(AdminAccessFilter.class);

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

        String n =req.getRequestURI();

        if (!(req.getRequestURI().equals(View.Mapping.REGISTRATION)||req.getRequestURI().equals(View.Mapping.AUTHORIZATION))){
            if (session.getAttribute(EntityConstants.USER_PARAM) == null) {
                resp.sendRedirect(View.Mapping.AUTHORIZATION);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public final void destroy() {
        LOG.debug("UserAccessFilter finished.");
    }

}
