package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.AuteurDAO;
import eu.telecom_bretagne.CESI.data.model.Auteur;

/**
 * Session Bean implementation class GestionAuteur
 */
@Stateless
@LocalBean
public class GestionAuteur implements IGestionAuteur {
	
	@EJB
	AuteurDAO auteurDAO;
	
    /**
     * Default constructor. 
     */
    public GestionAuteur() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Auteur> listeAuteur() {
		return auteurDAO.findAll();
	}

}
