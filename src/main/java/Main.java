import etu1899.framework.utility.Util;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try{
            ArrayList<Class> f = Util.getClassesWithAnnotation("model","DBModel");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
