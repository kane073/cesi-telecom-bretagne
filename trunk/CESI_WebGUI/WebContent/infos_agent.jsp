<%@page import="eu.telecom_bretagne.CESI.data.model.Agent"
	import="eu.telecom_bretagne.CESI.service.IGestionAgent"
	import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	InitialContext ctx = new InitialContext();
	IGestionAgent gestionAgent = (IGestionAgent) ctx.lookup(IGestionAgent.JNDI_NAME);
	int id = Integer.parseInt(request.getParameter("id"));
	Agent agent = gestionAgent.lireAgent(id);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Information sur agent</title>
</head>
<body>
	<h1>Information sur un agent</h1>
	<table>
		<tr>
			<td>ID</td>
			<td><%=agent.getId()%></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><%=agent.getNom()%></td>
		</tr>
	</table>
	<a href="index.jsp">Retour</a>
</body>
</html>