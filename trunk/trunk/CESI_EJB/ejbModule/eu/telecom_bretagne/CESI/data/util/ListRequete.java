package eu.telecom_bretagne.CESI.data.util;

public class ListRequete {
	
	public static String findInstitutionByNom = "select institution from Institutionrattchement institution"
										+ "where institution.nominstitution = :";
}
