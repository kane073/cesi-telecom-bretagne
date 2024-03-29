package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Auteur;
import eu.telecom_bretagne.CESI.data.model.Auteurexterne;
import eu.telecom_bretagne.CESI.data.model.Auteurinterne;
import eu.telecom_bretagne.CESI.service.IGestionAuteur;
import eu.telecom_bretagne.CESI.utile.TypeAuteur;

public class AppelGestionAuteur {

	public static void main(String[] args) {
		//test_listeAuteur();
		//test_creerAuteur();
		//test_modifierAuteu();
		
	}

	public static IGestionAuteur getGestionAuteur() {
		InitialContext ctx = null;
		IGestionAuteur gestionAuteur = null;
		try {
			ctx = new InitialContext();
			gestionAuteur = (IGestionAuteur) ctx
					.lookup(IGestionAuteur.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionAuteur;
	}

	public static void test_listeAuteur() {
		for (Auteur auteur : getGestionAuteur().listeAuteur()) {
			afficherAuteur(auteur);
		}
	}
	public static void test_creerAuteur(){
		Auteur auteur;
		try {
			auteur = getGestionAuteur().creerAuteurInterne("KANE", "Alassane", 
					"alassane.kane@telecom-bretagne.eu", "sitePerso", "akane", 3);
			afficherAuteur(auteur);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void test_modifierAuteu(){
		getGestionAuteur().modifierAuteur(1, "KANE", "Fousseyni", 
				"fousseyni.kane@gmail.com", "sitePerso", 3);
	}
	public static void afficherAuteur(Auteur auteur) {
		System.out.println("Auteur id:" + auteur.getId() + " nom: "
				+ auteur.getNom() + " prenom :" + auteur.getPrenom() + " email"
				+ auteur.getEmail()+"\n");
		if (auteur instanceof Auteurexterne) {
			System.out.println("Est un auteur Externe");
		}
		if (auteur instanceof Auteurinterne) {
			System.out.println("Est un auteur Interne");
		}
	}
}
