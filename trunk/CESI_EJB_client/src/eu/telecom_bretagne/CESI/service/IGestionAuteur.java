package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Auteur;

@Remote
public interface IGestionAuteur {
	
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionAuteur!eu.telecom_bretagne.CESI.service.IGestionAuteur";
	
	public List<Auteur> listeAuteur();
	
	public Auteur lireAuteur(int identifiant);
	
	public Auteur creerAuteur(String nom, String type, String prenom, String email,
			String sitePerso, String idExterne, String idInterne,
			int idInstitution) throws Exception;
	

	public Auteur creerAuteurExterne(String nom, String prenom, String email,
			String sitePerso, String idExterne, int identifiantInstitut) throws Exception;

	public Auteur creerAuteurInterne(String nom, String prenom, String email,
			String sitePerso, String idInterne) throws Exception;

	public void modifierAuteur(int identifiant, String nom, String prenom, String email, String sitePerso,
			int idInstitution);

	
}
