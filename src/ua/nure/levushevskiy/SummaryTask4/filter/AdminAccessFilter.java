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

import static ua.nure.levushevskiy.SummaryTask4.util.View.ACCOUNT_LIST_JSP;

/**
 * A filter that restricts ordinary users access to admin pages.
 */
@WebFilter(filterName = "AdminAccessFilter",
        urlPatterns = {"/ ","/adminAccountList","/adminRequestAccount"})
public class AdminAccessFilter implements Filter {

    /**
     * The Logger object for logging events of filter class.
     */
    private static final Logger LOG = Logger.getLogger(AdminAccessFilter.class);

    @Override
    public final void init(final FilterConfig filterConfig) throws ServletException {
        LOG.debug("AdminAccessFilter started.");
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
            if (userDTO.getUserRoleDTO().getRank().equals("admin")) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        resp.sendRedirect(View.Mapping.ACCOUNT_LIST);
    }

    @Override
    public final void destroy() {
        LOG.debug("AdminAccessFilter finished.");
    }
}
