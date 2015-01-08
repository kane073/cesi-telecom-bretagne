<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionDepartement"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Departement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	InitialContext ctx = new InitialContext();
	IGestionDepartement gestionDepartement = (IGestionDepartement) ctx.lookup(IGestionDepartement.JNDI_NAME);
	List<Departement> departements = gestionDepartement.listeDepartements();
	boolean firstDeptSelected = false;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cr&eacute;ation d'une agent</title>
</head>
<body>
	<h1>Cr&eacute;ation d'un agent</h1>
	<form method="post" action="creer_agent">
		<table>
			<tr>
				<td><b>Name:</b></td>
				<td><input type="text" name="nom" value=""></td>
			</tr>
			<tr>
				<td valign="top"><b>D&eacute;partement:</b></td>
				<td>
					<%
						for (Departement departement : departements) {
					%> <input type="radio" name="departement_id"
					value="<%=departement.getId()%>"
					<%=!firstDeptSelected ? "checked" : ""%>>
					<%
						firstDeptSelected = true;
					%> <%=departement.getIntitule()%><br /> <%
 	}
 %>
				</td>
			</tr>
		</table>
		<input type="submit">
	</form>
	<a href="index.jsp">Annuler / retour</a>
</body>
</html>