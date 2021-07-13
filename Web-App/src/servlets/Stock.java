package servlets;

import Utils.Constants;
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

@WebServlet(name = "stock", urlPatterns = {"/api/stock"})
public class Stock extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        String stockSymbol=req.getParameter(Constants.URL_STOCK_PARAM);
        Gson gson= Gsonparser.getParser(req.getServletContext());
        StockDTO stockDTO= engineInterface.getStockDto(stockSymbol);
        if(stockDTO!=null){
            resp.getWriter().print(gson.toJson(stockDTO));
        }else{
            resp.setStatus(500);
            resp.getWriter().print("The stock not exist");
        }
    }
}
