package servlets;

import Utils.Constants;
import Utils.EngineManager;
import com.Command.CmdTypes.Command;
import com.Engine.EngineInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "exchange", urlPatterns = {"/api/exchange"})
public class Exchange extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        String user=req.getParameter(Constants.URL_USER_PARAM);
        String stocsymbol=req.getParameter(Constants.URL_STOCK_PARAM);
        String direction=req.getParameter("direction");
        String type=req.getParameter("type");
        int quantity=Integer.parseInt(req.getParameter("quantity"));
        int limitPrice=Integer.parseInt(req.getParameter("limit"));
        Command command= engineInterface.CreateAndExecuteCmd(user,direction,stocsymbol,type,quantity,limitPrice);
        int numOfTransactions= engineInterface.ExecuteCmd(command.getCommand());
        resp.getWriter().print(numOfTransactions);

    }
}
