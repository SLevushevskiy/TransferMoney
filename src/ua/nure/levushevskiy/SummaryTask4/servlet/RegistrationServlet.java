package ua.nure.levushevskiy.SummaryTask4.servlet;

import ua.nure.levushevskiy.SummaryTask4.dto.UserDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;
import ua.nure.levushevskiy.SummaryTask4.util.View;
import ua.nure.levushevskiy.SummaryTask4.validation.MainValidator;
import ua.nure.levushevskiy.SummaryTask4.validation.PasswordValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import static ua.nure.levushevskiy.SummaryTask4.util.View.REGISTRATION_JSP;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{

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
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
	}

	@Override
	protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();//создаем сессию
		session.removeAttribute(EntityConstants.ERROR_CONTAINER_PARAM);
		session.removeAttribute(EntityConstants.OPERATION_SUCCESSFUL);
		req.setCharacterEncoding("UTF-8");
		UserDTO userDTO = getUserFromRequest(req);
		Map<String, String> errorContainer = new HashMap<>();
		errorContainer = MainValidator.validate(userDTO, errorContainer);
		errorContainer = PasswordValidator.validatePasswordAndConfirm(errorContainer, req.getParameter("password"), req.getParameter("confirm"));
		if (!errorContainer.isEmpty()) {
			session.setAttribute(EntityConstants.ERROR_CONTAINER_PARAM, errorContainer);
			session.setAttribute(EntityConstants.INVALID_USER_PARAM, userDTO);
			resp.sendRedirect(View.Mapping.REGISTRATION + "#zatemnenie");
			return;
		} else {
			try {
				userDTO = userService.saveUser(userDTO);
			}
			catch (Exception e) {
				errorContainer.put(EntityConstants.ERROR_PARAM, e.getMessage());
				session.setAttribute(EntityConstants.INVALID_USER_PARAM, userDTO);
				session.setAttribute(EntityConstants.ERROR_CONTAINER_PARAM, errorContainer);
				resp.sendRedirect(View.Mapping.REGISTRATION + "#zatemnenie");
				return;
			}
		}
		resp.sendRedirect(View.Mapping.AUTHORIZATION);
	}

	/**
	 * A method that receives information from request.
	 *
	 * @param req - request.
	 * @return - formed object with the received data.
	 */
	private UserDTO getUserFromRequest(final HttpServletRequest req) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(req.getParameter(EntityConstants.NAME_PARAM));
		userDTO.setSurname(req.getParameter(EntityConstants.SURNAME_PARM));
		userDTO.setEmail(req.getParameter(EntityConstants.EMAIL_PARAM));
		userDTO.setPassword(req.getParameter(EntityConstants.PASSWORD_PARAM));
		return userDTO;
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
