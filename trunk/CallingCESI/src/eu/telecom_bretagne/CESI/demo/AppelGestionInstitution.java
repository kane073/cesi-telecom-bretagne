package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Institutionrattchement;
import eu.telecom_bretagne.CESI.service.IGestionInstitution;

public class AppelGestionInstitution {

	public static void main(String[] args) {
		test_listeInstitutions();
		test_creerInstitution();
		test_listeInstitutions();
	}

	public static IGestionInstitution getGestionInstitution() {
		InitialContext ctx = null;
		IGestionInstitution gestionInstitution = null;
		try {
			ctx = new InitialContext();
			gestionInstitution = (IGestionInstitution) ctx
					.lookup(IGestionInstitution.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionInstitution;
	}

	public static void test_listeInstitutions() {
		for (Institutionrattchement institutionrattchement : getGestionInstitution()
				.listeInstitution()) {
			afficherInstitution(institutionrattchement);
		}
	}

	public static void test_lireInstitution() {
		test_lireInstitution(1);
	}

	public static void test_lireInstitution(Integer id) {
		Integer identifiant = id;
		afficherInstitution(getGestionInstitution().lireInstitutionrattchement(
				identifiant));
	}

	public static void test_creerInstitution() {
		String nom = "Télécom Paris";
		String adresse = "Rue Barrau";

		Institutionrattchement institutionrattchement;
		try {
			institutionrattchement = getGestionInstitution().creerInstitution(nom, adresse);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void afficherInstitution(
			Institutionrattchement institutionrattchement) {
		System.out.println("Nom institution :"
				+ institutionrattchement.getNominstitution() + " Adresse : "
				+ institutionrattchement.getAdresse() + "\n");
	}

}
