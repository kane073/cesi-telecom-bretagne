package eu.telecom_bretagne.CESI.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.service.IGestionAgent;
import eu.telecom_bretagne.CESI.service.IGestionPublication;

/**
 * Servlet implementation class SuppressionPublication
 */
@WebServlet("/suppression_publication")
public class SuppressionPublication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuppressionPublication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("actionB");
		String message = "";
		if (action.equals("supprimer")) {
			String[] ids = request.getParameterValues("liste_publication");
			String messageErreur = "";
			if (ids == null) {
				response.sendRedirect("index.jsp");
				return;
			}
			try {
				InitialContext ctx = new InitialContext();
				IGestionPublication gestionPublication = (IGestionPublication) ctx
						.lookup(IGestionPublication.JNDI_NAME);
				try {
					for (String id : ids) {
						gestionPublication.supprimerPublication(Integer
								.parseInt(id));
					}
				} catch (Exception e) {
					messageErreur = e.getMessage();
				}
			} catch (NamingException e) {
				throw new ServletException(e);
			}
			response.sendRedirect("index.jsp");
		}
		if (action.equals("reference")) {
			String[] ids = request.getParameterValues("liste_publication");
			
			if (ids == null) {
				response.sendRedirect("index.jsp");
				return;
			}
			if(ids.length>1){
				message = "Selectionnez une seule publication pour le référencement";
				request.setAttribute("messageErreur", message);
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}
			boolean test = true;
			Publication publication=new Publication();
			try {
				InitialContext ctx = new InitialContext();
				IGestionPublication gestionPublication = (IGestionPublication) ctx
						.lookup(IGestionPublication.JNDI_NAME);
				
				try {
					publication = gestionPublication.lirePublication(Integer.parseInt(ids[0]));
				} catch (Exception e) {
					test = false;
					message = e.getMessage();
				}
			} catch (NamingException e) {
				throw new ServletException(e);
			}
			if(test){
				request.setAttribute("id_publication",ids[0]);
				request.setAttribute("publication",publication);
				request.getRequestDispatcher("creer_reference.jsp").forward(request,
						response);
			}else{
				request.setAttribute("messageErreur", message);
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}
		}
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
