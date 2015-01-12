package eu.telecom_bretagne.CESI.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.CESI.service.IGestionPublication;
import eu.telecom_bretagne.CESI.utils.HelperGuiCesi;

/**
 * Servlet implementation class CreationPublication
 */
@WebServlet("/creation_publication")
public class CreationPublication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationPublication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String messageErreur = "Vérifiez que tous les champs sont corrects!";
		boolean attibutOk = true;
		
		String titre = request.getParameter("titre");
		String resume = request.getParameter("resume");
		String typePublication = request.getParameter("typePublication");
		String themeornom = request.getParameter("themeornom");
		String langue = request.getParameter("langue");
		String[] auteurs = (String[])request.getParameterValues("auteurs");
		
		fonctionRedistecte(HelperGuiCesi.valideChamp(titre, 5), request, response, messageErreur);
		fonctionRedistecte(HelperGuiCesi.valideChamp(resume, 10), request, response, messageErreur);
		fonctionRedistecte(HelperGuiCesi.valideChamp(themeornom, 5), request, response, messageErreur);
		fonctionRedistecte(HelperGuiCesi.valideChamp(titre, 5), request, response, messageErreur);
		
		if(auteurs!=null && auteurs.length>1){
			attibutOk = true;
		}else{
			attibutOk = false;
		}
		fonctionRedistecte(attibutOk, request, response, messageErreur);
		List<Integer> idAuteur = new ArrayList<Integer>();
		for (int i = 0; i < auteurs.length; i++) {
			idAuteur.add(Integer.parseInt(auteurs[i]));
		}
		try {
						
			InitialContext ctx = new InitialContext();
			IGestionPublication gestionPublication = (IGestionPublication) ctx.lookup(IGestionPublication.JNDI_NAME);
			messageErreur="";
			attibutOk = true;
			try {
				if(typePublication.equals("journal")){
					gestionPublication.creerJournal(titre, new Date(), 
							resume, langue, themeornom, null, null, null, 
							idAuteur);
				}
				if(typePublication.equals("conference")){
					gestionPublication.creerConference(titre, new Date(), resume, langue, 
							themeornom, null, null, null, null, idAuteur);
				}
			} catch (Exception e) {
				attibutOk = false;
				messageErreur = e.getMessage();
			}
			
		} catch (NamingException e) {
			throw new ServletException(e);			
		}
		fonctionRedistecte(attibutOk, request, response, messageErreur);
		response.sendRedirect("index.jsp");
	}
	
	public void fonctionRedistecte(boolean test, HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
		if(!test){
			request.setAttribute("messageErreur", message);
			request.getRequestDispatcher("creer_publication.jsp").forward(request, response);	
		}
	}
}
