package servlets;

import Utils.Constants;
import Utils.EngineManager;
import Utils.Gsonparser;
import com.Engine.EngineInterface;
import com.UserDTO;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userdata", urlPatterns = {"/api/user"})
public class UserData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        UserDTO userDTO=engineInterface.getUserDto(req.getParameter(Constants.URL_USER_PARAM));
        Gson gson= Gsonparser.getParser(req.getServletContext());
        resp.getWriter().print(gson.toJson(userDTO));
    }
}
