package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Mediatek;

@WebServlet("/ServiceAjout")
public class ServiceAjouterDoc extends HttpServlet {
	
	private static Mediatek m;
	
	static {
		m = Mediatek.getInstance();
	}

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    { 
        response.setContentType("text/html");
        String [] descriptions = {request.getParameter("titre"),request.getParameter("auteur")};
        int type = Integer.parseInt(request.getParameter("type"));
        
        try {
        	m.newDocument(type,descriptions);

        	request.setAttribute("msg", "Le document "+ descriptions[0]+"a bien été ajouter !");
        	this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );	
        } catch(Exception e) {
        	request.setAttribute("msg", "impossible d'ajouter le document");
        	this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
        }
    }
	
}
