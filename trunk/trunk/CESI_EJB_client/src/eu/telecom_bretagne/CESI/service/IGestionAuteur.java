package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Auteur;

@Remote
public interface IGestionAuteur {
	
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionAuteur!eu.telecom_bretagne.CESI.service.IGestionAuteur";
	
	public List<Auteur> listeAuteur();
}
