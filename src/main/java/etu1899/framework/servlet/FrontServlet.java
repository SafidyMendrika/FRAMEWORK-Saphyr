package etu1899.framework.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import etu1899.framework.utility.Util;

import java.io.IOException;

@WebServlet(name = "FrontServlet", value = "/")
public class FrontServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response){
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
}
