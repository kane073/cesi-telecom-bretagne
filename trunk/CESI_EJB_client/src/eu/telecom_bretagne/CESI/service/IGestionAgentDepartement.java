package eu.telecom_bretagne.CESI.service;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.dto.AgentDepartement;

@Remote
public interface IGestionAgentDepartement {
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionAgentDepartement!eu.telecom_bretagne.CESI.service.IGestionAgentDepartement";
	
	public AgentDepartement lireAgentDepartement(int id);
	
	public void modifierAgentDepartement(AgentDepartement agentDepartement);
}
