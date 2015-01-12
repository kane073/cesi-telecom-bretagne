package eu.telecom_bretagne.CESI.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.AuteurDAO;
import eu.telecom_bretagne.CESI.data.dao.PublicationDAO;
import eu.telecom_bretagne.CESI.data.model.Auteur;
import eu.telecom_bretagne.CESI.data.model.Conference;
import eu.telecom_bretagne.CESI.data.model.Journal;
import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.data.model.Reference;
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
	
	@EJB
	IGestionReference gestionReference;
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

	@SuppressWarnings("unchecked")
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
			listAuteur.add(auteurDAO.findById(id));
		}
		System.out.println(listAuteur.toString());
		Publication publication = new Publication();
		publication.setTitre(titre);
		publication.setResume(resume);
		publication.setLangue(langue);
		publication.setDatepublication(datePublication);
		publication.setType(type);
		publication.setDatedebutpublication(new Date());
		if(TypePublication.journal.toString().equals(type)){
			Journal journal = new Journal(publication);
			journal.setNomjournal(nomJournal);
			journal.setSujet(sujet);
			journal.setDateapparition(dateApparition);
			journal.setVolume(volume);
			journal.setAuteurs(new HashSet<Auteur>(listAuteur));
			
			for (Auteur a : listAuteur) {
				a.getPublications().add(journal);
			}
			publicationDAO.create(journal);
			for (Auteur a : listAuteur) {
				auteurDAO.update(a);
			}
			return journal;
		}else{
			Conference conference = new Conference(publication);
			conference.setTheme(theme);
			conference.setLieu(lieu);
			conference.setIsbn(isbn);
			conference.setEditeur(editeur);
			conference.setDescription(description);	
			conference.setAuteurs(new HashSet<Auteur>(listAuteur));
			for (Auteur a : listAuteur) {
				a.getPublications().add(conference);
			}
			publicationDAO.create(conference);
			for (Auteur a : listAuteur) {
				auteurDAO.update(a);
			}
			return conference;
		}
		
	}

	@Override
	public Publication creerConference(String titre, Date datePublication,
			String resume, String langue, String theme, String lieu,
			String isbn, String editeur, String description,
			List<Integer> listIdentifiantAuteur) throws Exception{
		return creerPublication(TypePublication.conference.toString(), 
				titre, datePublication, resume, langue,
				null, null, null, null, 
				theme, lieu, isbn, editeur, description, listIdentifiantAuteur);
		
	}

	@Override
	public Publication creerJournal(String titre, Date datePublication,
			String resume, String langue, String nomJournal, String sujet,
			Date dateApparition, String volume,
			List<Integer> listIdentifiantAuteur) throws Exception{
		return creerPublication(TypePublication.journal.toString(), titre, datePublication, resume, langue, 
				nomJournal, sujet, dateApparition, volume, 
				null, null, null, null, null,  listIdentifiantAuteur);
	}

	@Override
	public void modifierPublication(int identifiant, String titre,
			Date datePublication, String resume, String langue,
			List<Integer> listIdentifiantAuteur) throws Exception {
		
		Publication publication = publicationDAO.findById(identifiant);
		if(publication == null){
			throw new Exception(MessageExceptionCesi.ExceptionPublicationNotExiste.getMessage());
		}
		
		if(publication.getReferences()!=null && !publication.getReferences().isEmpty()){
			throw new Exception(MessageExceptionCesi.ExceptionPublicationNotModifier.getMessage());
		}
		if(HelperCesi.listIsEmpty(listIdentifiantAuteur)){
			throw new Exception(MessageExceptionCesi.ExceptionPublicationAuteur.getMessage());
		}
		List<Auteur> listAuteur = new ArrayList<Auteur>();
		for (int id : listIdentifiantAuteur) {
			listAuteur.add(auteurDAO.findById(id));
		}
		
		String oldTitre = publication.getTitre();
		Set<Auteur> oldAuteur = publication.getAuteurs();
		
		if(!oldTitre.equals(titre) && !HelperCesi.listIsEmpty(publicationDAO.findByTitre(titre))){
			throw new Exception(MessageExceptionCesi.ExceptionPublicationExiste.getMessage());
		}
		
		publication.setTitre(titre);
		publication.setDatepublication(datePublication);
		publication.setResume(resume);
		publication.setLangue(langue);
		publication.setAuteurs(new HashSet<Auteur>(listAuteur));
		
		for (Auteur a : oldAuteur) {
			a.getPublications().remove(publication);
		}
		for (Auteur a : listAuteur) {
			a.getPublications().add(publication);
		}
		publicationDAO.update(publication);
		for (Auteur a : oldAuteur) {
			auteurDAO.update(a);
		}
		for (Auteur a : listAuteur) {
			auteurDAO.update(a);
		}
		
	}

	@Override
	public void supprimerPublication(int identifiant) throws Exception {
		
		Publication publication = publicationDAO.findById(identifiant);
		if(publication == null){
			throw new Exception(MessageExceptionCesi.ExceptionPublicationNotExiste.getMessage());
		}
		publication.setDatefinpublication(new Date());
		publicationDAO.update(publication);
		
		//Modification de la reference de la publication
		if(publication.getReferences()!=null && !publication.getReferences().isEmpty()){
			for(Reference reference: publication.getReferences()){
				gestionReference.supprimerReference(reference.getIdReference());
			}
		}
	}

	@Override
	public List<Publication> recherchePublicationParTitre(String textSearch)
			throws Exception {
		
		return publicationDAO.findPublicationCriteriaTitre(textSearch);
	}

	@Override
	public List<Publication> recherchePublicationParAuteur(String nom)
			throws Exception {
		return publicationDAO.findPublicationCriteriaNomAuteur(nom);
	}

	@Override
	public List<String> typeRecherche() {
		List<String> listRecherche = new ArrayList<String>();
		listRecherche.add("Auteur");
		listRecherche.add("Titre");
		return listRecherche;
	}
	
	

}
