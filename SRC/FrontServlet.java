package etu1899.framework.servlet;

import etu1899.framework.Mapping;
import etu1899.framework.ModelView;
import etu1899.framework.annotations.DBMethod;
import etu1899.framework.annotations.ParameterName;
import etu1899.framework.annotations.RestAPI;
import etu1899.framework.annotations.Scope;
import etu1899.framework.annotations.Url;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import etu1899.framework.utility.Util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Enumeration;

import com.google.gson.Gson;
import java.lang.reflect.Parameter;

//@WebServlet(name = "FrontServlet", value = "*.do")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    HashMap<Class, Object> singletonsObject;

    @Override
    public void init() throws ServletException {
        super.init();
        this.setMappingUrls(new HashMap<String,Mapping>());
        this.setMappingUrls(new HashMap<String,Mapping>());

        try{
            defineUrlMappings();
        }catch (Exception e){
            e.printStackTrace();
            
        }
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            String uri = Util.getLink(request);
            //response.getWriter().println(uri);

            String firstUri = uri.split("/")[0];
            if (this.getMappingUrls().containsKey(firstUri)) {
                
                Mapping mapping = (Mapping)this.getMappingUrls().get(firstUri);
                
                System.out.println(mapping + " \n");
                Class clss = Class.forName(mapping.getClassName());
                
                Object mappingObject = null;
                if (this.getSingletonsObject().containsKey(clss)) {
                    mappingObject = this.getSingletonsObject().get(clss);   
                }else{
                    mappingObject =  clss.getConstructor().newInstance();
                }

                Method mappingMethod = mapping.getMethod();
                
               
                //formulaire
                Field[] clssFields = clss.getDeclaredFields();
                Object fieldContent = null;
                for (Field field : clssFields) {
                    fieldContent = request.getParameter(field.getName());
                    if (fieldContent != null) {
                        fieldContent = parseType(fieldContent,field.getType());
                        
                        field.setAccessible(true);
                        field.set(mappingObject,fieldContent);
                        field.setAccessible(false);
                        System.out.println(fieldContent);
                    }
                    fieldContent = null;
                }
                //
                // getting the parameters of the method
                Parameter[] parameters = mappingMethod.getParameters();
                Object[] parametersObject = new Object[parameters.length];
                Object param = null;
                int i = 0;
                for (Parameter parameter : parameters) {

                    //System.out.println("misy parameters : " + parameter.getName());
                    param = request.getParameter(getNameByAnnotation(parameter));
                     //System.out.println("params : "+param);
                    parametersObject[i] = parseType(param, parameter.getType());
                    i++;
                }
                ModelView mv = null;

                // SessionField
                try {
                    Field f = mappingObject.getClass().getDeclaredField("session");
                    if (f != null) {
                        instanceSession(f, mappingObject, request.getSession());
                    }
                } catch (Exception e) {
                    System.out.println("erreur ao am session field");
                    e.printStackTrace();
                }
                //
                if(isAnnotedMethod(mappingMethod, RestAPI.class)){
                    Object result = null;

                    if (parametersObject.length == 0) {
                        result = (ModelView) mappingMethod.invoke(mappingObject);
                    }else{
                        result = (ModelView) mappingMethod.invoke(mappingObject,parametersObject);
                    }

                    Gson jsonizer = new Gson();

                    response.getWriter().print(jsonizer.toJson(result));
                }else{
                
                    if (parametersObject.length == 0) {
                    mv = (ModelView) mappingMethod.invoke(mappingObject);
                }else{
                    mv = (ModelView) mappingMethod.invoke(mappingObject,parametersObject);
                }

                if (mv.isJsonizable()) {
                    Gson jsonizer = new Gson();

                    response.getWriter().print(jsonizer.toJson(mv.getData()));
                    
                }else{
                    // data into a view
                    for (Map.Entry entry: mv.getData().entrySet()) {
                        // System.out.println(entry.getKey()+" :: "+entry.getValue());
                        request.setAttribute(entry.getKey().toString(),entry.getValue());
                    }
                    
                    RequestDispatcher dispatch = request.getRequestDispatcher(mv.getView());
                    dispatch.forward(request,response);
                    
                    // Collection<Mapping> values = this.getMappingUrls().values();
                    
                    // response.getWriter().println("Objects in the hashmap: " + values);
                }
            }


            }


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
                    this.getMappingUrls().put(link,new Mapping(clss.getName(),method));    
                }
            }
            checkScope(clss);

        }


    }
    private Object parseType(Object input,Class type){
        Object value = null;
        String strval = String.valueOf(input);
        if (type == int.class) {
            value = Integer.parseInt(strval);
        }else if(type == double.class){
            value = Double.valueOf(strval);
        }else if(type == String.class){
            value = strval;
        }
        return value;
    }
    private static boolean isAnnotedMethod(Method o ,Class c){
        if (o.getAnnotation(c) != null) {
                return true;
        }   
        return false;
    }
    private static String getNameByAnnotation(Parameter p ){
        String result = "";
        ParameterName pn = (ParameterName) p.getAnnotation(ParameterName.class);
        result = pn.value();
        return result;
    }
    private void instanceSession(Field f , Object o ,HttpSession session)throws Exception{
            HashMap<String,Object> sess = new HashMap<String,Object>();

            
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String key = attributeNames.nextElement();
                Object value = session.getAttribute(key);

                sess.put(key, value);
            }

            f.set(o, sess);
    }   
    private void checkScope(Class clss)throws Exception{
        Scope an = (Scope) clss.getAnnotation(Scope.class);

        if(an != null && an.value() == "singleton"){
            Object instance = clss.newInstance();
            this.getSingletonsObject().put(clss, instance);
        }
    }

    public void setSingletonsObject(HashMap<Class, Object> singletonsObject){
        this.singletonsObject = singletonsObject;
    }
    public HashMap<Class, Object>  getSingletonsObject(){
        return this.singletonsObject ;
    }
}
