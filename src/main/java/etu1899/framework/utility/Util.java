package etu1899.framework.utility;

import jakarta.servlet.http.HttpServletRequest;

public class Util {
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
}
