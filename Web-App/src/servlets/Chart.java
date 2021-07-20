package servlets;

import Utils.Constants;
import Utils.EngineManager;
import Utils.Gsonparser;
import com.Engine.EngineInterface;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "chart", urlPatterns = {"/api/chart"})
public class Chart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        EngineInterface engineInterface =EngineManager.getEngine(req.getServletContext());
        Gson gson= Gsonparser.getParser(req.getServletContext());
        resp.getWriter().print(gson.toJson(engineInterface.getChartData(req.getParameter(Constants.URL_STOCK_PARAM))));
    }
}
