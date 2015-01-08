package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.InstitutionDAO;
import eu.telecom_bretagne.CESI.data.model.Institutionrattchement;
import eu.telecom_bretagne.CESI.exception.ExceptionCesi;

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
	public Institutionrattchement creerInstitution(String nom, String adresse) throws Exception {
		Institutionrattchement institutionrattchement = new Institutionrattchement();
		
		if(institutionDAO.findByName(nom) == null){;
		
			institutionrattchement.setNominstitution(nom);
			institutionrattchement.setAdresse(adresse);
					
			institutionDAO.create(institutionrattchement);
		}else{
			throw new ExceptionCesi("Cet institution de ratachement existe déjà!");
		}
		return institutionrattchement;
	}

}
