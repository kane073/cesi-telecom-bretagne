package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.CESI.data.model.Departement;

@Remote
public interface IGestionDepartement {
	
	public static final String JNDI_NAME = "java:global/CESI_EAR/CESI_EJB/GestionDepartement!eu.telecom_bretagne.CESI.service.IGestionDepartement";
	
	public List<Departement> listeDepartements();
	
	public Departement lireDepartement(int identifiant);
}
