<%@page import="eu.telecom_bretagne.CESI.data.model.Publication"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Auteur"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Institutionrattchement"%>
<%@page import="eu.telecom_bretagne.CESI.data.model.Reference"%>
<%@page import="java.util.List"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionPublication"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionAuteur"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionInstitution"%>
<%@page import="eu.telecom_bretagne.CESI.service.IGestionReference"%>
<%@page import="eu.telecom_bretagne.CESI.utils.HelperGuiCesi"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	InitialContext ctx = new InitialContext();
	IGestionPublication gestionPublication = (IGestionPublication) ctx
			.lookup(IGestionPublication.JNDI_NAME);
	List<Publication> publications = gestionPublication
			.listPublication();
	List<String> listTypeRecherche = gestionPublication.typeRecherche();

	IGestionAuteur gestionAuteur = (IGestionAuteur) ctx
			.lookup(IGestionAuteur.JNDI_NAME);
	List<Auteur> auteurs = gestionAuteur.listeAuteur();
	
	IGestionInstitution gestionInstitution = (IGestionInstitution) ctx
			.lookup(IGestionInstitution.JNDI_NAME);
	List<Institutionrattchement> institutionrattchements = gestionInstitution.listeInstitution();
	
	IGestionReference gestionReference = (IGestionReference) ctx.lookup(IGestionReference.JNDI_NAME);
	List<Reference> references = gestionReference.listReference();
%>

<!DOCTYPE html>
<html lang="en">

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
					<a href="#">Liste des publications</a>
				</h2>

				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Titre</th>
								<th>Type de publication</th>
								<th>Date de publication</th>
								<th>Date de fin publication</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (Publication p : publications) {
							%>
							<tr>
								<td><input type="checkbox" name="liste_publication"
									value="<%=p.getId()%>"></td>
								<td><%=p.getTitre()%></td>
								<td><%=p.getType()%></td>
								<td><span class="glyphicon glyphicon-time"></span> <%=HelperGuiCesi.afficheHeureFormat("dd/MM/yyyy",
						p.getDatedebutpublication())%></td>
								<td><span class="glyphicon glyphicon-time"></span> <%=HelperGuiCesi.afficheHeureFormat("dd/MM/yyyy",
						p.getDatefinpublication())%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				<!-- First Blog Post -->
				<h2>
					<a href="#">Liste des référencement</a>
				</h2>

				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Code</th>
								<th>Publication</th>
								<th>Institution</th>
								<th>Date de debut de référencementt</th>
								<th>Date de fin de référencementt</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (Reference r : references) {
							%>
							<tr>
								<td><input type="checkbox" name="liste_reference"
									value="<%=r.getIdReference()%>"></td>
								<td><%=r.getCodereference()%></td>
								<td><%=r.getPublication().getTitre()%></td>
								<td><%=r.getInstitutionrattachement()%></td>
								<td><span class="glyphicon glyphicon-time"></span> <%=HelperGuiCesi.afficheHeureFormat("dd/MM/yyyy",
						r.getDatedebutreference())%></td>
								<td><span class="glyphicon glyphicon-time"></span> <%=HelperGuiCesi.afficheHeureFormat("dd/MM/yyyy",
						r.getDatefinreference())%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				
				<!-- First Blog Post -->
				<h2>
					<a href="#">Liste des auteurs</a>
				</h2>

				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Nom</th>
								<th>Prenom</th>
								<th>Email</th>
								<th>Situation</th>
								<th>Institution de rattachement</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (Auteur a : auteurs) {
							%>
							<tr>
								<td><input type="checkbox" name="liste_auteur"
									value="<%=a.getId()%>"></td>
								<td><%=a.getNom()%></td>
								<td><%=a.getPrenom()%></td>
								<td><%=a.getEmail()%></td>
								<td><%=a.getType()%></td>
								<td><%=a.getInstitutionrattchement().getNominstitution()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				
				<!-- First Blog Post -->
				<h2>
					<a href="#">Liste des institutions</a>
				</h2>

				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Nom</th>
								<th>Adresse</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (Institutionrattchement i : institutionrattchements) {
							%>
							<tr>
								<td><input type="checkbox" name="liste_institution"
									value="<%=i.getIdInstitution()%>"></td>
								<td><%=i.getNominstitution()%></td>
								<td><%=i.getAdresse()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>

			<!-- Blog Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Blog Search Well -->
				<form name="form_recherche" method="get"
					action="recherche_publication">
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
								<li><a type="button" class="btn btn-outline btn-warning">
										Créer une référence </a></li>
								<li><a type="button" class="btn btn-outline btn-info"
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