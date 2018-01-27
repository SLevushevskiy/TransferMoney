package ua.nure.levushevskiy.SummaryTask4.util;

/**
 * A class that contains mappings and jsp-file paths.
 */
public class View {


    public static final String AUTHORIZATION_JSP = "WEB-INF/jsp/authorisation.jsp";
    public static final String ERROR_JSP = "WEB-INF/jsp/errorPage.jsp";
    public static final String REPORT_JSP = "WEB-INF/jsp/reportPayment.jsp";

    public static final String MAIN_JSP = "WEB-INF/jsp/paymentWait.jsp";
    public static final String PAYMENT_LIST_JSP = "WEB-INF/jsp/paymentList.jsp";
    public static final String ACCOUNT_LIST_JSP = "WEB-INF/jsp/accountList.jsp";
    public static final String ACCOUNT_ADD_JSP = "WEB-INF/jsp/accountAdd.jsp";
    public static final String PAYMENT_RECHARGE_JSP = "WEB-INF/jsp/rechargePayment.jsp";
    public static final String PAYMENT_TRANSFER_JSP = "WEB-INF/jsp/transferPayment.jsp";
    public static final String PAYMENT_MOBILE_JSP = "WEB-INF/jsp/mobilePayment.jsp";
    public static final String REGISTRATION_CONFIRMATION_JSP = "WEB-INF/jsp/registrationConfirmation.jsp";
    public static final String REGISTRATION_JSP = "WEB-INF/jsp/registration.jsp";
    public static final String ADD_NEW_COLOR_AND_SIZE_JSP = "WEB-INF/jsp/addNewColorAndSizes.jsp";
    public static final String ADMIN_REQUEST_LIST_JSP = "WEB-INF/jsp/adminRequestList.jsp";
    public static final String ADMIN_USER_LIST_JSP = "WEB-INF/jsp/adminUserList.jsp";
    public static final String ADMIN_ACCOUNT_LIST_JSP = "WEB-INF/jsp/adminAccountList.jsp";
    public static final String CONFIRM_PAYMENT_JSP = "WEB-INF/jsp/confirmPayment.jsp";
    public static final String PAYMENT_WAIT_JSP = "WEB-INF/jsp/paymentWait.jsp";


    private View() { }

    /**
     * Application mappings.
     */
    public static class Mapping {

        public static final String ERROR = "/errorPage";
        public static final String AUTHORIZATION = "/authorisation";
        public static final String REGISTRATION = "/registration";
        public static final String ACCOUNT_LIST = "/accountList";
        public static final String REPORT_PAYMENT = "/reportPayment";
        public static final String ADMIN_ACCOUNT_LIST = "/adminAccountList";
        public static final String ADMIN_USER_LIST = "/userList";
        public static final String PAYMENT_MOBILE = "/mobilePayment";
        public static final String CONFIRM_PAYMENT = "/confirmPayment";
        public static final String PAYMENT_RECHARGE = "/rechargePayment";
        public static final String PAYMENT_TRANSFER = "/transferPayment";
        public static final String ADMIN_REQUEST_LIST = "/adminRequestList";
        public static final String ACCOUNT_ADD = "/accountAdd";
        public static final String PAYMENT_WAIT = "/paymentWait";

        private Mapping() { }
    }

}
