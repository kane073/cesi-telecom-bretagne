<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Reference"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Publication"%>
<%@page import="java.util.List"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionReference"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionPublication"%>

<%@page import="eu.telecom_bretagne.CESI.utils.HelperGuiCesi"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	InitialContext ctx = new InitialContext();
	IGestionReference gestionReference = (IGestionReference) ctx
			.lookup(IGestionReference.JNDI_NAME);
	
	IGestionPublication gestionPublication = (IGestionPublication) ctx
			.lookup(IGestionPublication.JNDI_NAME);
	List<Publication> publications = gestionPublication
			.listPublication();
	List<String> listTypeRecherche = gestionPublication.typeRecherche();
%>

<!DOCTYPE html>
<html lang="fr">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>CESI Application Biblithèque</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/blog-home.css" rel="stylesheet">
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="padding-top: 10px">
	<div class="container">

		<div class="row">

			<!-- Blog Entries Column -->
			<div class="col-md-8">

				<h1 class="page-header">
					CESI Hello <small>Application Bibliothèque</small>
				</h1>

				<!-- First Blog Post -->
				<h2>
					<a href="#">Créer une référence</a>
				</h2>
				<p class="lead">
					<a href="index.jsp">Accueil</a>
				</p>
				<p>
					<span class="glyphicon glyphicon-time"></span> Date du jour: le
					<%=HelperGuiCesi.afficheHeureFormat("dd MMMM yyyy",
					new Date())%>
				</p>
				<hr>
				<div class="col-lg-10">

					<c:if test="${not empty messageErreur}">
						<div class="alert alert-danger">${messageErreur}</div>
					</c:if>
					<form role="form" method="post" action="creation_reference">
						<input type="hidden" name="id_publication" value='<c:out value="${id_publication}"></c:out>'/> 
						<div class="form-group">
							<label>Code *</label> <input name="code" class="form-control">
						</div>
						<div class="form-group">
							<label>Titre publication *</label> 
							<input name="publicationname" value="${publication.titre}" class="form-control" disabled="disabled">
						</div>
						
						<button type="submit" class="btn btn-default">Créer</button>
						<a href="index.jsp" class="btn btn-default">Retour</a>
					</form>
				</div>
			</div>

			<!-- Blog Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Blog Search Well -->
				<form name="form_recherche" method="get"
					action="recherche_publication"">
					<div class="well">
						<h4>Rechercher une publication</h4>
						<div class="input-group">
							<input type="text" name="textRecherche" class="form-control" />
							<span class="input-group-btn"> <input>
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
						<div class="form-group">
							<label>Type de recherche</label>
							<%
								for (String typeR : listTypeRecherche) {
							%>
							<label class="radio-inline"> <input type="radio"
								name="typeRecherche" id="id_<%=typeR%>" value="<%=typeR%>"
								checked=""><%=typeR%>
							</label>
							<%
								}
							%>
						</div>
						<!-- /.input-group -->
					</div>
				</form>
				<!-- Blog Categories Well -->
				<div class="well">
					<h4>Actions</h4>
					<div class="row">
						<div class="col-lg-6">

							<ul class="list-unstyled">
								<li><a type="button" class="btn btn-outline btn-success"
									href="creer_publication.jsp"> Créer une publication </a></li>

								<li><a type="button" class="btn btn-outline btn-warning"
								href="creer_auteur.jsp">
										Créer un auteur </a></li>

								<li><a type="button" class="btn btn-outline btn-default">
										Créer une intitution </a></li>
							</ul>
						</div>
					</div>
					<!-- /.row -->
				</div>

			</div>

		</div>
		<!-- /.row -->

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; Alassane KANE 2014</p>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>