package eu.telecom_bretagne.CESI.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Publication;

@Remote
public interface IGestionPublication {
	
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionPublication!eu.telecom_bretagne.CESI.service.IGestionPublication";
	
	public List<Publication> listPublication();
	
	public Publication lirePublication(int identifiant);
	
	public Publication creerPublication(
								String type, String titre, Date datePublication, String resume,
								String langue, String nomJournal, String sujet,
								Date dateApparition, String volume, String theme, String lieu,
								String isbn, String editeur, String description, 
								List<Integer> listIdentifiantAuteur
								) throws Exception;
	public Publication creerConference(String titre, Date datePublication, String resume,
								String langue, String theme, String lieu,
								String isbn, String editeur, String description, 
								List<Integer> listIdentifiantAuteur
								) throws Exception;
	
	public Publication creerJournal(String titre, Date datePublication, String resume,
			String langue, String nomJournal, String sujet,
			Date dateApparition, String volume, 
			List<Integer> listIdentifiantAuteur
			) throws Exception;
}
