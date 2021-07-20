package Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute(Constants.CONNECTED_USERNAME) : null;
        return sessionAttribute != null ? sessionAttribute.toString() : null;
    }

    public  static void setUsername(HttpServletRequest request){
        HttpSession session= request.getSession(true);
        session.setAttribute(Constants.CONNECTED_USERNAME, request.getParameter(Constants.URL_USER_PARAM));
        session.setAttribute(Constants.USER_TYPE, request.getParameter(Constants.USER_TYPE));
        session.setAttribute("logged", true);
    }


    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
