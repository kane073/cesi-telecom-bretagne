package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Reference;

@Remote
public interface IGestionReference {
	
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionReference!eu.telecom_bretagne.CESI.service.IGestionReference";
	
	public List<Reference> listReference();
	
	public Reference lireReference(int identifiant);
	
	public Reference creerReference(String code, int identifiant) throws Exception;
	
	public void modifierReference(int identifiant, String code) throws Exception;
	
	public void supprimerReference(int identifiant) throws Exception;
	
	public Reference consulterReference(String code) throws Exception; 
	
}
