package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Document;
import mediatek2021.Mediatek;

@WebServlet("/ChoixDelete")
public class ChoixDelete extends HttpServlet {
	
	private static Mediatek m;
	
	static {
		m = Mediatek.getInstance();
	}

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    { 
    	try {
	        response.setContentType("text/html");

	        PrintWriter out = response.getWriter();
	        HttpSession session = request.getSession(true);
	        
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styleCSS/body.css\"/>");
	        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styleCSS/choix.css\"/>");
	        out.println("</head>");
	        
	        out.println("<body>");
		    out.println("<form action=\"./ServiceSupp\" method=\"post\">");
		    out.println("<div id=\"selection\">");
		    out.println("<label for=\"choix\" id=\"text-choix\">Choix du document : </label>");
		    out.println("<select name=\"choix\" id=\"choix-select\">");
		    
		    List<Document> catalogue = new ArrayList<Document>();
		    boolean b = false;
		    for(int i = 0; i < 2; i++) {
		    	catalogue = m.catalogue(i);
		    	for(Document doc :catalogue) {
			    	out.println("<option value="+doc.data()[0]+">"+doc.data()[1]+"</option>");
			    	b = true;
			    }
		    }
		    out.println("</select>");
		    out.println("</div>");
		    out.println("<div class=\"button\">");
		    out.println("<button type=\"submit\">Supprimer le document</button>");
		    out.println("</div>");
		    out.println("</form>");
	        out.println("</body>");
	        out.println("</html>");
	        if(!b) {
	        	request.setAttribute("msg", "Il n'y a aucun document à supprimer");
	        	this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
	        }
        } catch(Exception e) {
        	System.out.println(e);
        	request.setAttribute("msg", "Impossible de supprimer le document!");
        	this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
        }
    }
}
