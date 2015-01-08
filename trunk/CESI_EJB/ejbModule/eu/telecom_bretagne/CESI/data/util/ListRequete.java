package eu.telecom_bretagne.CESI.data.util;

public class ListRequete {
	
	public static String findInstitutionByNom = "SELECT institution FROM Institutionrattchement institution"
										+ " WHERE institution.nominstitution = :";
}
