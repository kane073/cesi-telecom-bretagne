package eu.telecom_bretagne.CESI.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.CESI.service.IGestionAuteur;
import eu.telecom_bretagne.CESI.utils.HelperGuiCesi;

/**
 * Servlet implementation class CreationAuteur
 */
@WebServlet("/creation_auteur")
public class CreationAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationAuteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String messageErreur = "Vérifiez que tous les champs sont corrects!";
		boolean attibutOk = true;

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String typeAuteur = request.getParameter("typeAuteur");
		String idAuteur = request.getParameter("idauteur");
		String institution = request.getParameter("institution");

		fonctionRedistecte(HelperGuiCesi.valideChamp(nom, 3), request,
				response, messageErreur);
		fonctionRedistecte(HelperGuiCesi.valideChamp(prenom, 3), request,
				response, messageErreur);
		fonctionRedistecte(HelperGuiCesi.valideChamp(email, 3), request,
				response, messageErreur);
		fonctionRedistecte(HelperGuiCesi.isValidEmailAddress(email), request,
				response, messageErreur);
		fonctionRedistecte(HelperGuiCesi.valideChamp(idAuteur, 3), request,
				response, messageErreur);

		try {

			InitialContext ctx = new InitialContext();
			IGestionAuteur gestionAuteur = (IGestionAuteur) ctx
					.lookup(IGestionAuteur.JNDI_NAME);
			messageErreur = "";
			attibutOk = true;

			try {
				if (typeAuteur.equals("auteurinterne")) {
					gestionAuteur.creerAuteurInterne(nom, prenom, email, null,
							idAuteur, Integer.parseInt(institution));
				}
				if (typeAuteur.equals("auteurexterne")) {
					gestionAuteur.creerAuteurExterne(nom, prenom, email, null,
							idAuteur, Integer.parseInt(institution));
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

	public void fonctionRedistecte(boolean test, HttpServletRequest request,
			HttpServletResponse response, String message)
			throws ServletException, IOException {
		if (!test) {
			request.setAttribute("messageErreur", message);
			request.getRequestDispatcher("creer_auteur.jsp").forward(request,
					response);
		}
	}
}
