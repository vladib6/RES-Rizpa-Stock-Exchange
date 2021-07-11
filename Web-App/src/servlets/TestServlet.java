package servlets;


import Utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logged", urlPatterns = {"/api/logged"})
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        boolean res;
        System.out.println("logged check : id:"+ req.getSession().getId());
        if(req.getSession(true).getAttribute(Constants.CONNECTED_USERNAME)==null){
            res=false;
            resp.getWriter().print(res);
            System.out.println("null");
        }else{
            System.out.println("not null");
            res =true;
            resp.getWriter().println(res);
            resp.getWriter().println(req.getAttribute(Constants.CONNECTED_USERNAME));

        }



    }
}
