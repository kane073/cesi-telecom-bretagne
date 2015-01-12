package eu.telecom_bretagne.CESI.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.criteria.CriteriaBuilder.In;

import eu.telecom_bretagne.CESI.data.model.Conference;
import eu.telecom_bretagne.CESI.data.model.Journal;
import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.service.IGestionPublication;

public class AppelGestionPublication {

	public static void main(String[] args) {
		
		//test_listePublication();
		//test_supprimerPublication();
		//test_creerPublication();
		//test_modifierPublication();
		//test_rechercheParTitre();
		//test_rechercheParAuteur();
	}

	public static IGestionPublication getGestionPublication() {
		InitialContext ctx = null;
		IGestionPublication gestionPublication = null;
		try {
			ctx = new InitialContext();
			gestionPublication = (IGestionPublication) ctx
					.lookup(IGestionPublication.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionPublication;
	}
	
	public static void test_listePublication(){
		for (Publication publication : getGestionPublication().listPublication()) {
			afficherPublication(publication);
		}
	}
	
	public static void test_creerPublication(){
		List<Integer> auteurList = new ArrayList<Integer>();
		auteurList.add(9);
		auteurList.add(10);
		try {
			Publication publication = getGestionPublication().creerJournal("Asp.net step by step", new Date(), "Papapapa", "chinois", 
					"papa ou tu es", "Allo ici nabilla", 
					new Date(), "6", auteurList);
			afficherPublication(publication);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void test_modifierPublication(){
		List<Integer> auteurList = new ArrayList<Integer>();
		auteurList.add(9);
		auteurList.add(1);
		try {
			getGestionPublication().modifierPublication(7, 
					"Un jour à Brest", new Date() , "Coucou", "français", auteurList);
			afficherPublication(getGestionPublication().lirePublication(7));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void test_supprimerPublication(){
		try{
			getGestionPublication().supprimerPublication(3);
			afficherPublication(getGestionPublication().lirePublication(3));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static void test_rechercheParTitre(){
		try{
			String textSearch = "jour";
			for (Publication publication : getGestionPublication().recherchePublicationParTitre(textSearch)) {
				afficherPublication(publication);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void test_rechercheParAuteur(){
		try{
			String textSearch = "kane";
			List<Publication> publications = getGestionPublication().recherchePublicationParAuteur(textSearch);
			System.out.println(publications.toString());
			for (Publication publication : publications) {
				afficherPublication(publication);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void afficherPublication(Publication publication){
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
		
		System.out.println("Titre :"+publication.getTitre()+"\n");
		if(publication.getDatefinpublication()!=null){
			System.out.println("Date fin publication: "+ formater.format(publication.getDatefinpublication()));
		}else{
			System.out.println("Date fin publication: null");
		}
		if(publication instanceof Journal){
			System.out.println("Cette publication est un journal \n");
		}
		if(publication instanceof Conference){
			System.out.println("Cette publication est une conférence \n");
		}
	}
}
