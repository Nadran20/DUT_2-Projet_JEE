package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.*;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;

@WebServlet("/ServiceConnexion")
public class ServiceConnexion extends HttpServlet {
	private static Mediatek m;
	
	static {
		m = Mediatek.getInstance();
	}

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    { 
        response.setContentType("text/html");

        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Utilisateur user = null;

        try {
        	user = m.getUser(login, mdp);
        	session.setAttribute("user", user);
		    
        	request.setAttribute("login", user.login());
        	this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
        } catch(Exception e) {
        	request.setAttribute("error", "Login ou mot de passe incorrect !");
        	this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }
    }

}