package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Institutionrattchement;

@Remote
public interface IGestionInstitution {
	
public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionInstitution!eu.telecom_bretagne.CESI.service.IGestionInstitution";
	
	public List<Institutionrattchement> listeInstitution();
	
	public Institutionrattchement lireInstitutionrattchement(Integer id);
	
	public Institutionrattchement creerInstitution(String nom, String adresse) throws Exception;
	
}
