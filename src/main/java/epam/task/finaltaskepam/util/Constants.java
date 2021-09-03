package epam.task.finaltaskepam.util;

/**
 * @author Aleksandr Ovcharenko
 */
public class Constants {

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/registerPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String WELCOME_PAGE = "index.jsp";
    private static final String PREVIOUS_QUERY = "previousQuery";
    private static final String SESSION_PREV_QUERY = "previousQuery";

    private static final String ERROR = "errorMessage";

    private static final String LANGUAGE = "language";
    private static final String SESSION_LANGUAGE = "language";
    private static final String RUSSIAN = "ru";
    private static final String ENGLISH = "en";

    private Constants(){}

    public static String getJspPagePath() {
        return JSP_PAGE_PATH;
    }

    public static String getErrorPage() {
        return ERROR_PAGE;
    }

    public static String getPreviousQuery() {
        return PREVIOUS_QUERY;
    }

    public static String getSessionPrevQuery() {
        return SESSION_PREV_QUERY;
    }

    public static String getWelcomePage() {
        return WELCOME_PAGE;
    }

    public static String getERROR() {
        return ERROR;
    }

    public static String getLANGUAGE() {
        return LANGUAGE;
    }

    public static String getRUSSIAN() {
        return RUSSIAN;
    }

    public static String getENGLISH() {
        return ENGLISH;
    }

    public static String getSessionLanguage() {
        return SESSION_LANGUAGE;
    }
}
