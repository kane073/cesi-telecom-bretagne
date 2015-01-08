<%@page import="eu.telecom_bretagne.CESI.data.model.Agent"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Departement"%>
<%@page import="java.util.List"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionAgent"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionDepartement"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	InitialContext ctx = new InitialContext();
	IGestionAgent gestionAgent = (IGestionAgent) ctx.lookup(IGestionAgent.JNDI_NAME);
	List<Agent> agents = gestionAgent.listeAgents();
	
	IGestionDepartement gestionDepartement =(IGestionDepartement) ctx.lookup(IGestionDepartement.JNDI_NAME);
	List<Departement> departements = gestionDepartement.listeDepartements();
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CESI Home Page</title>
</head>
<body>
	<h1>Hello CESI</h1>
	<a href="creer_agent_form.jsp">Cr&eacute;er un agent</a>
	<h2>Liste des agents:</h2>
	<form method="post" action="supprimer_agents">

	<table id="agents">
		<tr>
			<th>Identifiant</th>
			<th>Nom</th>
			<th>Département</th>
			<th></th>
		</tr>
		<%
			for (Agent agent : agents) {
		%>
		<tr>
			<td>
			<input type="checkbox" name="liste_agents" value="<%=agent.getId()%>">
			<%=agent.getId()%>
			</td>
			<td><a href="infos_agent.jsp?id=<%=agent.getId()%>"><%=agent.getNom()%></a>
			</td>
			<td><i><%=agent.getDepartement().getIntitule()%>(id: <%=agent.getDepartement().getId()%>)</i></td>
			<td><a href="modifier_agent_form.jsp?id=<%=agent.getId()%>">Modifier</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<input type="submit" value="Supprimer les agents sélectionnés">
	</form>
	<a href="index.jsp">Retour</a>
</body>
</body>
</html>