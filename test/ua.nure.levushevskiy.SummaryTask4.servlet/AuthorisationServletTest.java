package ua.nure.levushevskiy.SummaryTask4.servlet;

import org.junit.Before;
import org.junit.Test;
import ua.nure.levushevskiy.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AuthorisationServletTest {

    private AuthorisationServlet authorizationServlet;
    private HttpServletRequest request;
    private RequestDispatcher requestDispatcher;
    private ServletContext context;
    private HttpServletResponse response;
    private UserServiceImpl userService;

    @Before
    public void init() {
        authorizationServlet = new AuthorisationServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        context = mock(ServletContext.class);
        userService = mock(UserServiceImpl.class);
    }

    @Test
    public void doGet() throws Exception {
        authorizationServlet.init(mock(ServletConfig.class));
        when(context.getAttribute(EntityConstants.USER_SERVICE)).thenReturn(userService);
        when(authorizationServlet.getServletContext()).thenReturn(context);
        when(request.getRequestDispatcher(View.AUTHORIZATION_JSP)).thenReturn(requestDispatcher);
        authorizationServlet.doGet(request, response);
        verify(requestDispatcher, atLeastOnce()).forward(request, response);
    }

}