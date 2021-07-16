package servlets;

import Utils.Constants;
import Utils.EngineManager;
import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.Engine.StockException;
import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Collection;

@WebServlet(name = "upload", urlPatterns = {"/api/upload"})
@MultipartConfig (fileSizeThreshold = 1024*1024,maxFileSize = 1014*1024*5)
public class Upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Collection<Part> parts= req.getParts();
        EngineInterface engineInterface= EngineManager.getEngine(req.getServletContext());
        for (Part p:parts){
            try {
                engineInterface.loadDataFromXml(p.getInputStream(),req.getParameter(Constants.URL_USER_PARAM));
                resp.getWriter().print("Loaded successfully");
            } catch (Myexception | StockException | JAXBException e) {
               resp.getWriter().print(e.toString());
            }
        }



    }
}
