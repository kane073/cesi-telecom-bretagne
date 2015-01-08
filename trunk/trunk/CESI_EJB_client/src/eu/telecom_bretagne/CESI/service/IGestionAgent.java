package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Agent;

@Remote
public interface IGestionAgent {
	
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionAgent!eu.telecom_bretagne.CESI.service.IGestionAgent";
	
	public List<Agent> listeAgents();
	
	public Agent lireAgent(int identifiant);
	
	public void modifierAgent(int identifiant, String nom);

	void modifierAgent(int identifiant, String nouveauNom, int departement_id);
	
	public Agent creerAgent(String nomAgent, int idDepartement);
	
	public void supprimerAgent(int identifiant);
}
