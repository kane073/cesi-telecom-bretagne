package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.AuteurDAO;
import eu.telecom_bretagne.CESI.data.dao.InstitutionDAO;
import eu.telecom_bretagne.CESI.data.model.Auteur;
import eu.telecom_bretagne.CESI.data.model.Auteurexterne;
import eu.telecom_bretagne.CESI.data.model.Auteurinterne;
import eu.telecom_bretagne.CESI.data.model.Institutionrattchement;
import eu.telecom_bretagne.CESI.data.util.HelperCesi;
import eu.telecom_bretagne.CESI.data.util.TypeAuteur;
import eu.telecom_bretagne.CESI.exception.MessageExceptionCesi;

/**
 * Session Bean implementation class GestionAuteur
 */
@Stateless
@LocalBean
public class GestionAuteur implements IGestionAuteur {
	
	@EJB
	AuteurDAO auteurDAO;
	
	@EJB
	InstitutionDAO institutionDAO;
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
	
	@Override
	public Auteur lireAuteur(int identifiant){
		return auteurDAO.findById(identifiant);
	}
	@Override
	public Auteur creerAuteur(String nom, String type, String prenom, 
			String email, String sitePerso, String idExterne, 
			String idInterne, int idInstitution) throws Exception{
		if(!HelperCesi.listIsEmpty(auteurDAO.findByEmail(email))){
			throw new Exception(MessageExceptionCesi.ExceptionAuteur.getMessage());
		}
		Institutionrattchement institutionrattchement = institutionDAO.findById(idInstitution);
		
		Auteur auteur = new Auteur();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setEmail(email);
		auteur.setSiteperso(sitePerso);
		auteur.setInstitutionrattchement(institutionrattchement);
		
		if(TypeAuteur.auteurinterne.toString().equals(type)){
			Auteurinterne auteurinterne = new Auteurinterne(auteur);
			auteurinterne.setIdtelecom(idInterne);
			auteurDAO.create(auteurinterne);
			institutionDAO.update(institutionrattchement);
			return auteurinterne;
		}else{
			Auteurexterne auteurexterne = new Auteurexterne(auteur);
			auteurexterne.setIdexterne(idExterne);
			auteurDAO.create(auteurexterne);
			institutionDAO.update(institutionrattchement);
			return auteurexterne;
		}
		
	}

	@Override
	public Auteur creerAuteurExterne(String nom, String prenom, String email,
			String sitePerso, String idExterne, int identifiantInstitut) throws Exception {
		return creerAuteur(nom, TypeAuteur.auteurexterne.toString(), prenom, email, 
				sitePerso, idExterne, null, identifiantInstitut);
	}

	@Override
	public Auteur creerAuteurInterne(String nom, String prenom, String email,
			String sitePerso, String idInterne) throws Exception {
		return creerAuteur(nom, TypeAuteur.auteurinterne.toString(), prenom, email, sitePerso, 
				null, idInterne, 1);
	}
	
	@Override
	public void modifierAuteur(int identifiant, String nom, String prenom, 
			String email, String sitePerso, int idInstitution){
		
		Institutionrattchement institutionrattchement = institutionDAO.findById(idInstitution);
		Auteur auteur = lireAuteur(identifiant);
		Institutionrattchement old_institutionrattchement = auteur.getInstitutionrattchement();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setEmail(email);
		auteur.setSiteperso(sitePerso);
		auteur.setInstitutionrattchement(institutionrattchement);
		institutionrattchement.addAuteur(auteur);
		
		auteurDAO.update(auteur);
		institutionDAO.update(old_institutionrattchement);
		institutionDAO.update(institutionrattchement);
		
//		auteurDAO.enregistrerLaSession();
//		auteurDAO.detacheEntity(auteur);
//		
//		if(TypeAuteur.auteurinterne.toString().equals(type)){
//			Auteurinterne auteurinterne = new Auteurinterne(auteur);
//			auteurinterne.setId(auteur.getId());
//			auteurinterne.setIdtelecom(idInterne);
//			auteurDAO.create(auteurinterne);
//		}else{
//			Auteurexterne auteurexterne = new Auteurexterne(auteur);
//			auteurexterne.setId(auteur.getId());
//			auteurexterne.setIdexterne(idExterne);
//			auteurDAO.create(auteurexterne);
//		}
		
//		Auteurinterne auteurinterne;
//		Auteurexterne auteurexterne;
//		if(auteur instanceof Auteurinterne){
//			auteurinterne = auteurDAO.findByIdAuteurInterne(identifiant);
//		}
//		if(auteur instanceof Auteurexterne){
//			auteurexterne = auteurDAO.findByIdAuteurExterne(identifiant);
//		}
//		if(TypeAuteur.auteurinterne.toString().equals(type)){
//			auteurinterne = new Auteurinterne(auteur);
//			auteurinterne.setId(auteur.getId());
//			auteurinterne.setIdtelecom(idInterne);
//			auteurDAO.update(auteurinterne);
//		}
//		if(TypeAuteur.auteurexterne.toString().equals(type)){
//			auteurexterne = new Auteurexterne(auteur);
//			auteurexterne.setId(auteur.getId());
//			auteurexterne.setIdexterne(idExterne);
//			auteurDAO.update(auteurexterne);
//		}
	}
	
}
