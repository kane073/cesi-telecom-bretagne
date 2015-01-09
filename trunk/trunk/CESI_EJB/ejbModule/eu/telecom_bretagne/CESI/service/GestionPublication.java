package eu.telecom_bretagne.CESI.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.AuteurDAO;
import eu.telecom_bretagne.CESI.data.dao.PublicationDAO;
import eu.telecom_bretagne.CESI.data.model.Auteur;
import eu.telecom_bretagne.CESI.data.model.Conference;
import eu.telecom_bretagne.CESI.data.model.Journal;
import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.data.util.HelperCesi;
import eu.telecom_bretagne.CESI.data.util.TypePublication;
import eu.telecom_bretagne.CESI.exception.MessageExceptionCesi;

/**
 * Session Bean implementation class GestionPublication
 */
@Stateless
@LocalBean
public class GestionPublication implements IGestionPublication {
	
	@EJB
	PublicationDAO publicationDAO;
	
	@EJB
	AuteurDAO auteurDAO;
	
    /**
     * Default constructor. 
     */
    public GestionPublication() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Publication> listPublication() {
		
		return publicationDAO.findAll();
	}

	@Override
	public Publication lirePublication(int identifiant) {
		return publicationDAO.findById(identifiant);
	}

	@Override
	public Publication creerPublication(String type, String titre,
			Date datePublication, String resume, String langue,
			String nomJournal, String sujet, Date dateApparition,
			String volume, String theme, String lieu, String isbn,
			String editeur, String description, 
			List<Integer> listIdentifiantAuteur) throws Exception{
		
		if(!HelperCesi.listIsEmpty(publicationDAO.findByTitre(titre))){
			throw new Exception(MessageExceptionCesi.ExceptionPublicationExiste.getMessage());
		}
		
		if(HelperCesi.listIsEmpty(listIdentifiantAuteur)){
			throw new Exception(MessageExceptionCesi.ExceptionPublicationAuteur.getMessage());
		}
		List<Auteur> listAuteur = new ArrayList<Auteur>();
		for (int id : listIdentifiantAuteur) {
			if(auteurDAO.findById(id)!=null){
				listAuteur.add(auteurDAO.findById(id));
			}
		}
		Publication publication = new Publication();
		publication.setTitre(titre);
		publication.setResume(resume);
		publication.setLangue(langue);
		publication.setDatepublication(datePublication);
		publication.setDatedebutpublication(new Date());
		if(TypePublication.journal.toString().equals(type)){
			Journal journal = new Journal(publication);
			journal.setNomjournal(nomJournal);
			journal.setSujet(sujet);
			journal.setDateapparition(dateApparition);
			journal.setVolume(volume);
			publicationDAO.create(journal);
			return journal;
		}else{
			Conference conference = new Conference(publication);
			conference.setTheme(theme);
			conference.setLieu(lieu);
			conference.setIsbn(isbn);
			conference.setEditeur(editeur);
			conference.setDescription(description);	
			publicationDAO.create(conference);
			return conference;
		}
		
	}

	@Override
	public Publication creerConference(String titre, Date datePublication,
			String resume, String langue, String theme, String lieu,
			String isbn, String editeur, String description,
			List<Integer> listIdentifiantAuteur) throws Exception{
		creerPublication(TypePublication.conference.toString(), 
				titre, datePublication, resume, langue,
				null, null, null, null, 
				theme, lieu, isbn, editeur, description, listIdentifiantAuteur);
		return null;
	}

	@Override
	public Publication creerJournal(String titre, Date datePublication,
			String resume, String langue, String nomJournal, String sujet,
			Date dateApparition, String volume,
			List<Integer> listIdentifiantAuteur) throws Exception{
		creerPublication(TypePublication.journal.toString(), titre, datePublication, resume, langue, 
				nomJournal, sujet, dateApparition, volume, 
				null, null, null, null, null,  listIdentifiantAuteur);
		return null;
	}

}
