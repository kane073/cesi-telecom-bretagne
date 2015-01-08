<%@page import="eu.telecom_bretagne.CESI.data.model.Departement"%>
<%@page import="java.util.List"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionDepartement"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Agent"
	import="eu.telecom_bretagne.CESI.service.IGestionAgent"
	import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	InitialContext ctx = new InitialContext();
	IGestionAgent gestionAgent = (IGestionAgent) ctx.lookup(IGestionAgent.JNDI_NAME);
	int id = Integer.parseInt(request.getParameter("id"));
	Agent agent = gestionAgent.lireAgent(id);

	IGestionDepartement gestionDepartement = (IGestionDepartement) ctx.lookup(IGestionDepartement.JNDI_NAME);
	List<Departement> departements = gestionDepartement.listeDepartements();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modification d'un agent</title>
</head>
<body>
	<h1>Modification d'un agent</h1>
	<form method="post" action="modifier_agent">
		<table>
			<tr>
				<td><b>ID:</b></td>
				<td><%=agent.getId()%> <input type="hidden" name="id"
					value="<%=agent.getId()%>"></td>
			</tr>
			<tr>
				<td><b>Name:</b></td>
				<td><input type="text" name="nom" value="<%=agent.getNom()%>">
				</td>
			</tr>
			<tr>
				<td valign="top"><b>D&eacute;partement:</b></td>
				<td><i> <%for (Departement departement : departements) {%> 
						<input type="radio" name="departement_id"
						value="<%=departement.getId()%>"
						<%=departement.getId().equals(
						agent.getDepartement().getId()) ? "checked" : ""%>>
						<%=departement.getIntitule()%><br /> <%
					 	}
					 %>
				</i></td>
			</tr>
		</table>
		<input type="submit">
	</form>
	<a href="index.jsp">Retour</a>
</body>
</html>