package ua.nure.levushevskiy.SummaryTask4.listener;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.*;
import ua.nure.levushevskiy.SummaryTask4.dao.factory.MySQLDAOFactory;
import ua.nure.levushevskiy.SummaryTask4.dao.factory.api.DAOFactory;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.Account;
import ua.nure.levushevskiy.SummaryTask4.entity.AccountStatus;
import ua.nure.levushevskiy.SummaryTask4.mail.api.ConfirmationMailSender;
import ua.nure.levushevskiy.SummaryTask4.mail.impl.MailSender;
import ua.nure.levushevskiy.SummaryTask4.service.api.*;
import ua.nure.levushevskiy.SummaryTask4.service.impl.*;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * A listener initializing all the necessary constituent applications.
 */
@WebListener
public class InitApplicationListener implements ServletContextListener {

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(InitApplicationListener.class);

    @Override
    public final void contextInitialized(final ServletContextEvent servletContextEvent) {
        LOG.info("Application initialization!");
        ServletContext context = servletContextEvent.getServletContext();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("dataBase");
        ResourceBundle mailBundle = ResourceBundle.getBundle("mail");
        ConnectionPool dataSource = null;
        try {
            dataSource = new ConnectionPool(resourceBundle);
        } catch (IOException | SQLException | PropertyVetoException e) {
            throw new IllegalArgumentException("Incorrect database properties!");
        }
/*
        ConfirmationMailSender confirmationMailSender = new MailSender(mailBundle);*/
        initServices(context, dataSource);
    }

    /**
     * The method that creates all dao and services.
     *
     * @param servletContext         - servlet context.
     * @param dataSource             - connection pool.
     */
    private void initServices(final ServletContext servletContext, final ConnectionPool dataSource) {
        DAOFactory mySQLDAOFactory = new MySQLDAOFactory(dataSource);

        UserRoleDAO roleDAO = mySQLDAOFactory.getUserRoleDAO();
        UserDAO userDAO = mySQLDAOFactory.getUserDAO();
        UserStatusDAO userStatusDAO = mySQLDAOFactory.getUserStatusDAO();

        UserRoleService roleService = new UserRoleServiceImpl(roleDAO);
        UserStatusService userStatusService = new UserStatusServiceImpl(userStatusDAO);
        UserService userService = new UserServiceImpl(userDAO, roleService, userStatusService);

        AccountNameDAO accountNameDAO = mySQLDAOFactory.getAccountNameDAO();
        AccountStatusDAO accountStatusDAO = mySQLDAOFactory.getAccountStatusDAO();
        AccountDAO accountDAO = mySQLDAOFactory.getAccountDAO();

        AccountNameService accountNameService = new AccountNameServiceImpl(accountNameDAO);
        AccountStatusService accountStatusService = new AccountStatusServiceImpl(accountStatusDAO);
        AccountService accountService = new AccountServiceImpl(accountDAO,userService,accountStatusService,accountNameService);

        PaymentTypeDAO paymentTypeDAO = mySQLDAOFactory.getPaymentTypeDAO();
        PaymentNameDAO paymentNameDAO = mySQLDAOFactory.getPaymentNameDAO();
        PaymentStatusDAO paymentStatusDAO = mySQLDAOFactory.getPaymentStatusDAO();
        PaymentDAO paymentDAO = mySQLDAOFactory.getPaymentDAO();

        PaymentTypeService paymentTypeService = new PaymentTypeServiceImpl(paymentTypeDAO);
        PaymentNameService paymentNameService = new PaymentNameServiceImpl(paymentNameDAO,paymentTypeService);
        PaymentStatusService paymentStatusService = new PaymentStatusServiceImpl(paymentStatusDAO);
        PaymentService paymentService = new PaymentServiceImpl(paymentDAO,accountService,paymentStatusService,paymentNameService);

        servletContext.setAttribute(EntityConstants.ROLE_SERVICE, roleService);
        servletContext.setAttribute(EntityConstants.USER_SERVICE, userService);
        servletContext.setAttribute(EntityConstants.USER_STATUS_SERVICE, userStatusService);

        servletContext.setAttribute(EntityConstants.ACCOUNT_SERVICE, accountService);
        servletContext.setAttribute(EntityConstants.ACCOUNT_NAME_SERVICE, accountNameService);
        servletContext.setAttribute(EntityConstants.ACCOUNT_STATUS_SERVICE, accountStatusService);

        servletContext.setAttribute(EntityConstants.PAYMENT_SERVICE, paymentService);
        servletContext.setAttribute(EntityConstants.PAYMENT_TYPE_SERVICE, paymentTypeService);
        servletContext.setAttribute(EntityConstants.PAYMENT_NAME_SERVICE, paymentNameService);
        servletContext.setAttribute(EntityConstants.PAYMENT_STATUS_SERVICE, paymentStatusService);

    }

    @Override
    public final void contextDestroyed(final ServletContextEvent servletContextEvent) {
        LOG.info("End application!");
    }
}
