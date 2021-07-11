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
        System.out.println("session id:"+session.getId());
        session.setAttribute(Constants.CONNECTED_USERNAME, request.getParameter(Constants.URL_USER_PARAM));
        System.out.println("session details"+Constants.CONNECTED_USERNAME+" "+ request.getParameter(Constants.URL_USER_PARAM));
    }


    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
