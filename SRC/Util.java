package etu1899.framework.utility;

import etu1899.framework.annotations.DBMethod;
import etu1899.framework.annotations.DBModel;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Util {

    //  ABOUT LINK
    public static String getLink(HttpServletRequest request){
        return removeSRC(request.getRequestURI());
    }
    private static String removeSRC(String link){
        String[] strings = link.split("/");

        String result = "";
        for (int i = 2; i < strings.length; i++) {
            result += strings[i];
            result += "/";
        }
        return result;
    }
    ////////
    public static ArrayList<Class> getClassesWithAnnotation(ArrayList<File> fileList,String annotationName)throws Exception{
        ArrayList<Class> result = new ArrayList<Class>();
        ArrayList<Class> classes = retriveClassOf(fileList);
        for (Class cl :

                classes) {
            if(cl.getAnnotation(Class.forName("etu1899.framework.annotations."+annotationName)) != null) result.add(cl);
        }
        return result;
    }
    public static ArrayList<Class> getClasses(ArrayList<File> fileList)throws Exception{
        return retriveClassOf(fileList);

    }
    public static ArrayList<Field> getAnnotedFields(Class classe)throws Exception{
        Field[] fields = classe.getDeclaredFields();

        ArrayList<Field> result = new ArrayList<Field>();
        for (Field field :
                fields) {
            if (field.getAnnotations().length != 0) result.add(field);
        }
        return result;
    }
    public static ArrayList<Field> getAnnotedFieldsWith(Class objectClass,Class annotationClass)throws Exception{
        Field[] fields = objectClass.getDeclaredFields();

        ArrayList<Field> result = new ArrayList<Field>();
        for (Field field :
                fields) {
            if (objectClass.getAnnotation(annotationClass) != null) result.add(field);
        }
        return result;
    }

    // get class list from package name
    public static ArrayList<Class> getClasses(String packageName)throws  Exception{
        File[] files = getFilesOf(packageName);

        ArrayList<Class> classes = new ArrayList<Class>();
        for (File file :
                files) {
            classes.add(Class.forName(packageName+"."+file.getName().split("\\.")[0]));
        }

        return classes;
    }

    private static DBModel getModelAnnotation(Object o){
        return o.getClass().getAnnotation(DBModel.class);
    }
    private static File[] getFilesOf(String fileName)throws Exception{
        File f = new File("./src/main/java/"+fileName);


        for (File foo : f.listFiles()){
            System.out.println("list files : "+foo.getPath());
        }
        if (!f.exists() || !f.isDirectory()) throw new Exception("undefined directory in entries (Util.java:79)");

        return f.listFiles();
    }
    private static boolean isModel(Object o) {

        if (getModelAnnotation(o) == null) return false;

        return true;
    }
    //get a specified class in packageName
    public Class getClassFrom(String className,String pakageName) throws Exception{
        ArrayList<Class> classes = getClasses(pakageName);

        for (Class cl :
                classes) {
            if (cl.getName().equalsIgnoreCase(className)) return cl;
        }
        throw new Exception("undefined class '"+className+"' (Util.java:97)");
    }

    public static ArrayList getFieldsValues(ArrayList<Field> list,Object o) throws Exception{
        ArrayList result = new ArrayList();

        for (Field f :
                list) {
            f.setAccessible(true);
            result.add(f.get(o));
        }
        return result;
    }
    public static ArrayList<Method> getAnnotedMethod(Class clss)throws Exception{
        Method[] methods = clss.getDeclaredMethods();

        ArrayList<Method> result = new ArrayList<Method>();

        for (Method method : methods){
            if(method.getAnnotations().length != 0) result.add(method);
        }
        return result;
    }
    public static void getFilesIn(File source,ArrayList<File> container)throws Exception{
        for(File file : source.listFiles()){
            if (file.isDirectory()) getFilesIn(file,container);
            else container.add(file);
        }
    }
    public static ArrayList<File> getClassesIn(ArrayList<File> lists)throws Exception{
        ArrayList<File> result = new ArrayList<File>();
        for(File file : lists){
            try {
                if (file.getName().split("\\.")[1].equals("class")) result.add(file);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return result;
    }
        private static ArrayList<Class> retriveClassOf(ArrayList<File> files)throws Exception{
            ArrayList<Class> result  = new ArrayList<Class>();
            for (File f : files){
                result.add(Class.forName(getPackageAndName(f)));
            }
            return result;
        }
    public static String getPackageAndName(File file)throws Exception{
        String[] paths = file.getPath().split("/");

        StringBuilder builder = new StringBuilder();

        boolean isPackageStarted = false;
        for (String p : paths){
            if (isPackageStarted) builder.append(p+".");

            if (p.equals("classes")) isPackageStarted = true;
        }
        builder.delete(builder.length()-7,builder.length());

        return builder.toString();
    }
//    public  static ArrayList<String> getAnnotationsValues(ArrayList<Field> annotedFields)throws  Exception{
//        ArrayList<String> result = new ArrayList<String>();
//
//        AnnotationField an = null;
//        for (Field f :
//                annotedFields) {
//            an = f.getAnnotation(AnnotationField.class);
//
//            result.add(an.value());
//        }
//        return result;
//    }

}
