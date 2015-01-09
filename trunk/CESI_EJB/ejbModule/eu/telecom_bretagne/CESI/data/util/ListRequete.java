package eu.telecom_bretagne.CESI.data.util;

public class ListRequete {
	
	public static String findInstitutionByNom = "SELECT institution FROM Institutionrattchement institution"
										+ " WHERE institution.nominstitution = :";
	
	public static String findAuteurByEmail = "SELECT auteur FROM Auteur auteur"
											+ " WHERE auteur.email = :";
	
	public static String findPublicationByTitre = "SELECT publication FROM Publication publication"
											+ " WHERE publication.titre = :";
}
