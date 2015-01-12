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
import eu.telecom_bretagne.CESI.service.IGestionReference;
import eu.telecom_bretagne.CESI.utils.HelperGuiCesi;

/**
 * Servlet implementation class CreationReference
 */
@WebServlet("/creation_reference")
public class CreationReference extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationReference() {
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

		String code = request.getParameter("code");
		String id = request.getParameter("id_publication");
		fonctionRedistecte(HelperGuiCesi.valideChamp(code, 3), request,
				response, messageErreur);
		
		try {

			InitialContext ctx = new InitialContext();
			IGestionReference gestionReference = (IGestionReference) ctx
					.lookup(IGestionReference.JNDI_NAME);
			messageErreur = "";
			attibutOk = true;

			try {
				gestionReference.creerReference(code, Integer.parseInt(id));	
			} catch (Exception e) {
				attibutOk = false;
				messageErreur = e.getMessage();
			}

		} catch (NamingException e) {
			throw new ServletException(e);
		}
			if(attibutOk){
				response.sendRedirect("index.jsp");
			}else{
				fonctionRedistecte(attibutOk, request, response, messageErreur);
			}
	}
	
	public void fonctionRedistecte(boolean test, HttpServletRequest request,
			HttpServletResponse response, String message)
			throws ServletException, IOException {
		if (!test) {
			request.setAttribute("messageErreur", message);
			request.getRequestDispatcher("index.jsp").forward(request,
					response);
		}
	}
}
