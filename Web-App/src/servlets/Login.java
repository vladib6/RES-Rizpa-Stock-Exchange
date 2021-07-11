package servlets;

import Utils.Constants;
import Utils.EngineManager;
import Utils.SessionUtil;
import com.Engine.EngineInterface;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


    @WebServlet(name = "login", urlPatterns = {"/api/login"})
    public class Login extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            boolean isConnected;
            resp.setContentType("application/json");

            EngineInterface engineInterface = EngineManager.getEngine(getServletContext());
            if (engineInterface.addUser(req.getParameter(Constants.URL_USER_PARAM), req.getParameter("type"))) {
                SessionUtil.setUsername(req);
                isConnected = true;
            } else {
                isConnected = false;
            }
            resp.getWriter().print(isConnected);
        }

    }

