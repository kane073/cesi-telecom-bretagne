package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.InstitutionDAO;
import eu.telecom_bretagne.CESI.data.model.Institutionrattchement;
import eu.telecom_bretagne.CESI.data.util.HelperCesi;
import eu.telecom_bretagne.CESI.exception.MessageExceptionCesi;

/**
 * Session Bean implementation class GestionInstitution
 */
@Stateless
@LocalBean
public class GestionInstitution implements IGestionInstitution {
	
	@EJB
	InstitutionDAO institutionDAO;
    /**
     * Default constructor. 
     */
    public GestionInstitution() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Institutionrattchement> listeInstitution() {
		return institutionDAO.findAll();
	}

	@Override
	public Institutionrattchement creerInstitution(String nom, String adresse) throws Exception{
			
		if(HelperCesi.listIsEmpty(institutionDAO.findByName(nom))){;
			throw new Exception(MessageExceptionCesi.ExceptionInstitution.getMessage());
		}
		Institutionrattchement institutionrattchement = new Institutionrattchement();
		institutionrattchement.setNominstitution(nom);
		institutionrattchement.setAdresse(adresse);
				
		institutionDAO.create(institutionrattchement);
		
		return institutionrattchement;
	}

	@Override
	public Institutionrattchement lireInstitutionrattchement(Integer identifiant) {
		return institutionDAO.findById(identifiant);
	}

}
