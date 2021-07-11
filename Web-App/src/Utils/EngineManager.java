package Utils;

import com.Engine.EngineInterface;
import com.Engine.MainEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EngineManager {

    private static  final Object syncObject=new Object();

    public static EngineInterface getEngine(ServletContext sc){
        synchronized (syncObject){
            if(sc.getAttribute(Constants.WEB_ENGINE)==null){
                sc.setAttribute(Constants.WEB_ENGINE,new MainEngine());
            }
        }
        return (EngineInterface) sc.getAttribute(Constants.WEB_ENGINE);
    }


}
