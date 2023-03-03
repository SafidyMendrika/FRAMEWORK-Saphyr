package utility;

import jakarta.servlet.http.HttpServletRequest;

public class Util {
    public static String getLink(HttpServletRequest request){
        return request.getRequestURI();
    }
}
