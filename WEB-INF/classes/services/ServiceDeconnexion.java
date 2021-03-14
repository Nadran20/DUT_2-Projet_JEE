package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.*;

@WebServlet("/ServiceDeconnexion")
public class ServiceDeconnexion extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    { 
        response.setContentType("text/html");
        try {
        	request.getSession().removeAttribute("user");
	        request.setAttribute("error", "Vous êtes bien déconnecté ");
        	this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        } catch(Exception e) {
        	request.setAttribute("error", "ERROR");
        	this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }
    }
    

}