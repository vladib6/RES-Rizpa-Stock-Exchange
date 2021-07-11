package servlets;

import Utils.EngineManager;
import Utils.Gsonparser;
import com.Engine.EngineInterface;
import com.StockDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "stocks", urlPatterns = {"/api/stocks"})
public class Stocks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        Gson gson= Gsonparser.getParser(req.getServletContext());
        List<StockDTO> stockDTOList= engineInterface.getStocksInfo();
        resp.getWriter().print(gson.toJson(stockDTOList));
    }
}
