package servlets;

import Utils.Constants;
import Utils.EngineManager;
import com.Engine.EngineInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "stockholding", urlPatterns = {"/api/stockholding"})
public class Stockholding extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        com.stock.Stock stock=engineInterface.getStockByName(req.getParameter(Constants.URL_STOCK_PARAM));
        resp.getWriter().print(engineInterface.getStockHolding(req.getParameter(Constants.URL_USER_PARAM),stock));
    }
}
