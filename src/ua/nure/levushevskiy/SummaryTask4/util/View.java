package ua.nure.levushevskiy.SummaryTask4.util;

/**
 * A class that contains mappings and jsp-file paths.
 */
public class View {

    public static final String ABOUT_JSP = "WEB-INF/jsp/about.jsp";
    public static final String LOGIN_JSP = "WEB-INF/jsp/authorisation.jsp";
    public static final String ERROR_JSP = "WEB-INF/jsp/errorPage.jsp";
    public static final String FAQ_JSP = "WEB-INF/jsp/faqs.jsp";
    public static final String HOME_JSP = "index.jsp";
    public static final String PERSONAL_AREA_JSP = "WEB-INF/jsp/personalArea.jsp";
    public static final String PRODUCT_DETAIL_JSP = "WEB-INF/jsp/productDetail.jsp";
    public static final String PRODUCTS_JSP = "WEB-INF/jsp/products.jsp";
    public static final String REGISTRATION_CONFIRMATION_JSP = "WEB-INF/jsp/registrationConfirmation.jsp";
    public static final String REGISTRATION_JSP = "WEB-INF/jsp/registration.jsp";
    public static final String SHOPPING_CART_JSP = "WEB-INF/jsp/shoppingCart.jsp";
    public static final String ADD_NEW_COLOR_AND_SIZE_JSP = "WEB-INF/jsp/addNewColorAndSizes.jsp";
    public static final String ADD_NEW_PRODUCT_JSP = "WEB-INF/jsp/addNewProduct.jsp";
    public static final String ADMIN_PRODUCTS_JSP = "WEB-INF/jsp/adminProducts.jsp";
    public static final String ADMIN_PAGE_JSP = "WEB-INF/jsp/adminPage.jsp";
    public static final String ALL_USERS_JSP = "WEB-INF/jsp/allUsers.jsp";
    public static final String EDIT_COLOR_SIZE_JSP = "WEB-INF/jsp/editColorSize.jsp";
    public static final String PENDING_ORDERS_JSP = "WEB-INF/jsp/pendingOrders.jsp";
    public static final String EDIT_PRODUCT_JSP = "WEB-INF/jsp/editProduct.jsp";

    private View() { }

    /**
     * Application mappings.
     */
    public static class Mapping {

        public static final String ADD_NEW_PRODUCT = "addNewProduct";
        public static final String ADMIN_PRODUCTS = "adminProducts";
        public static final String CART = "cart";
        public static final String AUTHORIZATION = "authorisation";
        public static final String ACCOUNT = "account";
        public static final String REGISTRATION = "registration";
        public static final String PRODUCTS = "products";

        private Mapping() { }
    }

}