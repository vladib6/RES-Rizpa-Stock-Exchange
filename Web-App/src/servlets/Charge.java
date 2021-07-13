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
@WebServlet(name = "charge", urlPatterns = {"/api/charge"})
public class Charge extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
       if(engineInterface.ChargeMoney(Integer.parseInt(req.getParameter("amount")),req.getParameter(Constants.URL_USER_PARAM))) {
           resp.getWriter().print("Done successfully");
       }else{
           resp.setStatus(500);
           resp.getWriter().print("Error in charging,user doesn't exist");
       }
    }
}

