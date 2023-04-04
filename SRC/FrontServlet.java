package etu1899.framework.servlet;

import etu1899.framework.Mapping;
import etu1899.framework.ModelView;
import etu1899.framework.annotations.DBMethod;
import etu1899.framework.annotations.Url;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import etu1899.framework.utility.Util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontServlet", value = "/")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;

    @Override
    public void init() throws ServletException {
        super.init();
        this.setMappingUrls(new HashMap<String,Mapping>());

        try{
            defineUrlMappings();
            System.out.println("init");
        }catch (Exception e){
            e.printStackTrace();
            
        }
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            String uri = Util.getLink(request);
            response.getWriter().println(uri);

            String firstUri = uri.split("/")[0];
            if (this.getMappingUrls().containsKey(firstUri)) {
                
                Mapping mapping = (Mapping)this.getMappingUrls().get(firstUri);
                
                System.out.println(mapping + " \n");
                Class clss = Class.forName(mapping.getClassName());
                Object mappingObject = clss.getConstructor().newInstance();
                Method mappingMethod = clss.getDeclaredMethod(mapping.getMethodName());
                
                ModelView mv = (ModelView) mappingMethod.invoke(mappingObject);

               
                for (Map.Entry entry: mv.getData().entrySet()) {
                    // System.out.println(entry.getKey()+" :: "+entry.getValue());
                    request.setAttribute(entry.getKey().toString(),entry.getValue());
                }

                RequestDispatcher dispatch = request.getRequestDispatcher(mv.getView());
                dispatch.forward(request,response);
            }

            Collection<Mapping> values = this.getMappingUrls().values();

            response.getWriter().println("Objects in the hashmap: " + values);


        }catch (Exception e){
            try {
                e.printStackTrace();
                response.getWriter().print(e.getMessage());
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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


        ArrayList<File> files = new ArrayList<File>();

        Util.getFilesIn(new File(this.getServletContext().getResource(".").getPath()),files);
        files = Util.getClassesIn(files);

        ArrayList<Class> classList = Util.getClasses(files);

        ArrayList<Method> methods = null;
        Url ann = null;
        for (Class clss :
                classList) {
            methods = Util.getAnnotedMethod(clss);
            for (Method method : methods){
                ann = (Url) method.getAnnotation(Url.class);
                if (ann != null) {
                    String link = ann.link();
                    this.getMappingUrls().put(link,new Mapping(clss.getName(),method.getName()));    
                }
            }
        }


    }
}
