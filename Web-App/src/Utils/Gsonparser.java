package Utils;

import com.google.gson.Gson;

import javax.servlet.ServletContext;

public class Gsonparser {
    private static  final Object syncObject=new Object();

    public static Gson getParser(ServletContext sc){

        synchronized (syncObject){
             if(sc.getAttribute(Constants.GSON_PARSER)==null){
                  sc.setAttribute(Constants.GSON_PARSER,new Gson());
              }
        }
        return (Gson) sc.getAttribute(Constants.GSON_PARSER);
    }
}
