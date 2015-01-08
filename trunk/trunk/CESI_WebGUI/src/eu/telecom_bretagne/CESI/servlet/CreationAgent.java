package eu.telecom_bretagne.CESI.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.CESI.service.IGestionAgent;

/**
 * Servlet implementation class CreationAgent
 */
@WebServlet("/creer_agent")
public class CreationAgent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationAgent() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String departement_id = request.getParameter("departement_id");
		try {
			InitialContext ctx = new InitialContext();
			IGestionAgent gestionAgent = (IGestionAgent) ctx.lookup(IGestionAgent.JNDI_NAME);
			gestionAgent.creerAgent(nom, Integer.parseInt(departement_id));
		} catch (NamingException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("index.jsp");
	}

}
