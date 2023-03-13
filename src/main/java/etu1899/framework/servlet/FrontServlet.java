package etu1899.framework.servlet;

import etu1899.framework.Mapping;
import etu1899.framework.annotations.DBMethod;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import etu1899.framework.utility.Util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "FrontServlet", value = "/")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;

    @Override
    public void init() throws ServletException {
        super.init();
        this.setMappingUrls(new HashMap<String,Mapping>());

        try{
            this.defineUrlMappings();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            String uri = Util.getLink(request);
            response.getWriter().println(uri);

        }catch (Exception e){

        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }

    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    private void defineUrlMappings()throws Exception{
        String modelsPackage = "model";

        ArrayList<Class> classList = Util.getClassesWithAnnotation(modelsPackage,"DBModel");

        ArrayList<Method> methods = null;
        DBMethod ann = null;
        for (Class clss :
                classList) {
            methods = Util.getAnnotedMethod(clss);
            for (Method method : methods){
                ann = (DBMethod) method.getAnnotation(DBMethod.class);
                String link = ann.link();
                this.getMappingUrls().put(link,new Mapping(clss.getName(),method.getName()));
            }
        }
    }
}
