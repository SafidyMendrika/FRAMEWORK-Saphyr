import etu1899.framework.utility.Util;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try{
            File f = new File(".");

            System.out.println(f.getPath());
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
