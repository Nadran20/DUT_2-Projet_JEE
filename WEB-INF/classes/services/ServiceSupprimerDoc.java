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

@WebServlet("/ServiceSupp")
public class ServiceSupprimerDoc extends HttpServlet {
	
private static Mediatek m;
	
	static {
		m = Mediatek.getInstance();
	}

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    { 
        response.setContentType("text/html");

        String numDoc = request.getParameter("choix");
        int i = Integer.parseInt(numDoc);
        

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        

        try {
        	m.suppressDoc(i);

	        request.setAttribute("msg", "Le document a bien été supprimé !");
        	this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
	        
        } catch(Exception e) {
        	request.setAttribute("msg", "Impossible de supprimer le document!");
        	this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
        }
    }
}
