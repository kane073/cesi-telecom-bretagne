package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Conference;
import eu.telecom_bretagne.CESI.data.model.Journal;
import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.service.IGestionAuteur;
import eu.telecom_bretagne.CESI.service.IGestionPublication;

public class AppelGestionPublication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	
	public void test_listePublication(){
		for (Publication publication : getGestionPublication().listPublication()) {
			afficherPublication(publication);
		}
	}
	
	
	public void afficherPublication(Publication publication){
		System.out.println("Titre :"+publication.getTitre()+"\n");
		if(publication instanceof Journal){
			System.out.println("Cette publication est un journal \n");
		}
		if(publication instanceof Conference){
			System.out.println("Cette publication est une conférence \n");
		}
	}
}
