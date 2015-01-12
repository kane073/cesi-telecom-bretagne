package eu.telecom_bretagne.CESI.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.service.IGestionPublication;
import eu.telecom_bretagne.CESI.utils.HelperGuiCesi;

/**
 * Servlet implementation class RecherchePublication
 */
@WebServlet("/recherche_publication")
public class RecherchePublication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecherchePublication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String messageErreur = "Aucun résultat n'a été trouvé pour !";
		boolean attibutOk = true;

		String texteSearch = request.getParameter("textRecherche");
		String type = request.getParameter("typeRecherche");
		fonctionRedistecte(HelperGuiCesi.valideChamp(texteSearch, 3), request,response, messageErreur);
		
		List<Publication> publications = new ArrayList<Publication>();
		try {

			InitialContext ctx = new InitialContext();
			IGestionPublication gestionPublication = (IGestionPublication) ctx
					.lookup(IGestionPublication.JNDI_NAME);
			messageErreur = "";
			attibutOk = true;
			try {
				if (type.equals("Auteur")) {
					publications = gestionPublication.recherchePublicationParAuteur(texteSearch);
				}
				if (type.equals("Titre")) {
					publications = gestionPublication.recherchePublicationParTitre(texteSearch);
				}
			} catch (Exception e) {
				attibutOk = false;
				messageErreur = e.getMessage();
			}

		} catch (NamingException e) {
			throw new ServletException(e);
		}
		//fonctionRedistecte(attibutOk, request, response, messageErreur);
		
		request.setAttribute("messageErreur", messageErreur);
		request.setAttribute("textSearch", texteSearch);
		request.setAttribute("resultat", publications);
		request.getRequestDispatcher("resultat_recherche.jsp").forward(request,
				response);

	}

	public void fonctionRedistecte(boolean test, HttpServletRequest request,
			HttpServletResponse response, String message)
			throws ServletException, IOException {
		if (!test) {
			request.setAttribute("messageErreur", message);
			request.getRequestDispatcher("resultat_recherche.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
