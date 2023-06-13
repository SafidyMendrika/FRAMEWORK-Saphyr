package etu1899.framework.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

// @WebServlet(name = "Main", value = "/")
public class Main extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws Exception{
        
        RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
        dispatch.forward(request,response);
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            processRequest(request,response);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
