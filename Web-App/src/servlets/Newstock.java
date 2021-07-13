package servlets;

import Utils.Constants;
import Utils.EngineManager;
import com.Engine.EngineInterface;
import com.Engine.Myexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "newstock", urlPatterns = {"/api/newstock"})
public class Newstock extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8 ");
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        String company=req.getParameter("company");
        String symbol=req.getParameter("symbol");
        int amount=Integer.parseInt(req.getParameter("amount"));
        int value=Integer.parseInt(req.getParameter("value"));
        if(amount==0) {
            resp.getWriter().print("amount couldn't be zero");
        }else if(value==0) {
            resp.getWriter().print("value couldn't be zero");
        }else {
            int price=value/amount;
            String username=req.getParameter(Constants.URL_USER_PARAM);

            try {
                engineInterface.createNewStockByUser(company,symbol,price,amount,username);
                resp.getWriter().print("Done successfully");
            } catch (Myexception myexception) {
                resp.setStatus(500);
                resp.getWriter().print(myexception.toString());
            }
        }
    }
}
