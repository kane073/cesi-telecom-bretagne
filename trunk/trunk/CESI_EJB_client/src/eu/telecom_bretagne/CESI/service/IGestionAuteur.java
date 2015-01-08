package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Auteur;
import eu.telecom_bretagne.CESI.data.model.Auteurexterne;
import eu.telecom_bretagne.CESI.data.model.Auteurinterne;

@Remote
public interface IGestionAuteur {
	
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionAuteur!eu.telecom_bretagne.CESI.service.IGestionAuteur";
	
	public List<Auteur> listeAuteur();

	public Auteur creerAuteur(String nom, String type, String prenom, String email,
			String sitePerso, String idExterne, String idInterne,
			int idInstitution);
	

	public Auteur creerAuteurExterne(String nom, String prenom, String email,
			String sitePerso, String idExterne, int identifiantInstitut);

	public Auteur creerAuteurInterne(String nom, String prenom, String email,
			String sitePerso, String idInterne);
}
